package ua.nure.sharov.Airlines.web.command.adminCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to edit flight
 * @author Max
 *
 */
public class AdministratorEditFlightCommand extends Command {

	private static final long serialVersionUID = -5093031525715398020L;
	
	private static final Logger LOG = Logger.getLogger(AdministratorEditFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		DBManager manager = DBManager.getInstance();
		String forward = Path.COMMAND_ADMIN_SHOW_FLIGHTS_REDIRECT;
		
		String number = request.getParameter("number");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");
		String price = request.getParameter("price");
		String flightId = request.getParameter("flightId");
		
		manager.updateFlight(number, from, to, date, price, flightId);
		
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}