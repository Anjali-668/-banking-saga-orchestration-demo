
package com.bank.risk.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class CreditCheckWorker implements Worker {
    @Override public String getTaskDefName() { return "credit_check"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        double amount = Double.parseDouble(String.valueOf(task.getInputData().getOrDefault("amount", 0)));
        int score = (int) (Math.random() * 300) + 600; // 600-900
        System.out.println("[CREDIT] amount=" + amount + " score=" + score);
        result.getOutputData().put("score", score);
        if (amount > 100000 && score < 650) {
            result.setReasonForIncompletion("Low credit score " + score + " for amount " + amount);
            result.setStatus(TaskResult.Status.FAILED);
        } else {
            result.setStatus(TaskResult.Status.COMPLETED);
        }
        return result;
    }
}
