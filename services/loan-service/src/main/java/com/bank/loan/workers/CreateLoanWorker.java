
package com.bank.loan.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

import java.util.UUID;

public class CreateLoanWorker implements Worker {
    @Override
    public String getTaskDefName() { return "create_loan_app"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        try {
            String applicant = String.valueOf(task.getInputData().getOrDefault("applicant", "UNKNOWN"));
            double amount = Double.parseDouble(String.valueOf(task.getInputData().getOrDefault("amount", 0)));
            // Simulate creating entities
            String loanId = UUID.randomUUID().toString();
            String customerId = UUID.randomUUID().toString();
            String accountId = "ACC-" + customerId.substring(0, 8);
            System.out.println("[CREATE_LOAN] applicant=" + applicant + ", amount=" + amount + ", loanId=" + loanId);
            result.getOutputData().put("loanId", loanId);
            result.getOutputData().put("customerId", customerId);
            result.getOutputData().put("accountId", accountId);
            result.setStatus(TaskResult.Status.COMPLETED);
        } catch (Exception e) {
            result.setReasonForIncompletion("create_loan_app failed: " + e.getMessage());
            result.setStatus(TaskResult.Status.FAILED);
        }
        return result;
    }
}
