package com.rxlogix.testEngine;

import java.util.*;

public class KeyMappingHelper {
	
	public static final Map<String, String> keySetMapping =new HashMap<String, String>() {{
	    put("Alert Type", "alertType");
	    put("Assigned To", "assignedTo");
	    put("Owner", "owner");
	    put("Products", "products");
	    put("Priority", "priority");
	    put("Date Range Type", "dateRangeType");
	    put("X", "xForDateRange");
	    put("Start Date", "startDate");
	    put("End Date", "endDate");
	    put("Version As Of Date", "versionDate");
	    put("Share With", "shareWith");
	    put("Scheduler", "scheduler");
	}};
	
	public static Map<String, String> getKeySetMapping(){
		return keySetMapping;
	}
	

}
