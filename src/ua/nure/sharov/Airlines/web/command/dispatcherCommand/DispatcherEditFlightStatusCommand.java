package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

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
 * Command to edit flight status
 * @author Max
 *
 */
public class DispatcherEditFlightStatusCommand extends Command {

	private static final long serialVersionUID = -3707941040507780654L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherEditFlightStatusCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.COMMAND_DISPATCHER_SHOW_FLIGHTS_REDIRECT;
		DBManager manager = DBManager.getInstance();
		String flightStatusId = request.getParameter("flightStatusId");
		String flightId = request.getParameter("flightId");

		int statusIdInt = Integer.valueOf(flightStatusId);
		int flightIdInt = Integer.valueOf(flightId);
		
		manager.updateFlightStatus(statusIdInt, flightIdInt);
		if(statusIdInt == 1 || statusIdInt == 3) {
			List<Integer> staffIdList = manager.findStaffersInTeamByFlightId(flightIdInt);
			manager.updateStaffersStatusToFree(staffIdList);
		}
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}