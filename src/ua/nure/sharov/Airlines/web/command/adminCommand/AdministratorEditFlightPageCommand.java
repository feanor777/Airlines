package ua.nure.sharov.Airlines.web.command.adminCommand;

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
 * Command to forward to edit page
 * @author Max
 *
 */
public class AdministratorEditFlightPageCommand extends Command {

	private static final long serialVersionUID = 6307490878041934870L;
	
	private static final Logger LOG = Logger.getLogger(AdministratorEditFlightPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		String forward = Path.PAGE_ADMIN_EDIT_FLIGHT;
		
		String flightId = request.getParameter("flightId");
		int flightIdInt = Integer.valueOf(flightId);
		Flight changeFlight = manager.findFlightById(flightIdInt);
		
		session.setAttribute("changeFlight", changeFlight);
		
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}