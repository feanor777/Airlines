package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.entity.Flight;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.DBException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to forward to edit flight status page
 * @author Max
 *
 */
public class DispatcherEditFlightStatusPageCommand extends Command {

	private static final long serialVersionUID = 1700902814801259580L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherEditFlightStatusPageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {
		LOG.trace(Messages.LOG_COMMAND_STARTS);

		String forward = Path.PAGE_DISPATCHER_EDIT_FLIGHT_STATUS;
		HttpSession session = request.getSession();

		String flightId = request.getParameter("flightId");
		int flightIdInt = convert(flightId);
		DBManager manager = DBManager.getInstance();

		Flight flightEditStatus = manager.findFlightById(flightIdInt);
		List<Integer> flightStatusList = getAvailableStatus(flightEditStatus);

		session.setAttribute("flightEditStatus", flightEditStatus);
		session.setAttribute("flightStatusList", flightStatusList);

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}

	private List<Integer> getAvailableStatus(Flight flightEditStatus) throws DBException {
		DBManager manager = DBManager.getInstance();
		
		int flightStatusId = flightEditStatus.getFlight_status_id();
		LocalDate dateOfDeparture = flightEditStatus.getDate_of_departure();
		LocalDate currentDate = new LocalDate();
		long flightId = flightEditStatus.getId();
		List<Integer> availableStatusList = new ArrayList<Integer>();
		switch (flightStatusId) {
		case 0:
			availableStatusList.add(2);
			availableStatusList.add(3);
			break;
		case 2:
			availableStatusList.add(3);
			break;
		case 4:
			if (currentDate.isEqual(dateOfDeparture) && manager.checkTeamByFlightId(flightId) != 0) {
				availableStatusList.add(0);
			}
			availableStatusList.add(1);
			break;
		}
		return availableStatusList;
	}

	private int convert(String flightId) throws ApplicationException {
		if (flightId == null || flightId.length() == 0) {
			throw new ApplicationException(Messages.ERR_INCORRECT_FIELD);
		}
		int flightIdInt = -1;
		try {
			flightIdInt = Integer.valueOf(flightId);
		} catch (NumberFormatException e) {
			LOG.error(Messages.ERR_INCORRECT_FIELD, e);
			throw new ApplicationException(Messages.ERR_INCORRECT_FIELD, e);
		}
		return flightIdInt;
	}

}
