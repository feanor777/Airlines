package ua.nure.sharov.Airlines.db;

import ua.nure.sharov.Airlines.db.entity.User;

public enum Role {
	ADMINISTRATOR, DISPATCHER;

	public static Role getRole(User user) {
		return Role.values()[user.getRoleId()];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
