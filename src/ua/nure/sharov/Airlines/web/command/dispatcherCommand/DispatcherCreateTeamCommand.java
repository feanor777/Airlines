package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.User;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.DBException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to create team
 * @author Max
 *
 */
public class DispatcherCreateTeamCommand extends Command {

	private static final long serialVersionUID = -2525659306282515983L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherCreateTeamCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);

		HttpSession session = request.getSession();
		String[] staffId = request.getParameterValues("staffId");
		String flightId = request.getParameter("flightId");
		User user = (User) session.getAttribute("user");
		long userId = user.getId();

		if (staffId == null || flightId == null) {
			LOG.warn(Messages.ERR_CANT_CREATE_TEAM);
			throw new ApplicationException(Messages.ERR_CANT_CREATE_TEAM);
		}

		if (!check(staffId)) {
			LOG.warn(Messages.ERR_CANT_CREATE_TEAM);
			throw new ApplicationException(Messages.ERR_CANT_CREATE_TEAM);
		}

		DBManager manager = DBManager.getInstance();
		int[] staffIdInt = convert(staffId);
		int flightIdInt = Integer.valueOf(flightId);
		int lastInsertId = manager.insertNewTeam(flightIdInt, userId);

		manager.updateStaffersStatusToBusy(staffIdInt);
		manager.insertIntoTeamStaff(lastInsertId, staffIdInt);

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return Path.COMMAND_DISPATCHER_FIND_TEAM_REDIRECT;
	}

	private boolean check(String[] staffId) throws DBException {
		if (staffId.length != 7) {
			return false;
		}
		int[] staffIdInt = convert(staffId);
		Map<Integer, Integer> countStaff = DBManager.getInstance()
				.findCountOfStaffers(staffIdInt);
		if (!check(countStaff)) {
			return false;
		}
		return true;
	}

	private boolean check(Map<Integer, Integer> countStaff) {
		if (countStaff.get(0) != 2 || countStaff.get(1) != 1
				|| countStaff.get(2) != 1 || countStaff.get(3) != 3) {
			return false;
		}
		return true;
	}

	private int[] convert(String[] staffId) {
		int[] staffIdInt = new int[staffId.length];
		for (int i = 0; i < staffIdInt.length; i++) {
			staffIdInt[i] = Integer.parseInt(staffId[i]);
		}
		return staffIdInt;
	}
}