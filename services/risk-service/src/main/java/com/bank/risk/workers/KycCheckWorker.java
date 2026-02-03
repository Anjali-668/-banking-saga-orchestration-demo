
package com.bank.risk.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class KycCheckWorker implements Worker {
    @Override public String getTaskDefName() { return "kyc_check"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String customerId = String.valueOf(task.getInputData().get("customerId"));
        System.out.println("[KYC] customerId=" + customerId + " status=PASS");
        result.getOutputData().put("kycStatus", "PASS");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
