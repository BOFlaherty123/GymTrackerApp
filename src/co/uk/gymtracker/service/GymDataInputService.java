package co.uk.gymtracker.service;

import co.uk.gymtracker.model.ExerciseCardio;
import co.uk.gymtracker.model.ExerciseWeight;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymSessionForm;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    private static final String BOTH_EXERCISES = "CW";

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

        List<ExerciseCardio> cardioExercises = processExerciseList(gymSessionForm.getExerciseCardio(),
                new ArrayList<ExerciseCardio>(), saveCardioExercise);

        List<ExerciseWeight> weightExercises = processExerciseList(gymSessionForm.getExerciseWeight(),
                new ArrayList<ExerciseWeight>(), saveWeightExercise);

        // populate cardio exercises if the list is not empty
        addCardioExercises(logData, cardioExercises);

        // populate weight exercises if the list is not empty
        addWeightExercises(logData, weightExercises);

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
     * add cardio exercises to the model object GymLogData
     *
     * @param logData
     * @param cardioExercises
     */
    private void addCardioExercises(GymLogData logData, List<ExerciseCardio> cardioExercises) {
        if(isExerciseListEmpty(cardioExercises)) {
            logData.setExerciseCardio(cardioExercises);
        }
    }

    /**
     * add weight exercises to the model object GymLogData
     *
     * @param logData
     * @param weightExercises
     */
    private void addWeightExercises(GymLogData logData, List<ExerciseWeight> weightExercises) {
        if(isExerciseListEmpty(weightExercises)) {
            logData.setExerciseWeight(weightExercises);
        }
    }

    /**
     * determines which type of exercise data has been submitted by the end user
     *
     * @param typeOfExercise
     * @param exercise
     * @return
     */
    private boolean determineTypeOfExercise(String typeOfExercise, String exercise) {
        return ((typeOfExercise.equals(exercise) || typeOfExercise.equals(BOTH_EXERCISES)));
    }

    /**
     * Generic method to process any userExercises that have been submitted by the end user
     *
     * @param userExercises
     * @param exercises
     * @param saveToDatabase
     * @param <T>
     * @return
     */
    private <T> List<T> processExerciseList(List<T> userExercises, List<T> exercises, boolean saveToDatabase) {

        if(saveToDatabase) {
            for(T exercise : userExercises) {

                try {
                    Field field = exercise.getClass().getDeclaredField("exercise");
                    String exerciseValue = String.valueOf(field.get(exercise));

                    if(!exerciseValue.isEmpty()) {
                        exercises.add(exercise);
                    }

                } catch (NoSuchFieldException | IllegalAccessException e)  {
                    e.printStackTrace();
                }
            }
        }

        // save exercises  to the model object
        logger.info("set [ " + exercises + " ] on gymSessionData");

        return exercises;
    }

    /**
     * returns boolean value testing whether the exercise list is empty or not
     *
     * @param list
     * @param <T>
     * @return
     */
    private <T> boolean isExerciseListEmpty(List<T> list) {
        return !list.isEmpty();
    }

}
