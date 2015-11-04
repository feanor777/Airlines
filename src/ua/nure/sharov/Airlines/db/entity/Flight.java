package ua.nure.sharov.Airlines.db.entity;

import org.joda.time.LocalDate;

import ua.nure.sharov.Airlines.db.FlightStatus;

public class Flight extends Entity {
	private static final long serialVersionUID = -1867569291371538015L;
	private String number;
	private String from;
	private String to;
	private LocalDate date_of_departure;
	private int flight_status_id;
	private int price;
	private boolean is_free;

	public boolean isIs_free() {
		return is_free;
	}

	public void setIs_free(boolean is_free) {
		this.is_free = is_free;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public LocalDate getDate_of_departure() {
		return date_of_departure;
	}

	public void setDate_of_departure(String flightDateOfDeparture) {
		this.date_of_departure = new LocalDate(flightDateOfDeparture);
	}

	public int getFlight_status_id() {
		return flight_status_id;
	}

	public void setFlight_status_id(int flight_status_id) {
		this.flight_status_id = flight_status_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flight [number=" + number + ", from=" + from + ", to=" + to
				+ ", date_of_departure=" + date_of_departure + ", price="
				+ price + ", flight_status="
				+ FlightStatus.getFlightStatusById(flight_status_id) + "]";
	}
}
