package ua.nure.sharov.Airlines.web.command;

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

/**
 * Find flights by flight number
 * @author Max
 *
 */
public class FindFlightNumberCommand extends Command {

	private static final long serialVersionUID = 162001011638847534L;

	private static final Logger LOG = Logger
			.getLogger(FindFlightNumberCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		String number = request.getParameter("number");
		if (number == null || number.isEmpty()) {
			LOG.info(Messages.ERR_EMPTY_FIELD);
			throw new ApplicationException(Messages.ERR_EMPTY_FIELD);
		}
		Integer n = Integer.valueOf(number);
		List<Flight> flightList = manager.findFlightsByNumber(n);
		session.setAttribute("flightsNumber", flightList);
		String forward = Path.PAGE_FLIGHTS_NUMBER;
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
