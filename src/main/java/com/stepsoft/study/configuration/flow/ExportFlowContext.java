package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.flow.messaging.ExportAction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@Import(ExportChannelContext.class)
public class ExportFlowContext {

    @MessageEndpoint
    public static class inExportTransformerEndpoint {

        private Job job;

        @Transformer
        public JobLaunchRequest transform(@Header ExportAction exportAction) {

            JobParametersBuilder builder = new JobParametersBuilder();
            builder.addString("key", "sdf");

            return new JobLaunchRequest(job, builder.toJobParameters());
        }
    }
}
