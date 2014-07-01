package co.uk.gymtracker.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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

    private final XLogger logger = XLoggerFactory.getXLogger(AuditTrailAspect.class
            .getName());

    @Before("within(co.uk.gymtracker.controllers.*)")
    public void beforeTraceMethods(JoinPoint joinPoint) {
        logger.entry("class[ " + retrieveClassName(joinPoint) + " ] method[ " + retrieveMethodName(joinPoint) + " ]" +
                " args[ " + retrieveArguments(joinPoint) + " ]");
    }

    @After("within(co.uk.gymtracker.controllers.*)")
    public void afterTraceMethods(JoinPoint joinPoint) {
        logger.exit("class[ " + retrieveClassName(joinPoint) + " ] method[ " + retrieveMethodName(joinPoint)  + " ]");
    }

    @After("execution(* org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler.onAuthenticationSuccess(..))")
    public void authenticated() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);

    }

    private String retrieveArguments(JoinPoint joinPoint) {
        return Arrays.toString(joinPoint.getArgs());
    }

    private String retrieveMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String retrieveClassName(JoinPoint joinPoint) {
        return joinPoint.getStaticPart().getSignature().getDeclaringType().getName();
    }

}