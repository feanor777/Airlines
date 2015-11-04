package ua.nure.sharov.Airlines.web.command.adminCommand;

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
 * Command to edit staff
 * @author Max
 *
 */
public class AdministratorEditStaffCommand extends Command {

	private static final long serialVersionUID = -5093031525715398020L;
	
	private static final Logger LOG = Logger.getLogger(AdministratorEditStaffCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		String forward = Path.COMMAND_ADMIN_SHOW_STAFF_REDIRECT;
		
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String profession = request.getParameter("profession");
		String staffId = request.getParameter("staffId");
		
		Staff staff = (Staff) session.getAttribute("changeStaff");
		
		if(!staff.isIs_free()) {
			throw new ApplicationException(Messages.ERR_CANT_UPDATE_NOT_FREE_STAFF);
		}
		
		manager.updateStaff(first_name, last_name, profession, staffId);
		
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}