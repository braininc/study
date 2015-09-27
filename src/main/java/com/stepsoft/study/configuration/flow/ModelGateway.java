package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.mvc.model.RestModel;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Set;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_BULK_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_BULK_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.BULK_SIZE;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FINDING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FINDING_MODEL_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MODEL_GATE_WAY_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MODIFYING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.REMOVING_MODEL_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@MessagingGateway
public interface ModelGateway {

    @Gateway(
            requestChannel = ADDING_MODEL_CHANNEL,
            replyChannel = ADDING_MODEL_REPLY_CHANNEL,
            headers = {
                    @GatewayHeader(name = MODEL_GATE_WAY_ACTION,
                            expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).ADDING_MODEL")
            }
    )
    Long add(RestModel model);

    @Gateway(
            requestChannel = ADDING_MODEL_BULK_CHANNEL,
            replyChannel = ADDING_MODEL_BULK_REPLY_CHANNEL,
            headers = {
                    @GatewayHeader(
                            name = BULK_SIZE,
                            expression = "peyload.size()"
                    ),
                    @GatewayHeader(
                            name = MODEL_GATE_WAY_ACTION,
                            expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).ADDING_MODEL_BULK")
            }
    )
    Set<Long> add(Set<RestModel> model);

    @Gateway(
            requestChannel = MODIFYING_MODEL_CHANNEL,
            headers = {
                    @GatewayHeader(
                            name = MODEL_GATE_WAY_ACTION,
                            expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).MODIFYING_MODEL")
            }
    )
    void modify(RestModel model);

    @Gateway(
            requestChannel = REMOVING_MODEL_CHANNEL,
            headers = {
                    @GatewayHeader(
                            name = MODEL_GATE_WAY_ACTION,
                            expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).REMOVING_MODEL")
            }
    )
    void remove(Long id);

    @Gateway(
            requestChannel = FINDING_MODEL_CHANNEL,
            replyChannel = FINDING_MODEL_REPLY_CHANNEL,
            headers = {
                    @GatewayHeader(
                            name = MODEL_GATE_WAY_ACTION,
                            expression = "T(com.stepsoft.study.flow.messaging.ModelGatewayAction).FINDING_MODEL")
            }
    )
    RestModel find(Long id);
}
