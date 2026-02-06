package com.bank.loan.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class AfterWaitAuditWorker implements Worker {

    @Override
    public String getTaskDefName() { return "after_wait_audit"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        result.getOutputData().put("auditTag", "AuditCompleted");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
