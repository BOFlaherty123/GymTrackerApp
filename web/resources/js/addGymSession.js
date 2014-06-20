/**
 * Created by BO034731 on 20/06/14.
 */

function selectActivity() {

    $("#activity").click(function() {

        var code = $( "#activity").val();

        if(code == 'AC') {
            $('#activityWeights').hide();
            $('#activityCardio').show();
        } else {
            $('#activityCardio').hide();
            $('#activityWeights').show();
        }

    });

}
