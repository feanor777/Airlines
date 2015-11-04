package ua.nure.sharov.Airlines.db.bean;

import java.util.List;

import ua.nure.sharov.Airlines.db.entity.Entity;
import ua.nure.sharov.Airlines.db.entity.Staff;

/**
 * bean which represent table | teamId | flights number | from(city) | to(city) | staff on the flight | date of departure | dispatcher last name| 
 * @author Max
 *
 */
public class TeamStaffBean extends Entity {

	private static final long serialVersionUID = -8099804898521243924L;

	private int teamId;

	private String flightsNumber;

	private String from;

	private String to;

	private List<Staff> staffList;

	private String dateOfDeparture;

	private String dispatcherLastName;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getFlightsNumber() {
		return flightsNumber;
	}

	public void setFlightsNumber(String flightsNumber) {
		this.flightsNumber = flightsNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

	public String getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(String dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public String getDispatcherLastName() {
		return dispatcherLastName;
	}

	public void setDispatcherLastName(String dispatcherLastName) {
		this.dispatcherLastName = dispatcherLastName;
	}

	@Override
	public String toString() {
		return "TeamStaffBean [teamId=" + teamId + ", flightsNumber="
				+ flightsNumber + ", staffList=" + staffList
				+ ", dateOfDeparture=" + dateOfDeparture
				+ ", dispatcherLastName=" + dispatcherLastName + "]";
	}

}
