package ua.nure.sharov.Airlines.web.command;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.sharov.Airlines.exception.ApplicationException;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author Max
 *
 */
public abstract class Command implements Serializable {
	private static final long serialVersionUID = -6461849020967949375L;

	/**
	 * Execution method for command.
	 * 
	 * @return Address to go once the command is executed.
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws ApplicationException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}