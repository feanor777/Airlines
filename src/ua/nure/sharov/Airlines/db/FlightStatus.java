package ua.nure.sharov.Airlines.db;

import java.io.Serializable;

import ua.nure.sharov.Airlines.db.entity.Flight;

public class FlightStatus implements Serializable{
	private static final long serialVersionUID = -3518151639822219840L;

	private enum FlightStatusList {
		A("Active"), C("Canceled"), D("Diverted "), L("Landed"), S("Scheduled");
		private String status;

		FlightStatusList(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return status;
		}
	}

	public String getFlightStatus(Flight flight) {
		return FlightStatusList.values()[flight.getFlight_status_id()]
				.toString();
	}

	public static String getFlightStatusById(int statusId) {
		return FlightStatusList.values()[statusId].toString();
	}
}