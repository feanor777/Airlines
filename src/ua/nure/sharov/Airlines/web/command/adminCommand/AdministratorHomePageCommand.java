package ua.nure.sharov.Airlines.web.command.adminCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherHomePageCommand;
/**
 * Command to forward to administrator home page 
 * @author Max
 *
 */
public class AdministratorHomePageCommand extends Command {
	private static final long serialVersionUID = -6097287781548191011L;
	private static final Logger LOG = Logger
			.getLogger(DispatcherHomePageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_ADMIN_HOME;
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}