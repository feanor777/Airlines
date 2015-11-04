package ua.nure.sharov.Airlines.web.command.adminCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;

/**
 * Delete staff command
 * 
 * @author Max
 *
 */
public class AdministratorDeleteStaffCommand extends Command {

	private static final long serialVersionUID = 6966815079662432039L;
	private static final Logger LOG = Logger
			.getLogger(AdministratorDeleteStaffCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.COMMAND_ADMIN_SHOW_STAFF_REDIRECT;
		DBManager manager = DBManager.getInstance();

		String[] staffId = request.getParameterValues("checks");
		if (staffId == null) {
			throw new ApplicationException(Messages.ERR_CANT_DELETE_STAFF);
		} else if (staffId.length > 0) {
			for (int i = 0; i < staffId.length; i++) {
				manager.deleteStaffById(staffId[i]);
			}
		}

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}
