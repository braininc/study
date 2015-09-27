package com.stepsoft.study.flow;

import com.stepsoft.study.mvc.model.RestModel;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author Eugene Stepanenkov
 */
@Service
public class BatchServiceActivator {

    public Message<RestModel> service(Message<RestModel> message) {

        return message;
    }
}