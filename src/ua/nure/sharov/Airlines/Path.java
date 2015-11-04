package ua.nure.sharov.Airlines;

public final class Path {
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	public static final String PAGE_WELCOME = "index.jsp";
	public static final String SERVLET_WELCOME = "WelcomeServlet";

	public static final String PAGE_FLIGHTS = "/WEB-INF/jsp/flightsList.jsp";
	public static final String PAGE_FLIGHTS_NUMBER = "/WEB-INF/jsp/flightListNumber.jsp";

	public static final String PAGE_DISPATCHER_HOME = "/WEB-INF/jsp/dispatcher/dispatcherHomePage.jsp";
	public static final String PAGE_DISPATCHER_FIND_TEAM = "/WEB-INF/jsp/dispatcher/dispatcherFindTeamPage.jsp";
	public static final String PAGE_DISPATCHER_SHOW_TEAM = "/WEB-INF/jsp/dispatcher/dispatcherShowTeamPage.jsp";
	public static final String PAGE_DISPATCHER_SHOW_CURRENT_FLIGHTS = "/WEB-INF/jsp/dispatcher/dispatcherCurrentFlightsPage.jsp";
	public static final String PAGE_DISPATCHER_EDIT_FLIGHT_STATUS = "/WEB-INF/jsp/dispatcher/dispatcherEditFlightStatusPage.jsp";
	public static final String PAGE_DISPATCHER_CREATE_APPLICATION = "/WEB-INF/jsp/dispatcher/dispatcherCreateApplicationPage.jsp";
	
	public static final String PAGE_ADMIN_HOME = "/WEB-INF/jsp/administrator/administratorHomePage.jsp";
	public static final String PAGE_ADMIN_SHOW_FLIGHTS = "/WEB-INF/jsp/administrator/administratorShowFlightsPage.jsp";
	public static final String PAGE_ADMIN_EDIT_FLIGHT= "/WEB-INF/jsp/administrator/administratorEditFlightPage.jsp";
	public static final String PAGE_ADMIN_ADD_FLIGHT= "/WEB-INF/jsp/administrator/administratorAddFlightPage.jsp";
	public static final String PAGE_ADMIN_SHOW_STAFF = "/WEB-INF/jsp/administrator/administratorShowStaffPage.jsp";
	public static final String PAGE_ADMIN_EDIT_STAFF = "/WEB-INF/jsp/administrator/administratorEditStaffPage.jsp";
	public static final String PAGE_ADMIN_ADD_STAFF = "/WEB-INF/jsp/administrator/administratorAddStaffPage.jsp";
	public static final String PAGE_ADMIN_SHOW_APPLICATION = "/WEB-INF/jsp/administrator/administratorShowApplicationPage.jsp";
	
	public static final String COMMAND_ADMIN_SHOW_FLIGHTS_REDIRECT = "controller?command=showFlightsPage";
	public static final String COMMAND_ADMIN_SHOW_APPLICATION_REDIRECT = "controller?command=showApplicationPage";
	public static final String COMMAND_ADMIN_SHOW_STAFF_REDIRECT = "controller?command=showStaffPage";

	public static final String COMMAND_ADMIN_PAGE = "controller?command=administratorHomePage";
	public static final String COMMAND_DISPATCHER_PAGE = "controller?command=dispatcherHomePage";
	
	public static final String COMMAND_DISPATCHER_FIND_TEAM_REDIRECT = "controller?command=findTeam";
	public static final String COMMAND_DISPATCHER_SHOW_FLIGHTS_REDIRECT = "controller?command=showFlights";
	public static final String COMMAND_DISPATCHER_CREATE_APPLICATION_PAGE_REDIRECT = "controller?command=createApplicationPage";
}