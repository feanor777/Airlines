package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Flight;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to show flights
 * @author Max
 *
 */
public class DispatcherShowFlightCommand extends Command {

	private static final long serialVersionUID = -3694931381504620588L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherShowFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_DISPATCHER_SHOW_CURRENT_FLIGHTS;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Flight> currentFlightsList = manager.findCurrentFlight();
		session.setAttribute("currentFlightsList", currentFlightsList);
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
