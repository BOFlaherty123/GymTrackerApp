package co.uk.gymtracker.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/06/2014
 * @project GymTrackerApp
 */
@Controller
@RequestMapping(value="/admin")
public class AdminOptionsController {

    @RequestMapping(value="/editAppOptions")
    public ModelAndView editApplicationOptions() {
        return new ModelAndView("admin/editAppOptions");
    }

}
