package co.uk.gymtracker.logging;

import co.uk.gymtracker.dao.AuditTrailDao;
import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.audit.Audit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 26/06/14
 * @project GymTrackerApp
 */
@Aspect
@Component
public class AuditTrailAspect {

    @Autowired
    protected GymUserDao dao;
    @Autowired
    protected AuditTrailDao auditDao;

    @Around("within(co.uk.gymtracker.controllers.*)")
    public Object generateAuditBefore(ProceedingJoinPoint joinPoint) throws Throwable {

        SecurityContext ctx = SecurityContextHolder.getContext();
        GymUser gymUser =  dao.findGymUser(ctx.getAuthentication().getName());

        String className = joinPoint.getStaticPart().getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        String arguments = Arrays.toString(joinPoint.getArgs());

        Audit audit = new Audit(new Date().toString(), className, methodName, gymUser.getId(),
                gymUser.getUsername(), arguments);

        auditDao.saveAuditRecordByUsername(audit);

        return joinPoint.proceed();
    }

}
