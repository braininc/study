package com.stepsoft.study.configuration.flow;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.EXPORT_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@MessagingGateway(defaultRequestChannel = IN_EXPORT_CHANNEL)
public interface ExportGateway {

    @Gateway(headers = {
            @GatewayHeader(
                    name = EXPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ExportAction).EXPORT"
            )
    })
    void export();
}
