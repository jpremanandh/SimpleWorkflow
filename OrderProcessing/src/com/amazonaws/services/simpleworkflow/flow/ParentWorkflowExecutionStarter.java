package com.amazonaws.services.simpleworkflow.flow;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.common.ConfigHelper;

public class ParentWorkflowExecutionStarter {
    private static AmazonSimpleWorkflow swfService;
    private static String domain;
    
    public static void main(String[] args) throws Exception {

    	// Load configuration
    	ConfigHelper configHelper = ConfigHelper.createConfig();
        
        // Create the client for Simple Workflow Service
        swfService = configHelper.createSWFClient();
        domain = configHelper.getDomain();
               
        ParentWorkflowClientExternalFactory clientFactory = new ParentWorkflowClientExternalFactoryImpl(swfService, domain);
        if(clientFactory != null){
        	System.out.println("swfservice: "+swfService+" domain: "+domain);
        }
        ParentWorkflowClientExternal workflow = clientFactory.getClient();
        if(workflow != null){
        	System.out.println("workflow created "+workflow);
        }
        workflow.processOrder();
        
        System.exit(0);
    }    
}
