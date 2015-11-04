package ua.nure.sharov.Airlines.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
/**
 * No command
 * @author Max
 *
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = -3955110266779753114L;
	
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.debug(Messages.LOG_COMMAND_STARTS);
		String error = "No such command exists";
		request.setAttribute("errorMessage", error);
		LOG.debug(Messages.LOG_COMMAND_FINISHED);
		return Path.PAGE_ERROR_PAGE;
	}
}