package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.bean.TeamFlightBean;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to forward to the application page
 * @author Max
 *
 */
public class DispatcherCreateApplicationPageCommand extends Command {
	private static final long serialVersionUID = -3707155407854945973L;
	Logger LOG = Logger.getLogger(DispatcherCreateApplicationPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();	
		List<TeamFlightBean> tfbList = manager.findAllTeamFlightBean();
		session.setAttribute("tfbList", tfbList);
		String forward = Path.PAGE_DISPATCHER_CREATE_APPLICATION;
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}