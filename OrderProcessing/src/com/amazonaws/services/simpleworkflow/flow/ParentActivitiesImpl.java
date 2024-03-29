package com.amazonaws.services.simpleworkflow.flow;

public class ParentActivitiesImpl implements ParentActivities {

	@Override
	public void reserveCar(int requestId) {
        System.out.printf("Reserving car for Request ID: %d...\n", requestId);
	}

	@Override
	public void reserveAirline(int requestId) {
        System.out.printf("Reserving airline for Request ID: %d...\n", requestId);
    }
	
	@Override
	public void sendConfirmationActivity(int customerId){
		System.out.printf("Sending notification to Customer '%d'...\n", customerId);
	}

	@Override
	public boolean verifyOrder(int a, Type t) {
		Order o = new Order( a, t);
    	Boolean isOrderProper ;
    	if ( o.isOrderValid() ){
    		System.out.println("Order is Proper "+t.name());
    		isOrderProper = true; 
    	} else {
    		System.out.println("Order is not Proper "+t.name());
    		isOrderProper = false;
    	}
    	return isOrderProper;
	}

	@Override
	public boolean chargeCreditCard(String name, String passwd, int userAmount) {
		System.out.println(name+" "+passwd+" "+userAmount);
		ChargeCreditCard creditCardCharge = new ChargeCreditCard();
    	if (creditCardCharge.checkProperBalance(name, passwd, userAmount)){
    		System.out.println("Credit Card Details is Proper");
    		return true;
    	} else {
    		System.out.println("Credit Card Details is not Proper");
    		return false;
    	}
	}

	@Override
	public boolean shipOrder(int Amount) { 
		Boolean isOrderProper ;
    	if (Amount > 0){
    		System.out.println("Order Shipped");
    		isOrderProper = true; 
    	} else {
    		System.out.println("Order not Shipped");
    		isOrderProper = false;
    	}
    	return isOrderProper;
	}

	@Override
	public boolean recordCompletion(int RecordStored) {
		Boolean isOrderProper ;
    	if (RecordStored > 0){
    		System.out.println("Completion Recorded");
    		isOrderProper = true; 
    	} else {
    		System.out.println("Completion Not Recorded");
    		isOrderProper = false;
    	}
    	return isOrderProper;
	}
}
