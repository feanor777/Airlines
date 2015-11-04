package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Flight;
import ua.nure.sharov.Airlines.db.entity.Staff;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to forward to find team page
 * @author Max
 *
 */
public class DispatcherFindTeamPageCommand extends Command {

	private static final long serialVersionUID = -2525659306282515983L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherFindTeamPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_DISPATCHER_FIND_TEAM;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Staff> freeStaffers = manager.findFreeStaffers();
		List<Flight> flightsWithoutTeam = manager.findFlightsWithoutTeam();
		session.setAttribute("freeStaffers", freeStaffers);
		session.setAttribute("flightsWithoutTeam", flightsWithoutTeam);
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
