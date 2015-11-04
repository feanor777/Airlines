package ua.nure.sharov.Airlines.db.bean;

import ua.nure.sharov.Airlines.db.entity.Entity;
/**
 * bean which represent table | teamId | flights number | from(city) | to(city) | date of departure | 
 * @author Max
 *
 */
public class TeamFlightBean extends Entity {

	private static final long serialVersionUID = -8099804898521243924L;

	private int teamId;

	private String flightsNumber;

	private String from;

	private String to;

	private String dateOfDeparture;

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

	public String getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(String dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	@Override
	public String toString() {
		return "teamId =" + teamId + ", flightsNumber ="
				+ flightsNumber + ", from =" + from + ", to=" + to
				+ ", dateOfDeparture = " + dateOfDeparture;
	}

}
