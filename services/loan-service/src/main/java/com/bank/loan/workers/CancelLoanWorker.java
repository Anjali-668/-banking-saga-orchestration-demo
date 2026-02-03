
package com.bank.loan.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class CancelLoanWorker implements Worker {
    @Override public String getTaskDefName() { return "cancel_loan_app"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String loanId = String.valueOf(task.getInputData().getOrDefault("loanId", "UNKNOWN"));
        System.out.println("[CANCEL_LOAN] loanId=" + loanId);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
