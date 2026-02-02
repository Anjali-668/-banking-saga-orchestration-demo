
package com.bank.loan.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class SuccessNotifyWorker implements Worker {
    @Override public String getTaskDefName() { return "send_success_notification"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String loanId = String.valueOf(task.getInputData().get("loanId"));
        System.out.println("[NOTIFY_SUCCESS] Loan " + loanId + " approved & disbursed.");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
