/**
 * Created by BO034731 on 20/06/14.
 */

var typeOfExercise = {
    'CE': {
        show: "#activityCardio",
        hide: "#activityWeights"
    },
    'WE': {
        show: "#activityWeights",
        hide: "#activityCardio"
    },
    'CW': {
        show: "#activityCardio, #activityWeights"
    }
};

function selectActivity() {

    $("#activity").click(function() {

        var code = $("#activity").val();

        if(code in typeOfExercise) {
            $(typeOfExercise[code].show).show();
            $(typeOfExercise[code].hide).hide();
        }

    });

}
