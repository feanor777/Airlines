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
 * Command to forward to edit staff page
 * @author Max
 *
 */
public class AdministratorEditStaffPageCommand extends Command {

	private static final long serialVersionUID = 6307490878041934870L;

	private static final Logger LOG = Logger
			.getLogger(AdministratorEditStaffPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		String forward = Path.PAGE_ADMIN_EDIT_STAFF;

		String[] staffId = request.getParameterValues("checks");
		if (staffId == null) {
			session.setAttribute("changeStaff", null);
		} else if (staffId.length > 1) {
			throw new ApplicationException(Messages.ERR_CANT_EDIT_STAFF);
		}

		else {
			int staffIdInt = Integer.valueOf(staffId[0]);
			Staff changeStaff = manager.findStaffById(staffIdInt);
			session.setAttribute("changeStaff", changeStaff);
		}

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}