package co.uk.gymtracker.controllers.admin;

import co.uk.gymtracker.controllers.AbstractGymController;
import co.uk.gymtracker.dao.AppPerformanceDao;
import co.uk.gymtracker.model.performance.PerformanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Admin Dashboard Controller
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/06/2014
 * @project GymTrackerApp
 */
@Controller
@RequestMapping(value="/admin")
public class AdminDashboardController extends AbstractGymController {

    @Autowired
    private AppPerformanceDao appPerformanceDao;

    @RequestMapping(value="/dashboard")
    public ModelAndView executeEntryPage(ModelAndView mav) {

        logger.entry();

        mav.setViewName(("admin/admin"));
        mav.addObject(new PerformanceLog());

        displayAppStatistics(mav);
        displayAppPerformance(mav);

        logger.exit();

        return mav;
    }

    private void displayAppStatistics(ModelAndView mav) {
        logger.entry();

        mav.addObject("numberOfUsers", userDao.findAllGymUsers().size());

        logger.exit();
    }

    private void displayAppPerformance(ModelAndView mav) {
        logger.entry();

        mav.addObject(appPerformanceDao.findAllPerformanceLogs());

        logger.exit();
    }

    @RequestMapping(value="/performance/slow")
    public void displaySlowQueries() {
        appPerformanceDao.findAllSlowQueries();
    }


}