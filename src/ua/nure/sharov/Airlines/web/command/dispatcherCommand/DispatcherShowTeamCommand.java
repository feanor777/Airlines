package ua.nure.sharov.Airlines.web.command.dispatcherCommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.Path;
import ua.nure.sharov.Airlines.db.DBManager;
import ua.nure.sharov.Airlines.db.bean.TeamStaffBean;
import ua.nure.sharov.Airlines.db.entity.Staff;
import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
import ua.nure.sharov.Airlines.web.command.Command;
/**
 * Command to show teams
 * @author Max
 *
 */
public class DispatcherShowTeamCommand extends Command {

	private static final long serialVersionUID = 8815844524469658808L;

	private static final Logger LOG = Logger
			.getLogger(DispatcherShowTeamCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException {

		LOG.trace(Messages.LOG_COMMAND_STARTS);

		String forward = Path.PAGE_DISPATCHER_SHOW_TEAM;

		HttpSession session = request.getSession();

		DBManager manager = DBManager.getInstance();

		List<TeamStaffBean> teamStaffBeanList = manager.findAllTeamStaffBean();

		for (TeamStaffBean teamStaffBean : teamStaffBeanList) {
			int teamId = teamStaffBean.getTeamId();
			List<Staff> staffList = manager.findStaffersInTeamByTeamId(teamId);
			teamStaffBean.setStaffList(staffList);
		}
		session.setAttribute("teamStaffBeanList", teamStaffBeanList);

		LOG.trace(Messages.LOG_COMMAND_FINISHED);
		return forward;
	}
}