<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

    <head>
        <title>User Dashboard</title>

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <!-- Highchart API -->
        <script src="http://code.highcharts.com/highcharts.js"></script>
        <!-- User Dashboard Charts -->
        <script src="${pageContext.request.contextPath}/resources/js/userDashboard.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">

        <script>

            $(function () {

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
                        <li><a href="${pageContext.request.contextPath}/userLog/show">Back</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="userDetailsDiv" class="user_detail">
            <h4>
                Dashboard data for user, <b><sec:authentication property="principal.username" /></b>.
            </h4>
        </div>

        <!-- Row 1 -->
        <div class="col-md-6">
            <div id="activityDistanceDiv" class="chart_size"></div>
        </div>

        <div class="col-md-6">
            <div id="avgCaloriesByActivityDiv" class="chart_size"></div>
        </div>

        <!-- Row 2 -->
        <div class="col-md-6" class="chart_padding">
            <div id="activityDurationDiv" class="chart_size"></div>
        </div>

        <div class="col-md-6" class="chart_padding">
            <div id="activityAvgDurationDiv" class="chart_size"></div>
        </div>

    </body>

</html>