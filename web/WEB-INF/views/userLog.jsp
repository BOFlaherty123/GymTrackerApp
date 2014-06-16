<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

    <head>
        <title>Log</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script>
            $(function() {
                $("#toDate, #fromDate").datepicker();
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
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/userLog/show">
                        <img style="max-width:80px; margin-top: -15px;" src="${pageContext.request.contextPath}/resources/images/gymTrackerLogo.png">
                    </a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/user/userDashboard"><span class="glyphicon glyphicon-stats nav_icon"></span> User Dashboard</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/createUser"><span class="glyphicon glyphicon-user nav_icon"></span> Create a new User</a></li>
                        <li><a href="${pageContext.request.contextPath}/addGymSessionForm"><span class="glyphicon glyphicon-file nav_icon"></span> Add Gym Session</a></li>
                        <li><a href="${pageContext.request.contextPath}/userLog/view.pdf"><span class="glyphicon glyphicon-list-alt nav_icon"></span> Download Results PDF</a></li>
                        <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout"><span class="glyphicon glyphicon-log-out nav_icon"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="welcomeUserDiv">
            <div>
                <h4>Welcome, <sec:authentication property="principal.username" />!</h4>
            </div>
        </div>

        <div id="search">

            <fieldset>
                <legend><h4>Search</h4></legend>

                <form:form method="post" commandName="gymSessionForm" action="/test">
                    <form role="form">

                        <div class="col-md-2">
                            <div class="form-group">
                                <form:label path="activity">By Activity</form:label>
                                <form:select path="activity" items="${activity}" class="form-control"/>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="form-group">
                                <form:label path="date">From</form:label>
                                <form:input path="date" id="toDate" class="form-control" placeholder="From Date"/>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="form-group">
                                <form:label path="date">To</form:label>
                                <form:input path="date" id="fromDate" class="form-control" placeholder="To Date"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-10">
                                <input type="submit" value="Submit"/>
                            </div>
                        </div>

                    </form>
                </form:form>

            </fieldset>
        </div>

        <div class="table-responsive">

            <table class="table table-hover">
                <tr>
                    <th>Date</th>
                    <th>Duration</th>
                    <th>User Weight</th>
                    <th>Activity</th>
                    <th>Activity Duration</th>
                    <th>Distance</th>
                    <th>Level/Weight</th>
                    <th>Calories</th>
                </tr>

                <c:forEach var="record" items="${gymLogDataList}">
                    <tr>
                        <td><c:out value="${record.date}"/> </td>
                        <td><c:out value="${record.duration}"/> </td>
                        <td><c:out value="${record.userWeight}"/> </td>
                        <td><c:out value="${record.activity}"/> </td>
                        <td><c:out value="${record.activityDuration}"/> </td>
                        <td><c:out value="${record.distance}"/> </td>
                        <td><c:out value="${record.levelOrWeight}"/> </td>
                        <td><c:out value="${record.calories}"/> </td>
                    </tr>
                </c:forEach>

            </table>

        </div>

    </body>

</html>