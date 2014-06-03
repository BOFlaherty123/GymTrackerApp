<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>

    <head>
        <title>Log</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">

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
                    <a class="navbar-brand" href="#">GymTrackerApp</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/user/userDashboard">User Dashboard</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/createUser">Create a new User</a></li>
                        <li><a href="${pageContext.request.contextPath}/addGymSessionForm">Add Gym Session</a></li>
                        <li><a href="${pageContext.request.contextPath}/userLog/view.pdf">Download Results PDF</a></li>
                        <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="welcomeUserDiv">
            <div>
                <h4>Welcome, <sec:authentication property="principal.username" />!</h4>
            </div>
        </div>

        <div class="table-responsive">

            <table class="table table-hover">
                <tr>
                    <th>Date</th>
                    <th>Duration</th>
                    <th>User Weight</th>
                    <th>Activity</th>
                    <th>Activity Duration</th>
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
                        <td><c:out value="${record.levelOrWeight}"/> </td>
                        <td><c:out value="${record.calories}"/> </td>
                    </tr>
                </c:forEach>

            </table>

        </div>

    </body>

</html>