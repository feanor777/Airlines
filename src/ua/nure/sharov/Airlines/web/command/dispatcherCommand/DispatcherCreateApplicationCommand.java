package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.User;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to show page with application
 * @author Max
 *
 */
public class DispatcherCreateApplicationCommand extends Command {

	private static final long serialVersionUID = 6176654443752740170L;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		String forward = Path.COMMAND_DISPATCHER_CREATE_APPLICATION_PAGE_REDIRECT;
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		String problemDescription = request.getParameter("description");
		String teamId = request.getParameter("teamId");
		User user = (User) session.getAttribute("user");
		
		byte[] bytes = problemDescription.getBytes(StandardCharsets.ISO_8859_1);
		problemDescription = new String(bytes, StandardCharsets.UTF_8);
		
		System.out.println(problemDescription);
		System.out.println(request.getCharacterEncoding());
		String email = user.getEmail();
		int teamIdInt = Integer.valueOf(teamId);
		manager.insertNewApplication(problemDescription, teamIdInt, email);
		return forward;
	}

}
