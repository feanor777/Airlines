package ua.nure.sharov.Airlines.db.entity;

public class Team extends Entity {

	private static final long serialVersionUID = 5619228640369377326L;
	private int flight_id;
	private int user_id;

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Team [flight_id=" + flight_id + ", user_id=" + user_id
				+ ", getId()=" + getId() + "]";
	}

}
