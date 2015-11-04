package ua.nure.sharov.Airlines.db;

import java.io.Serializable;

import ua.nure.sharov.Airlines.db.entity.Staff;

public class Profession implements Serializable {

	private static final long serialVersionUID = 3421110145786071701L;

	private enum ProfessionList {
		PILOT, NAVIGATOR, OPERATOR, FLIGHT_ATTENDANT;
	}

	public String getProfession(Staff staff) {
		return ProfessionList.values()[staff.getProfession_id()].toString()
				.toLowerCase();
	}

	public String getProfessionById(int staffId) {
		return ProfessionList.values()[staffId].toString().toLowerCase();
	}
}