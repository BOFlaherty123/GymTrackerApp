package co.uk.gymtracker.controllers.admin;

import co.uk.gymtracker.controllers.AbstractGymController;
import co.uk.gymtracker.dao.AppPerformanceDao;
import co.uk.gymtracker.dao.AuditTrailDao;
import co.uk.gymtracker.model.form.AuditSearch;
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

    @Autowired
    private AuditTrailDao auditDao;

    @RequestMapping(value="/dashboard")
    public ModelAndView executeEntryPage(ModelAndView mav) {

        mav.setViewName(("admin/admin"));
        mav.addObject(new PerformanceLog());
        mav.addObject(new AuditSearch());

        displayAppStatistics(mav);
        displayAppPerformance(mav);
        displayAuditRecords(mav);

        return mav;
    }

    private void displayAppStatistics(ModelAndView mav) {

        // display database statistics
        mav.addObject(appPerformanceDao.retrieveDatabaseInformation());
        mav.addObject("numberOfUsers", userDao.findAllGymUsers().size());

    }

    private void displayAppPerformance(ModelAndView mav) {
        mav.addObject(appPerformanceDao.findAllPerformanceLogs());
    }

    private void displayAuditRecords(ModelAndView mav) {
        mav.addObject(auditDao.findAllAuditRecords());
    }

    @RequestMapping(value="/performance/slow")
    public void displaySlowQueries() {
        appPerformanceDao.findAllSlowQueries();
    }

}