/*
 * Copyright 2012-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.simpleworkflow.flow.common;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClientExternal;
import com.amazonaws.services.simpleworkflow.flow.worker.GenericWorkflowClientExternalImpl;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;

/**
 * Simple example utility to pretty print workflow execution history. 
 * 
 * @author fateev
 */
public class WorkflowExecutionGetState {
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java " + WorkflowExecutionGetState.class.getName() + " <workflowId> <runId>");
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
        
        GenericWorkflowClientExternal client = new GenericWorkflowClientExternalImpl(swfService, domain);
        
        String state = client.getWorkflowState(workflowExecution);
        
        System.out.println("Current state of " + workflowExecution + ":");
        System.out.println(state);
    }    
    
}
