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

        mav.setViewName(("admin/admin"));
        mav.addObject(new PerformanceLog());

        displayAppStatistics(mav);
        displayAppPerformance(mav);
        displayAppAudit(mav);

        return mav;
    }

    private void displayAppStatistics(ModelAndView mav) {
        mav.addObject("numberOfUsers", userDao.findAllGymUsers().size());
    }

    private void displayAppPerformance(ModelAndView mav) {
        mav.addObject(appPerformanceDao.findAllPerformanceLogs());
    }

    private void displayAppAudit(ModelAndView mav) {

    }

    @RequestMapping(value="/performance/slow")
    public void displaySlowQueries() {
        appPerformanceDao.findAllSlowQueries();
    }


}