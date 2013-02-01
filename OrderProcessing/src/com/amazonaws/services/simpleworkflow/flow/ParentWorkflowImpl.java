package com.amazonaws.services.simpleworkflow.flow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public class ParentWorkflowImpl implements ParentWorkflow {

    private final ParentActivitiesClient client = new ParentActivitiesClientImpl();
    //private final OrderProcessingActivitiesCient orderClient = new OrderProcessingActivitiesCientImpl();

    @Override
    public void processOrder() {
       Promise<Boolean> creditCardCharged = null, shipOrder = null, recordCompletion = null;      
        
        Promise<Boolean> isOrderProper = checkOrder();
        Promise<Boolean> isCreditCardCharged = chargeCreditCard(isOrderProper);
        Promise<Boolean> isOrderShipped = shipOrder(isCreditCardCharged); 
        Promise<Boolean> isRecordCompleted = recordCompletion(isOrderShipped);
    }
    

    
    @Asynchronous
    public Promise<Boolean> shipOrder(Promise<Boolean> iscreditCardCharged) {
    	Promise<Boolean> orderShipped = null;
    	if(iscreditCardCharged.get()) {
    		orderShipped = client.shipOrder(10);
    	}
		return orderShipped;
    }
    
    @Asynchronous
    public Promise<Boolean> recordCompletion(Promise<Boolean> isOrderShipped) {
    	Promise<Boolean> RecordCompleted = null;
    	if(isOrderShipped.get()) {
    		RecordCompleted = client.recordCompletion(1);    		
    	}
    	return RecordCompleted;
    }
    
    @Asynchronous
    public Promise<Boolean> chargeCreditCard(Promise<Boolean> isOrderProper) {
    	Promise<Boolean> creditCardCharged = null;
    	if(isOrderProper.get()) {
    		creditCardCharged = client.chargeCreditCard("Premanandh","test",100);
    	}
		return creditCardCharged;
    }
    
    @Asynchronous
    public Promise<Boolean> checkOrder() {
    	Promise<Boolean> isOrderProper = null;
        isOrderProper = client.verifyOrder(10,Type.ACCESSIORIES);
        return isOrderProper ;
    }
}
