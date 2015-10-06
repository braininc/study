package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.configuration.annotation.JpaGateway;
import com.stepsoft.study.configuration.annotation.MessageHeaderEnricher;
import com.stepsoft.study.data.entity.Sinner;
import com.stepsoft.study.flow.messaging.ImportAction;
import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.outbound.JpaOutboundGateway;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.handler.annotation.Header;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IMPORT_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DELETE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_FETCH_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_POST_PERSIST_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_PRE_PERSIST_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_TRANSFORMATION_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IS_PERSISTED;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_TRANSFORMER_CHANNEL;
import static java.util.Collections.singletonMap;
import static org.springframework.integration.jpa.support.OutboundGatewayType.RETRIEVING;
import static org.springframework.integration.jpa.support.OutboundGatewayType.UPDATING;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@Import(ImportChannelContext.class)
public class ImportFlowContext {

    @MessageEndpoint
    public static class InImportRouterEndpoint {

        @Router(inputChannel = IN_IMPORT_CHANNEL)
        public String route(@Header(name = IMPORT_ACTION) ImportAction action) {

            switch (action) {

                case DELETE:
                case FETCH:
                    return IN_IMPORT_DB_CHANNEL;

                case ADD:
                case UPDATE:
                    return IN_IMPORT_TRANSFORMATION_CHANNEL;

                default:
                    throw new IllegalStateException();
            }
        }
    }

    @MessageEndpoint
    public static class InImportTransformerEndpoint {

        @Transformer(inputChannel = IN_IMPORT_TRANSFORMATION_CHANNEL, outputChannel = IN_IMPORT_DB_CHANNEL)
        public Sinner transform(Sinner sinner) {

            return sinner;
        }
    }

    @MessageEndpoint
    public static class InImportDbRouterEndpoint {

        @Router(inputChannel = IN_IMPORT_DB_CHANNEL)
        public String route(@Header(name = IMPORT_ACTION) ImportAction action) {

            switch (action) {

                case DELETE:
                    return IN_IMPORT_PRE_PERSIST_CHANNEL;

                case FETCH:
                    return IN_IMPORT_FETCH_DB_CHANNEL;

                case ADD:
                case UPDATE:
                    return IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL;

                default:
                    throw new IllegalStateException();
            }
        }
    }

    @Bean
    @MessageHeaderEnricher(inputChannel = IN_IMPORT_PRE_PERSIST_CHANNEL, outputChannel = IN_IMPORT_FETCH_DB_CHANNEL)
    public HeaderEnricher inImportPrePersistHeaderEnricher() {

        return new HeaderEnricher(singletonMap(IS_PERSISTED, new StaticHeaderValueMessageProcessor<>(false)));
    }

    @Bean
    @MessageHeaderEnricher(inputChannel = IN_IMPORT_POST_PERSIST_CHANNEL, outputChannel = IN_IMPORT_DELETE_DB_CHANNEL)
    public HeaderEnricher inImportPostPersistHeaderEnricher() {

        HeaderEnricher enricher = new HeaderEnricher(
                singletonMap(IS_PERSISTED, new StaticHeaderValueMessageProcessor<>(true)));
        enricher.setDefaultOverwrite(true);

        return enricher;
    }

    @Bean
    @Autowired
    @JpaGateway(inputChannel = IN_IMPORT_FETCH_DB_CHANNEL)
    public JpaOutboundGateway inImportFetchDbGateway(JpaExecutor importFetchJpaExecutor) {

        JpaOutboundGateway gateway = new JpaOutboundGateway(importFetchJpaExecutor);
        gateway.setOutputChannelName(OUT_IMPORT_DB_CHANNEL);
        gateway.setGatewayType(RETRIEVING);
        gateway.setRequiresReply(true);

        return gateway;
    }

    @Bean
    @Autowired
    @JpaGateway(inputChannel = IN_IMPORT_DELETE_DB_CHANNEL)
    public JpaOutboundGateway inImportDeleteDbGateway(JpaExecutor importDeleteJpaExecutor) {

        JpaOutboundGateway gateway = new JpaOutboundGateway(importDeleteJpaExecutor);
        gateway.setOutputChannelName(OUT_IMPORT_DB_CHANNEL);
        gateway.setGatewayType(UPDATING);

        return gateway;
    }

    @Bean
    @Autowired
    @JpaGateway(inputChannel = IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL)
    public JpaOutboundGateway inImportAddOrUpdateDbGateway(JpaExecutor importAddOrUpdateJpaExecutor) {

        JpaOutboundGateway gateway = new JpaOutboundGateway(importAddOrUpdateJpaExecutor);
        gateway.setOutputChannelName(OUT_IMPORT_DB_CHANNEL);
        gateway.setGatewayType(UPDATING);

        return gateway;
    }

    @MessageEndpoint
    public static class OutImportDbRouterEndpoint {

        @Router(inputChannel = OUT_IMPORT_DB_CHANNEL)
        public String route(@Header(name = IMPORT_ACTION) ImportAction action,
                            @Header(name = IS_PERSISTED, required = false) Boolean isPersisted) {

            switch (action) {

                case DELETE:
                    if (isPersisted == null || !isPersisted) {
                        return IN_IMPORT_POST_PERSIST_CHANNEL;
                    }

                case FETCH:
                case ADD:
                case UPDATE:
                    return OUT_IMPORT_TRANSFORMER_CHANNEL;

                default:
                    throw new IllegalStateException();
            }
        }
    }

    @MessageEndpoint
    public static class OutImportTransformerEndpoint {


        @Transformer(inputChannel = OUT_IMPORT_TRANSFORMER_CHANNEL, outputChannel = OUT_IMPORT_CHANNEL)
        public SinnerModel transform(SinnerModel sinnerModel) {

            return sinnerModel;
        }
    }
}
