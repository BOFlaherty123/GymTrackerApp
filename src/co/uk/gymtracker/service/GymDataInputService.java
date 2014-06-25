package co.uk.gymtracker.service;

import co.uk.gymtracker.dao.GymUserDataDao;
import co.uk.gymtracker.model.ExerciseCardio;
import co.uk.gymtracker.model.ExerciseWeight;
import co.uk.gymtracker.model.GymLogData;
import co.uk.gymtracker.model.GymUser;
import co.uk.gymtracker.model.form.GymSessionForm;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GymDataInputService {

    private static final String CARDIO_EXERCISE = "CE";
    private static final String WEIGHT_EXERCISE = "WE";

    protected final XLogger logger = XLoggerFactory.getXLogger(GymDataInputService.class
            .getName());

    @Autowired
    public GymUserDataDao gymDataDao;

    public List<GymLogData> retrieveGymLogDataByUserId(GymUser user) {
        return gymDataDao.findAllUserGymData(user.getId());
    }

    public List<GymLogData> retrieveGymUserDataByActivity(GymUser user, String activity) {
        return gymDataDao.findGymUserDataByActivity(user, activity);
    }

    public void buildAndSaveGymLogData(GymUser user, GymSessionForm gymSessionForm) {

        logger.entry();

        GymLogData logData = populateGymLogData(gymSessionForm, user);

        String typeOfExercise = gymSessionForm.getTypeOfExercise();
        logger.info(".getTypeOfExercise() [" + typeOfExercise + "]");

        boolean saveCardioExercise; boolean saveWeightExercise;
        saveCardioExercise = isCardioExercise(typeOfExercise);
        saveWeightExercise = isWeightExercise(typeOfExercise);

        if(!typeOfExercise.equals(CARDIO_EXERCISE) && !typeOfExercise.equals(WEIGHT_EXERCISE)){
            saveCardioExercise = true;
            saveWeightExercise = true;
        }

        addCardioExercise(gymSessionForm, logData, saveCardioExercise);
        addWeightExercise(gymSessionForm, logData, saveWeightExercise);

        logger.info("added gymSessionData [" + logData + "]");

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
        GymLogData gymSessionData = new GymLogData();

        gymSessionData.setUserId(user.getId());
        gymSessionData.setDate(gymSessionForm.getDate());
        gymSessionData.setDuration(gymSessionForm.getDuration());
        gymSessionData.setUserWeight(gymSessionForm.getUserWeight());

        logger.info("built GymLogData[" + gymSessionData.toString() + "]");

        return gymSessionData;
    }

    private boolean isWeightExercise(String typeOfExercise) {
        return (typeOfExercise.equals(WEIGHT_EXERCISE)) ? true : false;
    }

    private boolean isCardioExercise(String typeOfExercise) {
        return (typeOfExercise.equals(CARDIO_EXERCISE)) ? true : false;
    }

    private void addCardioExercise(GymSessionForm gymSessionForm, GymLogData gymSessionData, boolean saveCardioExercise) {

        List<ExerciseCardio> exerciseCardio = new ArrayList<ExerciseCardio>();

        if(saveCardioExercise) {

            // filter out any blank cardio exercise rows
            for(ExerciseCardio ec : gymSessionForm.getExerciseCardio()) {
                if(ec.getExercise() != null && !ec.getExercise().isEmpty()) {
                    exerciseCardio.add(ec);
                }
            }

            // save cardio exercises
            gymSessionData.setExerciseCardio(exerciseCardio);

        }
    }

    private void addWeightExercise(GymSessionForm gymSessionForm, GymLogData gymSessionData, boolean saveWeightExercise) {

        List<ExerciseWeight> exerciseWeight = new ArrayList<ExerciseWeight>();

        if(saveWeightExercise) {

            // filter out any blank weight exercise rows
            for(ExerciseWeight ew : gymSessionForm.getExerciseWeight()) {
                if(ew.getExercise() != null && !ew.getExercise().isEmpty()) {
                    exerciseWeight.add(ew);
                }
            }

            // save weight exercises
            gymSessionData.setExerciseWeight(gymSessionForm.getExerciseWeight());

        }

    }

}
