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
 * Find flight by parameters from, to, date of departure
 * @author Max
 *
 */
public class FindFlightsCommand extends Command {

	private static final long serialVersionUID = -128130193535904531L;

	private static final Logger LOG = Logger
			.getLogger(FindFlightsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);

		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");

		if (from == null || to == null || date == null || from.isEmpty()
				|| to.isEmpty() || date.isEmpty()) {
			LOG.info(Messages.ERR_EMPTY_FIELD);
			throw new ApplicationException(Messages.ERR_EMPTY_FIELD);
		}
		
		List<Flight> flightsList = manager.findFlightsByFromToDate(from, to, date);
		session.setAttribute("flights", flightsList);
		String forward = Path.PAGE_FLIGHTS;
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
