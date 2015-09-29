package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.BULK_SIZE;
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
    Long add(SinnerModel sinner);

    @Gateway(headers = {
            @GatewayHeader(
                    name = BULK_SIZE,
                    expression = "peyload.size()"
            ),
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).ADD_BULK"
            )
    })
    Set<Long> add(Set<SinnerModel> sinners);

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).UPDATE"
            )
    })
    void modify(SinnerModel sinner);

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).DELETE"
            )
    })
    void remove(Long id);

    @Gateway(headers = {
            @GatewayHeader(
                    name = IMPORT_ACTION,
                    expression = "T(com.stepsoft.study.flow.messaging.ImportAction).FETCH"
            )
    })
    SinnerModel find(Long id);
}
