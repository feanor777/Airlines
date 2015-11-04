package ua.nure.sharov.Airlines.web.command.adminCommand;

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
 * Command to forward to show flight page
 */
public class AdministratorShowFlightsPageCommand extends Command{

	private static final long serialVersionUID = -646165181222818787L;
	
	private static final Logger LOG = Logger.getLogger(AdministratorShowFlightsPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_ADMIN_SHOW_FLIGHTS;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Flight> changeFlightsList = manager.findCurrentFlight();
		session.setAttribute("changeFlightsList", changeFlightsList);
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
