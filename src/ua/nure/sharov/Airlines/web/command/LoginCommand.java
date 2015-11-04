package ua.nure.sharov.Airlines.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.Role;
import ua.nure.sharov.Airlines.db.entity.User;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;

/**
 * Login command
 * @author Max
 *
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -8396307297189148569L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.debug(Messages.LOG_COMMAND_STARTS);
		HttpSession session = request.getSession();

		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (login == null || password == null || login.isEmpty()
				|| password.isEmpty()) {
			LOG.warn("Login or password is empty");
			throw new ApplicationException(
					"Login or password can not be empty!");
		}

		User user = manager.findUserByLogin(login);

		if (user == null || !password.equals(user.getPassword())) {
			LOG.warn("Can not find user with such login or password");
			throw new ApplicationException("Login or password is not correct");
		}

		LOG.trace("User " + user + " login in the system");

		Role userRole = Role.getRole(user);
		LOG.trace("User role ==> " + userRole);

		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Role.ADMINISTRATOR) {
			forward = Path.COMMAND_ADMIN_PAGE;
		}
		
		if(userRole == Role.DISPATCHER) {
			forward = Path.COMMAND_DISPATCHER_PAGE;
		}
		
		session.setAttribute("user", user);
		session.setAttribute("userRole", userRole);
		
		LOG.debug(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}