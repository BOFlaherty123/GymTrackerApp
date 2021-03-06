<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

    <head>
        <title>Gym Tracker App</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrapValidator.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/addGymSession.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/validation/addGymSession.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.typewatch.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">

        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/form.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrapValidator.css">

        <script>

            $(function() {
                setupAddGymSessionPage();
                selectActivity();
                validate();

                var exerciseCardioOptions = {
                    callback: function () {
                        var rowNo = ($("#table_cardioExercise tr").length - 1);
                        $("#table_cardioExercise tr#exerciseCardio_row" + rowNo).show();
                        $("#table_cardioExercise tr#exerciseCardio_row" + rowNo).find('input, select').attr('disabled', false);
                    },
                    wait: 200,
                    highlight: true,
                    captureLength: 1
                };

                $("#exerciseCardo_calories0").typeWatch( exerciseCardioOptions );

                var exerciseWeightOptions = {
                    callback: function () {
                        var rowNo = ($("#table_cardioExercise tr").length - 1);
                        $("#table_weightExercise tr#exerciseWeight_row" + rowNo).show();
                        $("#table_weightExercise tr#exerciseWeight_row" + rowNo).find('input, select').attr('disabled', false);
                    },
                    wait: 200,
                    highlight: true,
                    captureLength: 1
                };

                $("#exerciseWeight_weightLifted0").typeWatch( exerciseWeightOptions );

            });
        </script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/userLog/show">
                        <img style="max-width:80px; margin-top: -15px;" src="${pageContext.request.contextPath}/resources/images/gymTrackerLogo.png">
                    </a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav" class="active">
                        <li><a href="${pageContext.request.contextPath}/userLog/show"> Back</a></li>
                    </ul>
                </div>
            </div>
        </div>

            <div class="submit_user_form">

                <form:form id="gymSessionForm" method="post" commandName="gymSessionForm" action="/GymTrackerApp/addGymSession">

                    <form role="form">

                        <div class="row">

                            <div class="row">
                                <div class="col-md-6">
                                    <form:errors path="*" cssClass="errorblock" element="div" title="Errors"/>
                                </div>
                            </div>

                            <div class="gym_layout_padding">

                                <fieldset>
                                    <legend><h4>Gym Session Overview</h4></legend>

                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="date">Date</form:label>
                                            <form:input path="date" name="date" id="date" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="duration">Duration</form:label>
                                            <form:input path="duration" name="duration" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="userWeight">User Weight</form:label>
                                            <form:input path="userWeight" name="userWeight" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2"></div>
                                    <div class="col-md-2"></div>

                                </fieldset>

                            </div>

                        </div>

                        <div class="row">

                            <div class="gym_layout_padding">

                                <fieldset>

                                    <legend><h4>Gym Activity Log</h4></legend>

                                    <div class="row row_padding">

                                        <form:select id="activity" path="typeOfExercise" class="form-control">
                                            <form:option value="CE" label="Cardio"/>
                                            <form:option value="WE" label="Weight"/>
                                            <form:option value="CW" label="Cardio & Weights"/>
                                        </form:select>

                                    </div>

                                    <div class="row header row_padding table-responsive" id="activityCardio">

                                        <table id="table_cardioExercise" class="table table-striped table-condensed">
                                            <tr>
                                                <th>Exercise</th>
                                                <th>Duration (mins)</th>
                                                <th>Distance (km)</th>
                                                <th>Level</th>
                                                <th>Calories</th>
                                            </tr>
                                            <!-- Row 1 -->
                                            <tr id="exerciseCardio_row1">
                                                <td>
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <form:select path="exerciseCardio[0].exercise" id="exerciseCardo_exercise0" class="form-control">
                                                                <form:option value="" label=" "/>
                                                                <form:options items="${exercises}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-10">
                                                        <div class="form-group">
                                                            <form:select path="exerciseCardio[0].duration" items="${activityDuration}" id="exerciseCardo_duration0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseCardio[0].distance" id="exerciseCardo_distance0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseCardio[0].level" id="exerciseCardo_level0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseCardio[0].calories" id="exerciseCardo_calories0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <!-- Row 2 -->
                                            <tr id="exerciseCardio_row2">
                                                <td>
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <form:select path="exerciseCardio[1].exercise" class="form-control">
                                                                <form:option value="" label=" "/>
                                                                <form:options items="${exercises}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-10">
                                                        <div class="form-group">
                                                            <form:select path="exerciseCardio[1].duration" items="${activityDuration}" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseCardio[1].distance" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseCardio[1].level" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseCardio[1].calories" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>

                                    </div>

                                    <div class="row header row_padding table-responsive" id="activityWeights">

                                        <table id="table_weightExercise" class="table table-striped table-condensed">
                                            <tr>
                                                <th>Exercise</th>
                                                <th># Reps</th>
                                                <th>Weight (kg)</th>
                                            </tr>
                                            <tr id="exerciseWeight_row1">
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseWeight[0].exercise" id="exerciseWeight_exercise0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-5">
                                                        <div class="form-group">
                                                            <form:input path="exerciseWeight[0].reps" id="exerciseWeight_reps0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-5">
                                                        <div class="form-group">
                                                            <form:input path="exerciseWeight[0].weightLifted" id="exerciseWeight_weightLifted0" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr id="exerciseWeight_row2">
                                                <td>
                                                    <div class="col-md-8">
                                                        <div class="form-group">
                                                            <form:input path="exerciseWeight[1].exercise" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-5">
                                                        <div class="form-group">
                                                            <form:input path="exerciseWeight[1].reps" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="col-md-5">
                                                        <div class="form-group">
                                                            <form:input path="exerciseWeight[1].weightLifted" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>

                                    </div>

                                </fieldset>

                            </div>

                        </div>
                        <!--
                            Additional rows example, Needs to be removed once refactor has been completed
                            Change to List<Activity> activities and allow for a show/hide function on form input
                        -->
                        <div class="row .row_padding ">
                            <div class="col-md-10">
                                <button type="submit" class="btn btn-default">Submit</button>
                            </div>
                        </div>

                    </form>

                </form:form>

            </div>

    </body>

</html>