package ua.nure.sharov.Airlines.web.command.adminCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Application;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to forward on page to show applications
 * @author Max
 *
 */
public class AdministratorApplicationPageCommand extends Command {

	private static final long serialVersionUID = -8815881403993808036L;

	Logger LOG = Logger.getLogger(AdministratorApplicationPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_ADMIN_SHOW_APPLICATION;
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		List<Application> applicationList = manager.findUnresolvedApplication();
		session.setAttribute("applicationList", applicationList);
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

}
