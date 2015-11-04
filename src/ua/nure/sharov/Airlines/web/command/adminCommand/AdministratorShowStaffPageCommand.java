package ua.nure.sharov.Airlines.web.command.adminCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Staff;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;

/**
 * Command to forward to show staff page
 * 
 * @author Max
 *
 */
public class AdministratorShowStaffPageCommand extends Command {

	private static final long serialVersionUID = 4278860448716141973L;
	private static final Logger LOG = Logger
			.getLogger(AdministratorShowStaffPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_ADMIN_SHOW_STAFF;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Staff> changeStaffList = manager.findFreeStaffers();
		session.setAttribute("changeStaffList", changeStaffList);
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}