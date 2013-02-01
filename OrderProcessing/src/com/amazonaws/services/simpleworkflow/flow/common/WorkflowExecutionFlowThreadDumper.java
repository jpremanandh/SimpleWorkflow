package com.amazonaws.services.simpleworkflow.flow.common;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.WorkflowException;
import com.amazonaws.services.simpleworkflow.flow.WorkflowReplayer;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;

public class WorkflowExecutionFlowThreadDumper {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Usage: java " + WorkflowExecutionFlowThreadDumper.class.getName()
                    + "<workflow implementation class> <workflowId> <runId>");
            System.exit(1);
        }
        ConfigHelper configHelper = ConfigHelper.createConfig();
        AmazonSimpleWorkflow swfService = configHelper.createSWFClient();
        String domain = configHelper.getDomain();

        WorkflowExecution workflowExecution = new WorkflowExecution();
        String workflowId = args[1];
        workflowExecution.setWorkflowId(workflowId);
        String runId = args[2];
        workflowExecution.setRunId(runId);

        String implementationTypeName = args[0];
        @SuppressWarnings("unchecked")
        Class<Object> workflowImplementationType = (Class<Object>) Class.forName(implementationTypeName);
        WorkflowReplayer<Object> replayer = new WorkflowReplayer<Object>(swfService, domain, workflowExecution,
                workflowImplementationType);

        System.out.println("Beginning workflow replay for " + workflowExecution);
        try {
            String flowThreadDump = replayer.getAsynchronousThreadDumpAsString();
            System.out.println("Workflow asynchronous thread dump:");
            System.out.println(flowThreadDump);
        }
        catch (WorkflowException e) {
            System.out.println("No asynchronous thread dump available as workflow has failed: " + e);
        }
    }

}
