package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.configuration.annotation.DataOutboundGateway;
import com.stepsoft.study.flow.BatchServiceActivator;
import com.stepsoft.study.mvc.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jpa.outbound.JpaOutboundGateway;
import org.springframework.integration.splitter.DefaultMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;
import java.util.Set;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_BULK_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_BULK_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.BULK_SIZE;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FINDING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FINDING_MODEL_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MODEL_GATE_WAY_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MODIFYING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.REMOVING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.REPLY_DB_CHANNEL;
import static java.lang.Integer.parseInt;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan(basePackages = {"com.stepsoft.study.flow", "com.stepsoft.study.configuration.flow"})
@ComponentScan(basePackages = {"com.stepsoft.study.flow", "com.stepsoft.study.configuration.flow"})
@Import({ChannelContext.class, DataContext.class})
public class FlowContext {

    @MessagingGateway
    public interface ModelGateway {

        @Gateway(requestChannel = ADDING_MODEL_CHANNEL, replyChannel = ADDING_MODEL_REPLY_CHANNEL, headers = {
                @GatewayHeader(name = MODEL_GATE_WAY_ACTION,
                        expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).ADDING_MODEL")})
        Long add(RestModel model);

        @SuppressWarnings("SpringElInspection")
        @Gateway(requestChannel = ADDING_MODEL_BULK_CHANNEL, replyChannel = ADDING_MODEL_BULK_REPLY_CHANNEL, headers = {
                @GatewayHeader(name = BULK_SIZE, expression = "peyload.size()"),
                @GatewayHeader(name = MODEL_GATE_WAY_ACTION,
                        expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).ADDING_MODEL_BULK")})
        Set<Long> add(Set<RestModel> model);

        @Gateway(requestChannel = MODIFYING_MODEL_CHANNEL, headers = {
                @GatewayHeader(name = MODEL_GATE_WAY_ACTION,
                        expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).MODIFYING_MODEL")})
        void modify(RestModel model);

        @Gateway(requestChannel = REMOVING_MODEL_CHANNEL, headers = {
                @GatewayHeader(name = MODEL_GATE_WAY_ACTION,
                        expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).REMOVING_MODEL")})
        void remove(Long id);

        @Gateway(requestChannel = FINDING_MODEL_CHANNEL, replyChannel = FINDING_MODEL_REPLY_CHANNEL, headers = {
                @GatewayHeader(name = MODEL_GATE_WAY_ACTION,
                        expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).FINDING_MODEL")})
        RestModel find(Long id);
    }

    @Bean
    @Splitter(inputChannel = ADDING_MODEL_BULK_CHANNEL)
    public DefaultMessageSplitter bulkModelSplitter() {

        DefaultMessageSplitter splitter = new DefaultMessageSplitter();
        splitter.setOutputChannelName(ADDING_MODEL_CHANNEL);
        return splitter;
    }

    @MessageEndpoint
    public static class BulkModelAggregatorEndpoint {

        @Aggregator(inputChannel = ADDING_MODEL_REPLY_CHANNEL)
        public Set<Long> aggregationMethod(Set<Long> ids) {

            return ids;
        }

        @ReleaseStrategy
        public boolean releaseStrategyMethod(List<Message<Long>> messages) {

            int bulkSize = parseInt((String) messages.get(0).getHeaders().get(BULK_SIZE));
            return bulkSize == messages.size();
        }

        @CorrelationStrategy
        public String correlationStrategyMethod(@Header(name = BULK_SIZE) String bulkSize) {

            return bulkSize;
        }
    }

    @MessageEndpoint
    public static class BulkRoutingEndpoint {

        @Router(inputChannel = REPLY_DB_CHANNEL)
        public String routeMethod(@Header(name = BULK_SIZE) String bulkSize) {
            if (bulkSize == null) {
                return ADDING_MODEL_REPLY_CHANNEL;
            }
            return ADDING_MODEL_BULK_REPLY_CHANNEL;
        }
    }

    @MessageEndpoint
    public static class BatchServiceActivatorEndpoint {

        @Autowired
        private BatchServiceActivator serviceActivator;

        @ServiceActivator(inputChannel = ADDING_MODEL_CHANNEL, outputChannel = IN_DB_CHANNEL)
        public Message<RestModel> serviceMethod(Message<RestModel> message) {
            return serviceActivator.service(message);
        }
    }

    @DataOutboundGateway(inputChannel = IN_DB_CHANNEL)
    public JpaOutboundGateway dbGateway() {

//        JpaOutboundGateway gateway = new JpaOutboundGateway()
        return null;
    }

}
