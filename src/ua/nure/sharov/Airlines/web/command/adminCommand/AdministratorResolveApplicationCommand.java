package ua.nure.sharov.Airlines.web.command.adminCommand;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.ApplicationStatus;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Application;
import ua.nure.sharov.Airlines.db.entity.User;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
import ua.nure.sharov.Airlines.web.email.Sender;
/**
 * Command to resolve application
 * @author Max
 *
 */
public class AdministratorResolveApplicationCommand extends Command {

	private static final long serialVersionUID = 135937234674935515L;

	Logger LOG = Logger.getLogger(AdministratorResolveApplicationCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		String forward = Path.COMMAND_ADMIN_SHOW_APPLICATION_REDIRECT;

		String applicationStatus = request.getParameter("applicationStatus");
		String applicationId = request.getParameter("choose");
		String solution = request.getParameter("solution");
		Application application = manager.findApplicationById(applicationId);
		String toEmail = application.getEmail();
		
		byte[] bytes = solution.getBytes(StandardCharsets.ISO_8859_1);
		solution = new String(bytes, StandardCharsets.UTF_8);

		int applicationStatusInt = Integer.valueOf(applicationStatus);
		String appStatus = ApplicationStatus
				.getApplicationStatus(applicationStatusInt);

		User user = (User) session.getAttribute("user");
		long administratorId = user.getId();

		String subject = "application id --> " + applicationId + " status --> "
				+ appStatus;
		String fromEmail = user.getEmail();
		String fromPassword = user.getEmail_password();
		
		Sender tlsSender = new Sender(fromEmail, fromPassword);
		tlsSender.send(subject, solution, toEmail);

		manager.updateApplicationStatus(applicationId, applicationStatus,
				administratorId);
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
