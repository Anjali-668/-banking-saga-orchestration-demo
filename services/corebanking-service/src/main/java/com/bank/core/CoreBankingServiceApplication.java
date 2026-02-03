
package com.bank.core;

import com.bank.core.workers.*;
import com.netflix.conductor.client.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreBankingServiceApplication {
    public static void main(String[] args) { SpringApplication.run(CoreBankingServiceApplication.class, args); }

    @Bean public Worker reserveFundsWorker() { return new ReserveFundsWorker(); }
    @Bean public Worker disburseFundsWorker() { return new DisburseFundsWorker(); }
    @Bean public Worker postLedgerWorker() { return new PostLedgerWorker(); }
    @Bean public Worker rollbackLedgerWorker() { return new RollbackLedgerWorker(); }
    @Bean public Worker releaseFundsWorker() { return new ReleaseFundsWorker(); }
}
