package ua.nure.sharov.Airlines.exception;

public class Messages {
	public static final String LOG_COMMAND_STARTS = "Command starts";
	public static final String LOG_COMMAND_FINISHED = "Command finished";

	public static final String ERR_CANT_GET_CONNECTION_DB_POOL = "Can not get a connection from data base pool";
	public static final String ERR_EMPTY_FIELD = "One of the fields in the form is empty";
	
	public static final String ERR_INCORRECT_FIELD = "One of the fields is not correct";

	public static final String ERR_CANT_GET_USER_BY_LOGIN = "Can not get a user from data base with current login";
	public static final String ERR_CANT_GET_FLIGHT_BY_FROM_TO_DATE = "Can not get a flight from data base with current parameteres: from, to, date of departure";
	public static final String ERR_CANT_GET_FLIGHT_WITHOUT_TEAM = "Can not get a flight without team";
	public static final String ERR_CANT_GET_FLIGHT_BY_NUMBER = "Can not get a flight from data base with current number";
	public static final String ERR_CANT_GET_FLIGHT_BY_ID = "Can not get a flight from data base with current id";
	public static final String ERR_CANT_GET_FLIGHT_STATUS_ID = "Can not get a flight status id from data base with current flight id";
	public static final String ERR_CANT_GET_TOP_5_FLIGHT = "Can not get the top five flights from data base";
	public static final String ERR_CANT_GET_CURRENT_FLIGHTS = "Can not get current flights from data base";
	public static final String ERR_CANT_GET_FREE_STAFFERS = "Can not get a list with free staffers from data base";
	public static final String ERR_CANT_GET_TEAM_STAFF_BEAN = "Can not get a teamStaffBean from data base";
	public static final String ERR_CANT_GET_TEAM_FLIGHT_BEAN = "Can not get a teamFlightBean from data base";
	public static final String ERR_CANT_GET_APPLICATION = "Can not get an application from data base";
	public static final String ERR_CANT_GET_STAFF = "Can not get a staff from data base";	
	public static final String ERR_CANT_GET_STAFF_BY_ID = "Can not get a staff from data base with current id";
	public static final String ERR_CANT_GET_STAFFERS_FROM_TEAM = "Can not get a staffers from team by id";
	public static final String ERR_CANT_CREATE_TEAM = "Not enough team members";
	public static final String ERR_CANT_FIND_ALL_TEAMS = "Can not find all teams";
	
	public static final String ERR_CANT_EDIT_STAFF = "Please select just one row to be edited!";
	
	public static final String ERR_CANT_UPDATE_STAFF = "Can not update staff";
	public static final String ERR_CANT_UPDATE_NOT_FREE_STAFF = "Can not update staff which is not free";
	public static final String ERR_CANT_UPDATE_FLIGHT_STATUS = "Can not update status of the flight";
	public static final String ERR_CANT_UPDATE_FLIGHT = "Can not update flight";
	public static final String ERR_CANT_UPDATE_APPLICATION_STATUS = "Can not update application status";
	
	public static final String ERR_CANT_INSERT_TEAM = "Can not insert new team";
	public static final String ERR_CANT_INSERT_TEAM_STAFF = "Can not insert new team-staff";
	public static final String ERR_CANT_INSERT_FLIGHT = "Can not insert new flight";
	public static final String ERR_CANT_INSERT_STAFF = "Can not insert new staff";
	public static final String ERR_CANT_INSERT_APPLICATION = "Can not insert new application";
	
	public static final String ERR_CANT_DELETE_FLIGHT = "Can not delete the flight";	
	public static final String ERR_CANT_DELETE_STAFF = "Can not delete the staff";	

	public static final String ERR_CANT_CLOSE_CONNECTION = "Can not close a connection";
	public static final String ERR_CANT_CLOSE_STATEMENT = "Can not close a statement";
	public static final String ERR_CANT_CLOSE_RESULT_SET = "Can not close a resul set";
	
	public static final String ERR_CANT_GET_CURRENCY = "Can not get an exchange rates";
	
	public static final String ERR_CANT_SEND_MESSAGE = "Can not send message by email";

}
