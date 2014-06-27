package co.uk.gymtracker.exceptions;

/**
 * AppRunTimeException
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/06/14
 * @project GymTrackerApp
 */
public class AppRunTimeException extends RuntimeException {

    public AppRunTimeException(Throwable e) {
        super(e);
    }

    public AppRunTimeException(String message) {
        super(message);
    }
}
