package ua.nure.sharov.Airlines.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.db.bean.TeamFlightBean;
import ua.nure.sharov.Airlines.db.bean.TeamStaffBean;
import ua.nure.sharov.Airlines.db.entity.Application;
import ua.nure.sharov.Airlines.db.entity.Flight;
import ua.nure.sharov.Airlines.db.entity.Staff;
import ua.nure.sharov.Airlines.db.entity.Team;
import ua.nure.sharov.Airlines.db.entity.User;
import ua.nure.sharov.Airlines.exception.DBException;
import ua.nure.sharov.Airlines.exception.Messages;
/**
 * This class represents connection with data base and have methods to get information from db
 * @author Max
 *
 */
public class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static DBManager instance;

	private DataSource ds;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/ST4DB");
			LOG.trace("DataSource object => " + ds);
		} catch (NamingException e) {
			LOG.error(Messages.ERR_CANT_GET_CONNECTION_DB_POOL, e);
			throw new DBException(Messages.ERR_CANT_GET_CONNECTION_DB_POOL, e);
		}
	}

	// SQL queries
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
	private static final String SQL_FIND_TOP_5_FLIGHTS = "SELECT * FROM flights WHERE date_of_departure >= current_date() AND flight_status_id = 4 ORDER BY date_of_departure, price LIMIT 5";
	private static final String SQL_FIND_FLIGHT_BY_FROM_TO_DATE = "SELECT * FROM flights WHERE from_city = ? AND to_city = ? AND date_of_departure = ? AND flight_status_id NOT IN(1, 3)";
	private static final String SQL_FIND_FLIGHT_BY_NUMBER = "SELECT * FROM flights WHERE number = ? AND date_of_departure >= current_date() AND flight_status_id NOT IN(1, 3)";
	private static final String SQL_FIND_FLIGHT_BY_ID = "SELECT * FROM flights WHERE id = ?";
	private static final String SQL_FIND_FLIGHT_STATUS_BY_ID = "SELECT flight_status_id FROM flights WHERE id = ?";
	private static final String SQL_FIND_FLIGHT_WITHOUT_TEAM = "SELECT * FROM flights WHERE flight_status_id = 4 AND "
			+ "(SELECT count(*) FROM team WHERE(flights.id = team.flights_id)) = 0"
			+ " AND date_of_departure >= current_date()";
	private static final String SQL_FIND_NOT_C_AND_L_FLIGHTS = "SELECT * FROM flights WHERE flight_status_id NOT IN (1, 3)";
	private static final String SQL_FIND_FREE_STAFFERS = "SELECT * FROM staff WHERE is_free = 1 AND is_deleted = 0 ORDER BY profession_id, last_name";
	private static final String SQL_FIND_ALL_TEAMS = "SELECT * FROM TEAM";
	private static final String SQL_FIND_ALL_TEAM_STAFF_BEAN = "SELECT t.id, f.number, f.from_city, f.to_city, f.date_of_departure, u.last_name FROM team t, flights f, users u "
			+ "WHERE t.flights_id = f.id AND t.users_id = u.id";
	private static final String SQL_FIND_ALL_TEAM_FLIGHT_BEAN = "SELECT t.id, f.number, f.from_city, f.to_city, f.date_of_departure "
			+ "FROM team t JOIN flights f ON t.flights_id = f.id WHERE f.flight_status_id NOT IN (0, 1, 3)";
	private static final String SQL_FIND_STAFFERS_IN_TEAM_BY_TEAM_ID = "SELECT s.id, s.first_name, s.last_name, s.is_free, s.profession_id "
			+ "FROM staff s JOIN team_staff ts ON ts.staff_id = s.id"
			+ " WHERE ts.team_id = ? ORDER BY profession_id";
	private static final String SQL_FIND_STAFFERS_IN_TEAM_BY_FLIGHT_ID = "SELECT staff_id FROM team_staff JOIN team on team_id = team.id WHERE flights_id = ?";
	private static final String SQL_FIND_ALL_STAFF = "SELECT id, first_name, last_name, is_free, profession_id FROM staff WHERE is_deleted = 0 ORDER BY profession_id";
	private static final String SQL_FIND_STAFF_BY_ID = "SELECT * FROM staff WHERE id = ?";
	private static final String SQL_FIND_TEAM_BY_FLIGHT_ID = "SELECT COUNT(*) count FROM team WHERE flights_id = ?";
	private static final String SQL_FIND_APPLICATION_BY_ID = "SELECT * FROM application WHERE id = ?";
	private static final String SQL_FIND_UNRESOLVED_APPLICATION = "SELECT * FROM application WHERE application_status_id IS NULL";
	private static final String SQL_GET_COUNT_OF_STAFFERS = "SELECT profession_id, COUNT(*) AS count FROM airlines.staff WHERE id in(?, ?, ?, ?, ?, ?, ?) group by profession_id ORDER BY profession_id";
	private static final String SQL_GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
	private static final String SQL_INSERT_INTO_TEAM = "INSERT INTO team (flights_id, users_id) values (?, ?)";
	private static final String SQL_INSERT_INTO_TEAM_STAFF = "INSERT INTO team_staff (team_id, staff_id) VALUES(?, ?)";
	private static final String SQL_INSERT_INTO_FLIGHT = "INSERT INTO flights (number, from_city, to_city, date_of_departure, price) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_INTO_STAFF = "INSERT INTO staff (first_name, last_name, profession_id) VALUES (?, ?, ?)";
	private static final String SQL_INSERT_INTO_APPLICATION_WITH_TEAM = "INSERT INTO application (problem_description, email, team_id) VALUES (?, ?, ?)";
	private static final String SQL_INSERT_INTO_APPLICATION_WITHOUT_TEAM = "INSERT INTO application (problem_description, email) VALUES (?, ?)";
	private static final String SQL_UPDATE_STAFFERS_TO_BUSY = "UPDATE staff SET is_free = 0 WHERE id = ?";
	private static final String SQL_UPDATE_STAFFERS_TO_FREE = "UPDATE staff SET is_free = 1 WHERE id = ?";
	private static final String SQL_UPDATE_FLIGHT_STATUS = "UPDATE flights SET flight_status_id = ? WHERE id = ?";
	private static final String SQL_UPDATE_FLIGHT = "UPDATE flights SET number = ?, from_city = ?, to_city = ?, date_of_departure = ?, price = ? WHERE id = ?";
	private static final String SQL_UPDATE_STAFF = "UPDATE staff SET first_name = ?, last_name = ?, profession_id = ? WHERE id = ?";
	private static final String SQL_UPDATE_APPLICATION = "UPDATE application SET  users_id = ?, application_status_id = ? WHERE id = ?";
	private static final String SQL_DELETE_FLIGHT_BY_ID = "DELETE FROM flights WHERE id = ?";
	private static final String SQL_DELETE_STAFF_BY_ID = "DELETE FROM staff WHERE id = ?";

	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANT_GET_CONNECTION_DB_POOL, e);
			throw new DBException(Messages.ERR_CANT_GET_CONNECTION_DB_POOL, e);
		}
		return con;
	}

	public User findUserByLogin(String login) throws DBException {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}

			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_USER_BY_LOGIN, e);
			throw new DBException(Messages.ERR_CANT_GET_USER_BY_LOGIN, e);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	public List<Flight> findFlightsByFromToDate(String from, String to,
			String date) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Flight> flights = new ArrayList<Flight>();
		Flight flight = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FLIGHT_BY_FROM_TO_DATE);
			pstmt.setString(1, from);
			pstmt.setString(2, to);
			pstmt.setString(3, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				flight = extractFlight(rs);
				flights.add(flight);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FLIGHT_BY_FROM_TO_DATE, e);
			throw new DBException(Messages.ERR_CANT_GET_FLIGHT_BY_FROM_TO_DATE,
					e);
		} finally {
			close(con, pstmt, rs);
		}

		return flights;
	}

	public List<Flight> findFlightsByNumber(int number) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Flight> flights = new ArrayList<Flight>();
		Flight flight = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FLIGHT_BY_NUMBER);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				flight = extractFlight(rs);
				flights.add(flight);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FLIGHT_BY_NUMBER, e);
			throw new DBException(Messages.ERR_CANT_GET_FLIGHT_BY_NUMBER, e);
		} finally {
			close(con, pstmt, rs);
		}
		return flights;
	}

	public Flight findFlightById(int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Flight flight = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FLIGHT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flight = extractFlight(rs);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FLIGHT_BY_ID, e);
			throw new DBException(Messages.ERR_CANT_GET_FLIGHT_BY_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return flight;
	}

	public List<Flight> findFlightsWithoutTeam() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Flight> flights = new ArrayList<Flight>();
		Flight flight = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_FLIGHT_WITHOUT_TEAM);
			while (rs.next()) {
				flight = extractFlight(rs);
				flights.add(flight);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FLIGHT_WITHOUT_TEAM, e);
			throw new DBException(Messages.ERR_CANT_GET_FLIGHT_WITHOUT_TEAM, e);
		} finally {
			close(con, stmt, rs);
		}
		return flights;
	}

	public Map<Integer, Integer> findCountOfStaffers(int[] staffId)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Integer, Integer> staffers = new HashMap<Integer, Integer>();

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_GET_COUNT_OF_STAFFERS);
			for (int i = 0; i < staffId.length; i++) {
				pstmt.setInt(i + 1, staffId[i]);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				staffers.put(rs.getInt(Fields.STAFF_PROFESSION_ID),
						rs.getInt("count"));
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_CREATE_TEAM, e);
			throw new DBException(Messages.ERR_CANT_CREATE_TEAM, e);
		} finally {
			close(con, pstmt, rs);
		}
		return staffers;
	}

	public List<Staff> findFreeStaffers() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Staff> freeStaffers = new ArrayList<Staff>();
		Staff staff = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_FREE_STAFFERS);
			while (rs.next()) {
				staff = extractStaff(rs);
				freeStaffers.add(staff);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FREE_STAFFERS, e);
			throw new DBException(Messages.ERR_CANT_GET_FREE_STAFFERS, e);
		} finally {
			close(con, stmt, rs);
		}
		return freeStaffers;
	}

	public void updateStaffersStatusToBusy(int[] id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_STAFFERS_TO_BUSY);
			for (int i = 0; i < id.length; i++) {
				pstmt.setInt(1, id[i]);
				pstmt.executeUpdate();
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_UPDATE_STAFF, e);
			throw new DBException(Messages.ERR_CANT_UPDATE_STAFF, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void updateStaffersStatusToFree(List<Integer> id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_STAFFERS_TO_FREE);
			for (int i = 0; i < id.size(); i++) {
				pstmt.setInt(1, id.get(i));
				pstmt.executeUpdate();
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_UPDATE_STAFF, e);
			throw new DBException(Messages.ERR_CANT_UPDATE_STAFF, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public int insertNewTeam(int flightId, long userId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int lastInsertId = -1;
		try {
			con = getConnection();
			stmt = con.createStatement();
			pstmt = con.prepareStatement(SQL_INSERT_INTO_TEAM);
			pstmt.setInt(1, flightId);
			pstmt.setLong(2, userId);
			pstmt.executeUpdate();
			rs = stmt.executeQuery(SQL_GET_LAST_INSERT_ID);
			if (rs.next()) {
				lastInsertId = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_INSERT_TEAM, e);
			throw new DBException(Messages.ERR_CANT_INSERT_TEAM, e);
		} finally {
			close(pstmt);
			close(con, stmt, rs);
		}
		return lastInsertId;
	}

	public List<Team> findAllTeams() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Team team = null;
		List<Team> teamList = new ArrayList<Team>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_TEAMS);
			while (rs.next()) {
				team = extractTeam(rs);
				teamList.add(team);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_FIND_ALL_TEAMS, e);
			throw new DBException(Messages.ERR_CANT_FIND_ALL_TEAMS, e);
		} finally {
			close(con, stmt, rs);
		}
		return teamList;
	}

	public void insertIntoTeamStaff(int lastInsertId, int[] staffIdInt)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_INTO_TEAM_STAFF);
			pstmt.setInt(1, lastInsertId);
			for (int i = 0; i < staffIdInt.length; i++) {
				pstmt.setInt(2, staffIdInt[i]);
				pstmt.execute();
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_INSERT_TEAM_STAFF, e);
			throw new DBException(Messages.ERR_CANT_INSERT_TEAM_STAFF, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public List<TeamStaffBean> findAllTeamStaffBean() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		TeamStaffBean tsb = null;
		List<TeamStaffBean> tsbList = new ArrayList<TeamStaffBean>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_TEAM_STAFF_BEAN);
			while (rs.next()) {
				tsb = extractTeamStaffBean(rs);
				tsbList.add(tsb);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_TEAM_STAFF_BEAN, e);
			throw new DBException(Messages.ERR_CANT_GET_TEAM_STAFF_BEAN, e);
		} finally {
			close(con, stmt, rs);
		}
		return tsbList;
	}

	public List<Staff> findStaffersInTeamByTeamId(int teamId)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff staff = null;
		List<Staff> staffList = new ArrayList<Staff>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_STAFFERS_IN_TEAM_BY_TEAM_ID);
			pstmt.setInt(1, teamId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				staff = extractStaff(rs);
				staffList.add(staff);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_STAFFERS_FROM_TEAM, e);
			throw new DBException(Messages.ERR_CANT_GET_STAFFERS_FROM_TEAM, e);
		} finally {
			close(con, pstmt, rs);
		}
		return staffList;
	}

	public List<Integer> findStaffersInTeamByFlightId(long flightId)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int staffId = -1;
		List<Integer> staffIdList = new ArrayList<Integer>();
		try {
			con = getConnection();
			pstmt = con
					.prepareStatement(SQL_FIND_STAFFERS_IN_TEAM_BY_FLIGHT_ID);
			pstmt.setLong(1, flightId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				staffId = rs.getInt(Fields.TEAM_STAFF_STAFF_ID);
				staffIdList.add(staffId);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_STAFFERS_FROM_TEAM, e);
			throw new DBException(Messages.ERR_CANT_GET_STAFFERS_FROM_TEAM, e);
		} finally {
			close(con, pstmt, rs);
		}
		return staffIdList;
	}

	public List<Flight> findCurrentFlight() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Flight flight = null;
		List<Flight> currentFlight = new ArrayList<Flight>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_NOT_C_AND_L_FLIGHTS);
			while (rs.next()) {
				flight = extractFlight(rs);
				currentFlight.add(flight);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_CURRENT_FLIGHTS, e);
			throw new DBException(Messages.ERR_CANT_GET_CURRENT_FLIGHTS, e);
		} finally {
			close(con, stmt, rs);
		}
		return currentFlight;
	}

	public void updateFlightStatus(int statusId, int flightId)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_STATUS);
			pstmt.setInt(1, statusId);
			pstmt.setInt(2, flightId);
			pstmt.execute();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_UPDATE_FLIGHT_STATUS, e);
			throw new DBException(Messages.ERR_CANT_UPDATE_FLIGHT_STATUS, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void updateFlight(String number, String from, String to,
			String date, String price, String flightId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT);
			pstmt.setString(1, number);
			pstmt.setString(2, from);
			pstmt.setString(3, to);
			pstmt.setString(4, date);
			pstmt.setString(5, price);
			pstmt.setString(6, flightId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_UPDATE_FLIGHT, e);
			throw new DBException(Messages.ERR_CANT_UPDATE_FLIGHT, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public int checkTeamByFlightId(long flightId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_TEAM_BY_FLIGHT_ID);
			pstmt.setLong(1, flightId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_STAFFERS_FROM_TEAM, e);
			throw new DBException(Messages.ERR_CANT_GET_STAFFERS_FROM_TEAM, e);
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	}

	public void insertNewFlight(String number, String from, String to,
			String date, String price) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_INTO_FLIGHT);
			pstmt.setString(1, number);
			pstmt.setString(2, from);
			pstmt.setString(3, to);
			pstmt.setString(4, date);
			pstmt.setString(5, price);
			pstmt.execute();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_INSERT_FLIGHT, e);
			throw new DBException(Messages.ERR_CANT_INSERT_FLIGHT, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void deleteFlightById(long flightIdLong) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_FLIGHT_BY_ID);
			pstmt.setLong(1, flightIdLong);
			pstmt.execute();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_DELETE_FLIGHT, e);
			throw new DBException(Messages.ERR_CANT_DELETE_FLIGHT, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public int findFlightStatusById(int flightIdInt) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flightStatusId = -1;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FLIGHT_STATUS_BY_ID);
			pstmt.setInt(1, flightIdInt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flightStatusId = rs.getInt(Fields.FLIGHT_STATUS_ID);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FLIGHT_STATUS_ID, e);
			throw new DBException(Messages.ERR_CANT_GET_FLIGHT_STATUS_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return flightStatusId;
	}

	public List<Staff> findAllStaff() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Staff staff = null;
		List<Staff> staffList = new ArrayList<Staff>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_STAFF);
			while (rs.next()) {
				staff = extractStaff(rs);
				staffList.add(staff);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_STAFF, e);
			throw new DBException(Messages.ERR_CANT_GET_STAFF, e);
		} finally {
			close(con, stmt, rs);
		}
		return staffList;
	}

	public Staff findStaffById(int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff staff = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_STAFF_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				staff = extractStaff(rs);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_STAFF_BY_ID, e);
			throw new DBException(Messages.ERR_CANT_GET_STAFF_BY_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return staff;
	}

	public void updateStaff(String first_name, String last_name,
			String profession, String staffId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_STAFF);
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			pstmt.setString(3, profession);
			pstmt.setString(4, staffId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_UPDATE_STAFF, e);
			throw new DBException(Messages.ERR_CANT_UPDATE_STAFF, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public void deleteStaffById(String staffId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_STAFF_BY_ID);
			pstmt.setString(1, staffId);
			pstmt.execute();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_DELETE_STAFF, e);
			throw new DBException(Messages.ERR_CANT_DELETE_STAFF, e);
		} finally {
			close(pstmt);
			close(con);
		}

	}

	public void insertNewStaff(String first_name, String last_name,
			String profession) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_INTO_STAFF);
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			pstmt.setString(3, profession);
			pstmt.execute();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_INSERT_STAFF, e);
			throw new DBException(Messages.ERR_CANT_INSERT_STAFF, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public List<TeamFlightBean> findAllTeamFlightBean() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		TeamFlightBean tfb = null;
		List<TeamFlightBean> tfbList = new ArrayList<TeamFlightBean>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_TEAM_FLIGHT_BEAN);
			while (rs.next()) {
				tfb = extractTeamFlightBean(rs);
				tfbList.add(tfb);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_TEAM_FLIGHT_BEAN, e);
			throw new DBException(Messages.ERR_CANT_GET_TEAM_FLIGHT_BEAN, e);
		} finally {
			close(con, stmt, rs);
		}
		return tfbList;
	}

	public void insertNewApplication(String problemDescription, int teamId,
			String email) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			if (teamId == -1) {
				pstmt = con
						.prepareStatement(SQL_INSERT_INTO_APPLICATION_WITHOUT_TEAM);
			} else {
				pstmt = con
						.prepareStatement(SQL_INSERT_INTO_APPLICATION_WITH_TEAM);
				pstmt.setInt(3, teamId);
			}
			pstmt.setString(1, problemDescription);
			pstmt.setString(2, email);
			pstmt.execute();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_INSERT_APPLICATION, e);
			throw new DBException(Messages.ERR_CANT_INSERT_APPLICATION, e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public List<Application> findUnresolvedApplication() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Application application = null;
		List<Application> applicationList = new ArrayList<Application>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_UNRESOLVED_APPLICATION);
			while (rs.next()) {
				application = extractApplication(rs);
				applicationList.add(application);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_APPLICATION, e);
			throw new DBException(Messages.ERR_CANT_GET_APPLICATION, e);
		} finally {
			close(con, stmt, rs);
		}
		return applicationList;
	}

	public void updateApplicationStatus(String applicationId,
			String applicationStatus, long administratorId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_APPLICATION);
			pstmt.setLong(1, administratorId);
			pstmt.setString(2, applicationStatus);
			pstmt.setString(3, applicationId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_UPDATE_APPLICATION_STATUS, e);
			throw new DBException(Messages.ERR_CANT_UPDATE_APPLICATION_STATUS,
					e);
		} finally {
			close(pstmt);
			close(con);
		}
	}

	public Application findApplicationById(String applicationId)
			throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Application application = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_APPLICATION_BY_ID);
			pstmt.setString(1, applicationId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				application = extractApplication(rs);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_FLIGHT_BY_ID, e);
			throw new DBException(Messages.ERR_CANT_GET_FLIGHT_BY_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return application;
	}

	public List<Flight> findTopFiveFlights() throws DBException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Flight> flights = new ArrayList<Flight>();
		Flight flight = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_TOP_5_FLIGHTS);
			while (rs.next()) {
				flight = extractFlight(rs);
				flights.add(flight);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANT_GET_TOP_5_FLIGHT, e);
			throw new DBException(Messages.ERR_CANT_GET_TOP_5_FLIGHT, e);
		} finally {
			close(con, stmt, rs);
		}
		return flights;
	}
	
	private Application extractApplication(ResultSet rs) throws SQLException {
		Application application = new Application();
		application.setId(rs.getLong(Fields.ENTITY_ID));
		application.setProblem_description(rs
				.getString(Fields.APPLICATION_PROBLEM_DESCRIPTION));
		int team_id = 0;
		team_id = rs.getInt(Fields.APPLICATION_TEAM_ID);
		if (team_id != 0) {
			application.setTeam_id(team_id);
		}
		application.setEmail(rs.getString(Fields.APPLICATION_EMAIL));
		return application;
	}

	private TeamFlightBean extractTeamFlightBean(ResultSet rs)
			throws SQLException {
		TeamFlightBean tfb = new TeamFlightBean();
		tfb.setTeamId(rs.getInt(Fields.ENTITY_ID));
		tfb.setFlightsNumber(rs.getString(Fields.FLIGHT_NUMBER));
		tfb.setFrom(rs.getString(Fields.FLIGHT_FROM));
		tfb.setTo(rs.getString(Fields.FLIGHT_TO));
		tfb.setDateOfDeparture(rs.getString(Fields.FLIGHT_DATE_OF_DEPARTURE));
		return tfb;
	}

	private TeamStaffBean extractTeamStaffBean(ResultSet rs)
			throws SQLException {
		TeamStaffBean tsb = new TeamStaffBean();
		tsb.setTeamId(rs.getInt(Fields.ENTITY_ID));
		tsb.setFlightsNumber(rs.getString(Fields.FLIGHT_NUMBER));
		tsb.setFrom(rs.getString(Fields.FLIGHT_FROM));
		tsb.setTo(rs.getString(Fields.FLIGHT_TO));
		tsb.setDateOfDeparture(rs.getString(Fields.FLIGHT_DATE_OF_DEPARTURE));
		tsb.setDispatcherLastName(rs.getString(Fields.USER_LAST_NAME));
		return tsb;
	}

	private Team extractTeam(ResultSet rs) throws SQLException {
		Team team = new Team();
		team.setId(rs.getLong(Fields.ENTITY_ID));
		team.setUser_id(rs.getInt(Fields.TEAM_USERS_ID));
		team.setFlight_id(rs.getInt(Fields.TEAM_FLIGHTS_ID));
		return null;
	}

	private Staff extractStaff(ResultSet rs) throws SQLException {
		Staff staff = new Staff();
		staff.setId(rs.getLong(Fields.ENTITY_ID));
		staff.setFirst_name(rs.getString(Fields.USER_FIRST_NAME));
		staff.setLast_name(rs.getString(Fields.USER_LAST_NAME));
		staff.setIs_free(rs.getBoolean(Fields.STAFF_IS_FREE));
		staff.setProfession_id(rs.getInt(Fields.STAFF_PROFESSION_ID));
		return staff;
	}

	private Flight extractFlight(ResultSet rs) throws SQLException {
		Flight flight = new Flight();
		flight.setId(rs.getLong(Fields.ENTITY_ID));
		flight.setNumber(rs.getString(Fields.FLIGHT_NUMBER));
		flight.setFrom(rs.getString(Fields.FLIGHT_FROM));
		flight.setTo(rs.getString(Fields.FLIGHT_TO));
		flight.setDate_of_departure(rs
				.getString(Fields.FLIGHT_DATE_OF_DEPARTURE));
		flight.setPrice(rs.getInt(Fields.FLIGHT_PRICE));
		flight.setFlight_status_id(rs.getInt(Fields.FLIGHT_STATUS_ID));
		return flight;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setLast_name(rs.getString(Fields.USER_LAST_NAME));
		user.setFirst_name(rs.getString(Fields.USER_FIRST_NAME));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setEmail_password(rs.getString(Fields.USER_EMAIL_PASSWORD));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		return user;
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANT_CLOSE_RESULT_SET, e);
			}
		}
	}

	private void close(Statement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANT_CLOSE_STATEMENT, e);
			}
		}
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOG.error(Messages.ERR_CANT_CLOSE_CONNECTION, e);
			}
		}
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				LOG.error("Can't rollback transaction", e);
			}
		}
	}
}
