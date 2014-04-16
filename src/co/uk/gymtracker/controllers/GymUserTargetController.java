package co.uk.gymtracker.controllers;

import co.uk.gymtracker.targets.CalculateUserTargets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * GymUserTargetController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/04/14
 * @project GymTrackerApp
 */
@Controller
public class GymUserTargetController {

    @Autowired
    public CalculateUserTargets targets;

    /*
        Allow the user to view their future workout targets, 5, 10, 15% over 2, 4, 6 , 8 weeks.

        @RequestMapping(value="/target/{percentage_increase}")
        @PathVariable("percentage_increase") int percentage_increase

     */
    public void calculateTarget() {
        targets.calculateTargetOnPercentageIncrease(10);
    }


}
