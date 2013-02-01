package com.amazonaws.services.simpleworkflow.flow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activity;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.common.FlowConstants;

@Activities(version = "1.0")
@ActivityRegistrationOptions(defaultTaskHeartbeatTimeoutSeconds = FlowConstants.NONE, defaultTaskScheduleToCloseTimeoutSeconds = 300, defaultTaskScheduleToStartTimeoutSeconds = 60, defaultTaskStartToCloseTimeoutSeconds = 60)
public interface ParentActivities {

	 void reserveCar(int requestId);

	 void reserveAirline(int requestId);
 
	 void sendConfirmationActivity(int customerId);	
	
	 @Activity(name="OrderVerification",version="1.0")
	 boolean verifyOrder(int a,Type t);
	
	 @Activity(name="CreditCardCharge",version="1.0")
	 boolean chargeCreditCard(String name, String passwd, int userAmount);
	
	 @Activity(name="ShipOrder", version="1.0")
	 boolean shipOrder(int a);
	
	 @Activity(name="RecordCompletion", version="1.0")
	 boolean recordCompletion(int a);
		
}
