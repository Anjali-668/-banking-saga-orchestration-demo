
package com.bank.core.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class RollbackLedgerWorker implements Worker {
    @Override public String getTaskDefName() { return "rollback_ledger"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String loanId = String.valueOf(task.getInputData().getOrDefault("loanId", "UNKNOWN"));
        System.out.println("[ROLLBACK_LEDGER] loanId=" + loanId + " status=REVERSED");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
