package com.amazonaws.services.simpleworkflow.flow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;

@Workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 600, defaultTaskStartToCloseTimeoutSeconds = 300)
public interface ParentWorkflow {

    @Execute(name = "ProcessOrder", version = "1.0")
    void processOrder();

}
