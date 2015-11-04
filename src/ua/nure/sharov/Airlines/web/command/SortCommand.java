package ua.nure.sharov.Airlines.web.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.entity.Flight;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;

/**
 * Sort command( by price and number of flight)
 * @author Max
 *
 */
public class SortCommand extends Command {

	private static final long serialVersionUID = -3032767302173634116L;

	private static final Logger LOG = Logger.getLogger(SortCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.debug(Messages.LOG_COMMAND_STARTS);
		String order = request.getParameter("order");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Flight> flights = (List<Flight>) session.getAttribute("flights");
		switch (order) {
		case "number":
			Collections.sort(flights, new NumberComparator());
			break;
		case "price":
			Collections.sort(flights, new PriceComparator());
			break;
		}
		session.setAttribute("flights", flights);

		LOG.debug(Messages.LOG_COMMAND_FINISHED);
		return Path.PAGE_FLIGHTS;
	}

	private class NumberComparator implements Comparator<Flight> {
		@Override
		public int compare(Flight o1, Flight o2) {
			String n1 = o1.getNumber();
			String n2 = o2.getNumber();
			return n1.compareTo(n2);
		}
	}

	private class PriceComparator implements Comparator<Flight> {

		@Override
		public int compare(Flight o1, Flight o2) {
			Integer p1 = o1.getPrice();
			Integer p2 = o2.getPrice();
			return p1.compareTo(p2);
		}

	}
}
