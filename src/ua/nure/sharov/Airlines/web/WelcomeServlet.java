package ua.nure.sharov.Airlines.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Flight;
import ua.nure.sharov.Airlines.exception.DBException;
import ua.nure.sharov.Airlines.exception.Messages;
/**
 * Welcome servlet(welcome page)
 * @author Max
 *
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {

	Logger LOG = Logger.getLogger(WelcomeServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		List<Flight> topFive = null;
		try {
			DBManager manager = DBManager.getInstance();
			topFive = manager.findTopFiveFlights();
		} catch (DBException e) {
			LOG.warn(Messages.ERR_CANT_GET_TOP_5_FLIGHT);
			request.setAttribute("errorMessage", e.getMessage());
		}

		HttpSession session = request.getSession();
		session.setAttribute("topFive", topFive);

		String comandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + comandName);

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		request.getRequestDispatcher(Path.PAGE_WELCOME).forward(request,
				response);
	}

}
