package com.amazonaws.services.simpleworkflow.flow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.services.simpleworkflow.flow.common.SampleConstants; 

public class ChargeCreditCard {
	Properties config;
	ChargeCreditCard() {
		Map<String, String> env = System.getenv();
        String configPath = env.get(SampleConstants.ACCESS_PROPERTIES_ENVIRONMENT_VARIABLE); 
        File accessProperties = null;
        if (configPath != null && configPath.length() > 0) {
            accessProperties = new File(configPath, "CreditCardDetails.properties");
            FileInputStream inputStream;
			try {
				inputStream = new FileInputStream(accessProperties);
	            config = new Properties();
	            config.load(inputStream);
			} catch (FileNotFoundException e) {
				System.out.println("Given File CreditCardDetails.properties not found at location"+configPath);
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException Thrown for file CreditCardDetails.properties ");
				e.printStackTrace();
			}
        }
	}
	Boolean checkProperBalance(String name, String passwd, int userAmount){
		System.out.println("The values are "+name+" "+getValue("user.name"));
		if( getValue("user.name").equals(name) &&  getValue("user.password").equals(passwd) && Integer.parseInt(getValue("user.balance")) <= userAmount) {
			return true;
		}
		return false;
	}
	private String getValue(String key) {
		return config.getProperty(key);
	}
}
