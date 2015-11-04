package ua.nure.sharov.Airlines.web.command.adminCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;

/**
 * Command to forward on page to add flight
 * @author Max
 *
 */
public class AdministratorAddFlightPageCommand extends Command {


	private static final long serialVersionUID = 5835332826429505337L;
	
	Logger LOG = Logger.getLogger(AdministratorAddFlightPageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);
		String forward = Path.PAGE_ADMIN_ADD_FLIGHT;
		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}