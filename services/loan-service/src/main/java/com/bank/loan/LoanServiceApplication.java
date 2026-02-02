
package com.bank.loan;

import com.bank.loan.workers.*;
import com.netflix.conductor.client.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

    @Bean public Worker createLoanWorker() { return new CreateLoanWorker(); }
    @Bean public Worker cancelLoanWorker() { return new CancelLoanWorker(); }
    @Bean public Worker successNotifyWorker() { return new SuccessNotifyWorker(); }
    @Bean public Worker failureNotifyWorker() { return new FailureNotifyWorker(); }
}
