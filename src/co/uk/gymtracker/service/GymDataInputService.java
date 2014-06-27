package co.uk.gymtracker.service;

import co.uk.gymtracker.model.ExerciseCardio;
import co.uk.gymtracker.model.ExerciseWeight;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymSessionForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service containing business logic for the GymDataInputController
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 25/06/14
 * @project GymTrackerApp
 */
@Service
public class GymDataInputService extends AbstractGymService {

    private static final String CARDIO_EXERCISE = "CE";
    private static final String WEIGHT_EXERCISE = "WE";

    public List<GymLogData> retrieveGymLogDataByUserId(GymUser user) {
        return gymDataDao.findAllUserGymData(user.getId());
    }

    /**
     * retrieves GymLogData for a given GymUser object
     *
     * @param user
     * @param activity
     * @return
     */
    public List<GymLogData> retrieveGymUserDataByActivity(GymUser user, String activity) {
        return gymDataDao.findGymUserDataByActivity(user, activity);
    }

    /**
     * builds and saves an instance of GymLogData to the database
     *
     * @param user
     * @param gymSessionForm
     */
    public void buildAndSaveGymLogData(GymUser user, GymSessionForm gymSessionForm) {
        logger.entry();

        GymLogData logData = populateGymLogData(gymSessionForm, user);

        String typeOfExercise = gymSessionForm.getTypeOfExercise();
        logger.info(".getTypeOfExercise() [" + typeOfExercise + "]");

        boolean saveCardioExercise; boolean saveWeightExercise;
        saveCardioExercise = determineTypeOfExercise(typeOfExercise, CARDIO_EXERCISE);
        saveWeightExercise = determineTypeOfExercise(typeOfExercise, WEIGHT_EXERCISE);

        if(!typeOfExercise.equals(CARDIO_EXERCISE) && !typeOfExercise.equals(WEIGHT_EXERCISE)){
            saveCardioExercise = true; saveWeightExercise = true;
        }

        addCardioExercise(gymSessionForm, logData, saveCardioExercise, new ArrayList<ExerciseCardio>());
        addWeightExercise(gymSessionForm, logData, saveWeightExercise, new ArrayList<ExerciseWeight>());

        gymDataDao.saveGymLogData(logData);
        logger.info("saved gymSessionData [" + logData + "]");

        logger.exit();
    }

    /**
     * populate a gym log data object from the form submission
     *
     * @param gymSessionForm
     * @param user
     * @return gymSessionData
     */
    private GymLogData populateGymLogData(GymSessionForm gymSessionForm, GymUser user) {
        logger.entry();

        GymLogData gymSessionData = new GymLogData();
        gymSessionData.setUserId(user.getId());
        gymSessionData.setDate(gymSessionForm.getDate());
        gymSessionData.setDuration(gymSessionForm.getDuration());
        gymSessionData.setUserWeight(gymSessionForm.getUserWeight());

        logger.info("built GymLogData[" + gymSessionData.toString() + "]");
        logger.exit();

        return gymSessionData;
    }

    /**
     * determines which type of exercise data has been submitted by the end user
     *
     * @param typeOfExercise
     * @param exercise
     * @return
     */
    private boolean determineTypeOfExercise(String typeOfExercise, String exercise) {
        return typeOfExercise.equals(exercise);
    }

    /**
     * creates & adds a list of ExerciseCardio to GymLogData if required
     *
     * @param gymSessionForm
     * @param gymSessionData
     * @param saveToDatabase
     * @param cardioExercises
     */
    private void addCardioExercise(GymSessionForm gymSessionForm, GymLogData gymSessionData, boolean saveToDatabase,
                                   List<ExerciseCardio> cardioExercises) {
        logger.entry(gymSessionForm, gymSessionData, saveToDatabase);

        if(saveToDatabase) {

            // filter out any blank cardio exercise rows
            for(ExerciseCardio ec : gymSessionForm.getExerciseCardio()) {
                if(ec.getExercise() != null && !ec.getExercise().isEmpty()) {
                    cardioExercises.add(ec);
                }
            }

            // save cardio exercises
            logger.info("set [ " + cardioExercises + " ] on gymSessionData");
            gymSessionData.setExerciseCardio(cardioExercises);

        }

        logger.exit();
    }

    /**
     * creates & adds a list of ExerciseWeight to GymLogData if required
     *
     * @param gymSessionForm
     * @param gymSessionData
     * @param saveToDatabase
     * @param weightExercises
     */
    private void addWeightExercise(GymSessionForm gymSessionForm, GymLogData gymSessionData, boolean saveToDatabase,
                                   List<ExerciseWeight> weightExercises) {

        logger.entry(gymSessionForm, gymSessionData, saveToDatabase);

        if(saveToDatabase) {

            // filter out any blank weight exercise rows
            for(ExerciseWeight ew : gymSessionForm.getExerciseWeight()) {
                if(ew.getExercise() != null && !ew.getExercise().isEmpty()) {
                    weightExercises.add(ew);
                }
            }

            // save weight exercises
            logger.info("set [ " + weightExercises + " ] on gymSessionData");
            gymSessionData.setExerciseWeight(weightExercises);

        }

    }

}
