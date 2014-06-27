package co.uk.gymtracker.exceptions;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/06/14
 * @project GymTrackerApp
 */
public class GymUserNotFoundException extends NullPointerException {

    public GymUserNotFoundException(String s) {
        super(s);
    }
}
