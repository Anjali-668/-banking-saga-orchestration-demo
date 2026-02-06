package com.bank.loan.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class EscalationWorker implements Worker {

    @Override
    public String getTaskDefName() { return "escalate_to_manual_review"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        result.getOutputData().put("ticketId", "INC-12345");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}