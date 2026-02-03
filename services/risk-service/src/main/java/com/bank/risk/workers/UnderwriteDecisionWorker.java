
package com.bank.risk.workers;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class UnderwriteDecisionWorker implements Worker {
    @Override public String getTaskDefName() { return "underwrite_decision"; }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        int score = Integer.parseInt(String.valueOf(task.getInputData().get("score")));
        String decision = (score >= 650) ? "APPROVE" : "REJECT";
        System.out.println("[UW] score=" + score + " decision=" + decision);
        result.getOutputData().put("decision", decision);
        if (score < 650) {
            result.setReasonForIncompletion("Underwriting rejected due to score " + score);
            result.setStatus(TaskResult.Status.FAILED);
        } else {
            result.setStatus(TaskResult.Status.COMPLETED);
        }
        return result;
    }
}
