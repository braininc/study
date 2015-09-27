package com.stepsoft.study.flow;

import com.stepsoft.study.data.entity.Sinner;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author Eugene Stepanenkov
 */
@Service
public class ProcessingService {

    public Message<Sinner> service(Message<Sinner> message) {
        return message;
    }
}