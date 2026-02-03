
package com.bank.core.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class PostLedgerWorker implements Worker {
    @Override public String getTaskDefName() { return "post_ledger"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String loanId = String.valueOf(task.getInputData().get("loanId"));
        double amount = Double.parseDouble(String.valueOf(task.getInputData().getOrDefault("amount", 0)));
        System.out.println("[LEDGER] loanId=" + loanId + " amount=" + amount + " status=POSTED");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
