
package com.bank.core.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

import java.util.UUID;

public class ReserveFundsWorker implements Worker {
    @Override public String getTaskDefName() { return "reserve_funds"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String accountId = String.valueOf(task.getInputData().get("accountId"));
        double amount = Double.parseDouble(String.valueOf(task.getInputData().getOrDefault("amount", 0)));
        String vaultAccountId = "VAULT-" + UUID.randomUUID().toString().substring(0, 8);
        String holdId = "HOLD-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("[RESERVE] accountId=" + accountId + " amount=" + amount + " holdId=" + holdId);
        result.getOutputData().put("vaultAccountId", vaultAccountId);
        result.getOutputData().put("holdId", holdId);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
