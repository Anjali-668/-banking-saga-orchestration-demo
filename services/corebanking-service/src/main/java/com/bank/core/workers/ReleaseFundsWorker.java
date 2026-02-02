
package com.bank.core.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class ReleaseFundsWorker implements Worker {
    @Override public String getTaskDefName() { return "release_funds"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String accountId = String.valueOf(task.getInputData().getOrDefault("accountId", "UNKNOWN"));
        double amount = Double.parseDouble(String.valueOf(task.getInputData().getOrDefault("amount", 0)));
        System.out.println("[RELEASE_FUNDS] accountId=" + accountId + " amount=" + amount + " status=RELEASED");
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
