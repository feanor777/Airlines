package ua.nure.sharov.Airlines.web.command.adminCommand;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Add flight by number, from, to, date of departure, price
 * @author Max
 *
 */
public class AdministratorAddFlightCommand extends Command {

	private static final long serialVersionUID = 2020015164277085079L;
	
	private static final Logger LOG = Logger.getLogger(AdministratorAddFlightPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.COMMAND_ADMIN_SHOW_FLIGHTS_REDIRECT;
		DBManager manager = DBManager.getInstance();
		
		String number = request.getParameter("number");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");
		String price = request.getParameter("price");
		
		byte[] bytes = from.getBytes(StandardCharsets.ISO_8859_1);
		from = new String(bytes, StandardCharsets.UTF_8);
		bytes = to.getBytes(StandardCharsets.ISO_8859_1);
		to = new String(bytes, StandardCharsets.UTF_8);
		
		manager.insertNewFlight(number, from, to, date, price);
		
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
