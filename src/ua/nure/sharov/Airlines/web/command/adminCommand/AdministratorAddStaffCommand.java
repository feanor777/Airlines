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
 * Add staff by first name, last name, profession
 * @author Max
 *
 */
public class AdministratorAddStaffCommand extends Command {

	private static final long serialVersionUID = 2020015164277085079L;
	
	private static final Logger LOG = Logger.getLogger(AdministratorAddFlightPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.COMMAND_ADMIN_SHOW_STAFF_REDIRECT;
		DBManager manager = DBManager.getInstance();
		
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String profession = request.getParameter("profession");
		
		byte[] bytes = first_name.getBytes(StandardCharsets.ISO_8859_1);
		first_name = new String(bytes, StandardCharsets.UTF_8);
		bytes = last_name.getBytes(StandardCharsets.ISO_8859_1);
		last_name = new String(bytes, StandardCharsets.UTF_8);
		
		manager.insertNewStaff(first_name, last_name, profession);
		
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
