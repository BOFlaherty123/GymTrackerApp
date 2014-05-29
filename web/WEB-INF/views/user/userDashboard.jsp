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
                displayActivityPieChart(50, 30, 20);
                displayUserWeightLineChart(362.4, 345.9, 290.9);

            });

        </script>

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
                    <a class="navbar-brand" href="#">GymTrackerApp</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/GymTrackerApp/userLog/show">Back</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="userDetailsDiv" class="user_detail">
            <h4>
                Dashboard data for user, <b><sec:authentication property="principal.username" /></b>.
            </h4>
        </div>

        <div class="col-md-6">
            <div id="activityDurationDiv" style="height: 250px; border: solid 1px"></div>
        </div>
        <div class="col-md-6">
            <div id="avgCaloriesByActivityDiv" style="height: 250px; border: solid 1px"></div>
        </div>

    </body>

</html>