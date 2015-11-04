package ua.nure.sharov.Airlines.db;

import java.io.Serializable;

import ua.nure.sharov.Airlines.db.entity.Application;

public class ApplicationStatus implements Serializable{
	private static final long serialVersionUID = -8975204570682481260L;

	private enum ApplicationStatusList {
		PERFORMED, DENIED;
	}
	
	public static ApplicationStatusList getApplicationStatus(Application application) {
		return ApplicationStatusList.values()[application.getApplication_status_id()];
	}

	public static String getApplicationStatus(int applicationStatusId) {
		return ApplicationStatusList.values()[applicationStatusId].toString().toLowerCase();
	}	
}