package co.uk.gymtracker.validation;

import co.uk.gymtracker.model.form.GymSessionForm;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static java.lang.String.format;

/**
 * Custom Validator for GymLogData model object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 23/06/14
 * @project GymTrackerApp
 */
@Component
public class GymLogDataValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return GymSessionForm.class.equals(clazz);
    }

    protected final XLogger logger = XLoggerFactory.getXLogger(GymLogDataValidator.class
            .getName());

    @Override
    public void validate(Object target, Errors errors) {

        logger.entry();

        GymSessionForm logData = (GymSessionForm) target;

        if(logData.getTypeOfExercise().equals("CE")) {

            // cardio activity validation
            logger.info("Validate [CardioExercises]");
            ValidationUtils.rejectIfEmpty(errors, "exerciseCardio[0].exercise", "error.empty.exercise");
            ValidationUtils.rejectIfEmpty(errors, "exerciseCardio[0].duration", "error.empty.duration");
            ValidationUtils.rejectIfEmpty(errors, "exerciseCardio[0].distance", "error.empty.distance");
            ValidationUtils.rejectIfEmpty(errors, "exerciseCardio[0].level", "error.empty.level");
            ValidationUtils.rejectIfEmpty(errors, "exerciseCardio[0].calories", "error.empty.calories");

        }

        if(logData.getTypeOfExercise().equals("WE")) {

            // weight activity validation
            logger.info("Validate [Reps/Weight]");

            ValidationUtils.rejectIfEmpty(errors, "exerciseWeight[0].reps", "error.empty.reps");
            ValidationUtils.rejectIfEmpty(errors, "exerciseWeight[0].weightLifted", "error.empty.weight");

        }

        // Log all thrown error messages
        for(ObjectError error : errors.getAllErrors()) {
            logger.error(format("[ %s ]", error.getCode()));
        }

        logger.exit();

    }
}
