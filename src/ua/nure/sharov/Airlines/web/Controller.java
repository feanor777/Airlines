package ua.nure.sharov.Airlines.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.web.command.Command;
import ua.nure.sharov.Airlines.web.command.CommandContainer;
/**
 * Main servlet controller.
 * @author Max
 *
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Method returns page to forward or redirect request and response to the page in dependence of the command
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String comandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + comandName);

		Command command = CommandContainer.getCommand(comandName);
		String forward = Path.PAGE_ERROR_PAGE;

		try {
			forward = command.execute(request, response);
		} catch (ApplicationException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		if (!forward.equals(Path.PAGE_ERROR_PAGE)) {
			String url = response.encodeRedirectURL(forward);
			switch (comandName) {
			case "login":
			case "logout":
			case "createTeam":
			case "setFlightStatus":
			case "addFlight":
			case "deleteFlight":
			case "editFlight":
			case "addStaff":
			case "editStaff":
			case "deleteStaff":
			case "createApplication":
			case "resolveApplication":
				 response.sendRedirect(url);
				return;
			}
		}
		request.getRequestDispatcher(forward).forward(request, response);
	}
}