package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to forward to the dispatcher home page
 * @author Max
 *
 */
public class DispatcherHomePageCommand extends Command {

	private static final long serialVersionUID = -9035453139738266881L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherHomePageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_DISPATCHER_HOME;
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}
