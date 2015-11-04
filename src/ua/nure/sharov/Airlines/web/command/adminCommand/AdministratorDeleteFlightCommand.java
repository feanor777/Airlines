package ua.nure.sharov.Airlines.web.command.adminCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Delete flight command
 * @author Max
 *
 */
public class AdministratorDeleteFlightCommand extends Command {

	private static final long serialVersionUID = -6375791348229340729L;
	private static final Logger LOG = Logger
			.getLogger(AdministratorDeleteFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.COMMAND_ADMIN_SHOW_FLIGHTS_REDIRECT;
		DBManager manager = DBManager.getInstance();

		String flightId = request.getParameter("flightId");
		int flightIdInt = Integer.valueOf(flightId);
		int flightStatusId = manager.findFlightStatusById(flightIdInt);

		if (flightStatusId == 0 || flightStatusId == 2) {
			throw new ApplicationException(Messages.ERR_CANT_DELETE_FLIGHT);
		}

		if (manager.checkTeamByFlightId(flightIdInt) == 1) {
			List<Integer> staffIdList = manager
					.findStaffersInTeamByFlightId(flightIdInt);
			manager.updateStaffersStatusToFree(staffIdList);
		}
		manager.deleteFlightById(flightIdInt);

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}
