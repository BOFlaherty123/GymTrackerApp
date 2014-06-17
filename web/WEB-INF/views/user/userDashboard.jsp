<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

    <head>
        <title>User Dashboard</title>

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <!-- Highchart API -->
        <script src="http://code.highcharts.com/highcharts.js"></script>
        <!-- User Dashboard Charts -->
        <script src="${pageContext.request.contextPath}/resources/js/userDashboard.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">

        <script>

            $(function () {

                $('#tabs').tab();

                $("#percentage_slider").slider({
                    range: "max",
                    min: 2.5,
                    max: 15,
                    step: 2.5,
                    slide: function( event, ui ) {
                        $("#percentageIncrease").val(ui.value);
                    },
                    stop: function( event, ui ) {

                        var activity = $("#targetActivity").val();

                        $.ajax({
                            type: "POST",
                            data: ui.value,
                            contentType: 'application/json',
                            dataType : 'json',
                            url: "calculateTargetByPercentIncrease/" + activity + "/" + ui.value,
                            success: function(data){

                                var response = JSON.stringify(data);
                                console.log(response);

                                var obj = JSON.parse(response);
                                console.log(obj.duration);
                                console.log(obj.distance);

                                var activityDurationIncrease = "<p>" + obj.duration + "</p>";
                                $('#durationTargetOutput').empty();
                                $('#durationTargetOutput').append(activityDurationIncrease);

                                var activityDistanceIncrease = "<p>" + obj.distance + "</p>";
                                $('#distanceTargetOutput').empty();
                                $('#distanceTargetOutput').append(activityDistanceIncrease);
                            }
                        });
                    }
                });

                $("#percentageIncrease").val( $("ex1").slider("value") );

                // Exact average figures to be provided via an ajax GET
                displayUserWeightLineChart(362.4, 345.9, 290.9);

                processAvgDistanceChart();
                processAvgDurationChart();

            });

            function processAvgDistanceChart() {

                var running_avg = Number($('#running_avg_distance').val());
                var cycling_avg = Number($('#cycling_avg_distance').val());
                var rowing_avg = Number($('#rowing_avg_distance').val());

                displayAvgDistanceLineChart(running_avg, cycling_avg, rowing_avg);

            }

            function processAvgDurationChart() {

                var running_percent = Number($('#running_duration_percent').val());
                var cycling_percent = Number($('#cycling_duration_percent').val());
                var rowing_percent = Number($('#rowing_duration_percent').val());

                displayActivityPieChart(running_percent, cycling_percent, rowing_percent);
                displayAvgDurationLineChart(running_percent, cycling_percent, rowing_percent);

            }

        </script>

    </head>

    <body>

        <!-- average distance hidden fields -->
        <input type="hidden" name="running_avg_distance" id="running_avg_distance" value="${running_avg_distance}">
        <input type="hidden" name="cycling_avg_distance" id="cycling_avg_distance" value="${cycling_avg_distance}">
        <input type="hidden" name="rowing_avg_distance" id="rowing_avg_distance" value="${rowing_avg_distance}">

        <!-- activity duration percentage -->
        <input type="hidden" name="running_duration_percent" id="running_duration_percent" value="${running_duration_percent}">
        <input type="hidden" name="cycling_duration_percent" id="cycling_duration_percent" value="${cycling_duration_percent}">
        <input type="hidden" name="rowing_duration_percent" id="rowing_duration_percent" value="${rowing_duration_percent}">

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
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/userLog/show"> Back</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="userDetailsDiv" class="user_detail">
            <h4>
                Dashboard data for user, <b><sec:authentication property="principal.username" /></b>.
            </h4>
        </div>

        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
            <li class="active"><a href="#profile" data-toggle="tab">Profile</a></li>
            <li><a href="#charts" data-toggle="tab">Charts</a></li>
            <li><a href="#targets" data-toggle="tab">Targets</a></li>
        </ul>

        <div id="my-tab-content" class="tab-content">
            <div class="tab-pane active dashboard_profile" id="profile">

                <form:form method="post" commandName="gymUser" action="/GymTrackerApp/user/updateUser">

                    <div class="row">
                        <form:errors path="*" cssClass="errorblock" element="div" title="Errors"/>
                    </div>

                    <div class="form-group">
                        <label for="username">Username</label>
                        <form:input path="username" class="form-control" readonly="true"/>
                    </div>

                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <form:input path="firstName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <form:input path="lastName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="age">Age</label>
                        <form:input path="age" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <form:input path="email" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputEmail1">Password</label>
                        <form:input type="password" path="password" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <form:input type="password" path="confirmPassword" class="form-control" placeholder="Confirm Password"/>
                    </div>

                    <div class="form-group">
                        <label for="role">Role</label>
                        <form:input path="role" class="form-control" readonly="true"/>
                    </div>

                    <div class="row">
                        <input type="submit" value="Submit"/>
                    </div>

                </form:form>

            </div>
            <div class="tab-pane dashboard_charts" id="charts">

                <!-- Row 1 -->
                <div class="col-md-6">
                    <div id="activityDistanceDiv" class="chart_size"></div>
                </div>

                <div class="col-md-6">
                    <div id="avgCaloriesByActivityDiv" class="chart_size"></div>
                </div>

                <!-- Row 2 -->
                <div class="col-md-6 chart_padding">
                    <div id="activityDurationDiv" class="chart_size"></div>
                </div>

                <div class="col-md-6 chart_padding">
                    <div id="activityAvgDurationDiv" class="chart_size"></div>
                </div>

            </div>
            <div class="tab-pane dashboard_targets" id="targets">

                <label for="percentageIncrease">Target Percentage Increase:</label>
                <input type="text" id="percentageIncrease" style="border:0; color:#f6931f; font-weight:bold;">

                <div id="percentage_slider"></div>

                <div class="activityDropdownDiv">
                    <form:select path="activity" id="targetActivity" items="${activity}" class="form-control"/>
                </div>

                <div id="durationTargetIncrease" style="padding-top: 2%">
                    <b>Duration: </b>
                    <div id="durationTargetOutput" style="font-size: 6em; font-weight: bold"></div>
                </div>

                <div id="distanceTargetIncrease" style="padding-top: 2%">
                    <b>Distance: </b>
                    <div id="distanceTargetOutput" style="font-size: 6em; font-weight: bold"></div>
                </div>

            </div>
        </div>


    </body>

</html>