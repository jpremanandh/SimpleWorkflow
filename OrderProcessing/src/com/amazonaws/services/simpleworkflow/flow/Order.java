package com.amazonaws.services.simpleworkflow.flow;

public class Order {
	int volume;
	Type selectedType;

	Order(int count, Type type) {
		this.volume = count;
		this.selectedType = type;
	}

	Boolean isOrderValid() {
		if (this.volume > 0) {
			return true;
		} else {
			return false;
		}
	}
}
