package ua.nure.sharov.Airlines.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorAddFlightCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorAddFlightPageCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorAddStaffCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorApplicationPageCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorDeleteFlightCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorDeleteStaffCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorEditFlightCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorEditFlightPageCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorEditStaffCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorEditStaffPageCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorHomePageCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorResolveApplicationCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorShowFlightsPageCommand;
import ua.nure.sharov.Airlines.web.command.adminCommand.AdministratorShowStaffPageCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherCreateApplicationCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherCreateApplicationPageCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherCreateTeamCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherEditFlightStatusCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherEditFlightStatusPageCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherFindTeamPageCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherHomePageCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherShowFlightCommand;
import ua.nure.sharov.Airlines.web.command.dispatcherCommand.DispatcherShowTeamCommand;

/**
 * Holder for all commands
 * @author Max
 *
 */
public class CommandContainer {
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		commands.put("find_flights", new FindFlightsCommand());
		commands.put("find_flight_number", new FindFlightNumberCommand());
		commands.put("sort", new SortCommand());
		commands.put("login", new LoginCommand());
		
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		
		commands.put("dispatcherHomePage", new DispatcherHomePageCommand());
		commands.put("administratorHomePage", new AdministratorHomePageCommand());
		
		commands.put("findTeam", new DispatcherFindTeamPageCommand());
		commands.put("createTeam", new DispatcherCreateTeamCommand());
		commands.put("showTeam", new DispatcherShowTeamCommand());
		commands.put("showFlights", new DispatcherShowFlightCommand());
		commands.put("editFlightStatus", new DispatcherEditFlightStatusPageCommand());
		commands.put("setFlightStatus", new DispatcherEditFlightStatusCommand());
		commands.put("createApplicationPage", new DispatcherCreateApplicationPageCommand());
		commands.put("createApplication", new DispatcherCreateApplicationCommand());
		
		commands.put("showFlightsPage", new AdministratorShowFlightsPageCommand());
		commands.put("addFlightPage", new AdministratorAddFlightPageCommand());
		commands.put("addFlight", new AdministratorAddFlightCommand());
		commands.put("deleteFlight", new AdministratorDeleteFlightCommand());
		commands.put("editFlightPage", new AdministratorEditFlightPageCommand());
		commands.put("editFlight", new AdministratorEditFlightCommand());
		commands.put("showStaffPage", new AdministratorShowStaffPageCommand());
		commands.put("addStaff", new AdministratorAddStaffCommand());
		commands.put("editStaffPage", new AdministratorEditStaffPageCommand());
		commands.put("editStaff", new AdministratorEditStaffCommand());
		commands.put("deleteStaff", new AdministratorDeleteStaffCommand());
		commands.put("showApplicationPage", new AdministratorApplicationPageCommand());
		commands.put("resolveApplication", new AdministratorResolveApplicationCommand());

		LOG.debug("Command container was succesfully initialized");
	}

	public static Command getCommand(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command doesn't found. Command --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}
}