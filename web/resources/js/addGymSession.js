/**
 * Created by BO034731 on 20/06/14.
 */
function setupAddGymSessionPage() {

    $("#date").datepicker();
    $('#activityCardio').hide();
    $('#activityWeights').hide();

    $("#table_cardioExercise tr#exerciseCardio_row2").hide();
    $("#table_cardioExercise tr#exerciseCardio_row2").find('input, select').attr('disabled', true);

    $("#table_weightExercise tr#exerciseWeight_row2").hide();
    $("#table_weightExercise tr#exerciseWeight_row2").find('input, select').attr('disabled', true);

}

var typeOfExercise = {
    'CE': {
        show: "#activityCardio",
        hide: "#activityWeights",
        applyHidden: "#exerciseWeight_row1",
        disableHidden: "#exerciseCardio_row1"
    },
    'WE': {
        show: "#activityWeights",
        hide: "#activityCardio",
        applyHidden: "#exerciseCardio_row1",
        disableHidden: "#exerciseWeight_row1"
    },
    'CW': {
        show: "#activityCardio, #activityWeights",
        disableHidden: "#exerciseCardio_row1, #exerciseWeight_row1"
    }
};

function selectActivity() {

    $("#activity").click(function() {

        var code = $("#activity").val();

        if(code in typeOfExercise) {
            $(typeOfExercise[code].show).show();
            $(typeOfExercise[code].hide).hide();

            // Enable/Disable a set of fields to bypass validation
            $(typeOfExercise[code].disableHidden).find('input, select').attr('disabled', false);
            $(typeOfExercise[code].applyHidden).find('input, select').attr('disabled', true);
        }

    });

}