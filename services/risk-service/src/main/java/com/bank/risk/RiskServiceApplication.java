
package com.bank.risk;

import com.bank.risk.workers.*;
import com.netflix.conductor.client.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RiskServiceApplication {
    public static void main(String[] args) { SpringApplication.run(RiskServiceApplication.class, args); }

    @Bean public Worker kycWorker() { return new KycCheckWorker(); }
    @Bean public Worker creditWorker() { return new CreditCheckWorker(); }
    @Bean public Worker uwWorker() { return new UnderwriteDecisionWorker(); }
}
