package com.stepsoft.study.flow.messaging;

import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.transaction.annotation.Transactional;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IMPORT_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@MessagingGateway(
        defaultRequestChannel = IN_IMPORT_CHANNEL,
        defaultReplyChannel = OUT_IMPORT_CHANNEL
)
@Transactional
public interface ImportGateway {

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).ADD"
            )
    })
    SinnerModel add(SinnerModel sinner);

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).UPDATE"
            )
    })
    SinnerModel modify(SinnerModel sinner);

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).DELETE"
            )
    })
    SinnerModel remove(Long id);

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).FETCH"
            )
    })
    SinnerModel find(Long id);
}
