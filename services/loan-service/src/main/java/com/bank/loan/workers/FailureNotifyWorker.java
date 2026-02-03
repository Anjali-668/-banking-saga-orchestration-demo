
package com.bank.loan.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class FailureNotifyWorker implements Worker {
    @Override public String getTaskDefName() { return "send_failure_notification"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String reason = String.valueOf(task.getInputData().getOrDefault("reason", "UNKNOWN"));
        String loanId = String.valueOf(task.getInputData().getOrDefault("loanId", "UNKNOWN"));
        System.out.println("[NOTIFY_FAILURE] Loan " + loanId + " failed. Reason: " + reason);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
