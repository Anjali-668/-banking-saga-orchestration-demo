
package com.bank.core.config;

import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConductorConfig {
    @Value("${CONDUCTOR_SERVER_URL:http://localhost:8080/api/}")
    private String conductorServerUrl;

    @Bean
    public TaskClient taskClient() {
        TaskClient client = new TaskClient();
        client.setRootURI(conductorServerUrl);
        return client;
    }

    @Bean(destroyMethod = "shutdown")
    public TaskRunnerConfigurer taskRunnerConfigurer(TaskClient client, List<Worker> workers) {
        TaskRunnerConfigurer configurer = new TaskRunnerConfigurer.Builder(client, workers)
                .withThreadCount(2)
                .build();
        configurer.init();
        return configurer;
    }
}
