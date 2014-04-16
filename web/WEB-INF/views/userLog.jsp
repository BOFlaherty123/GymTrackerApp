<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Log</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/userLog.css">

    </head>

    <body>

        <div id="header">
            <h3>Gym Tracker</h3>
            <br/>
            <a href="/GymTrackerApp/static/j_spring_security_logout">Logout</a>
        </div>


        <div id="results">

            <table id="gym_results">
                <tr>
                    <th>Date</th>
                    <th>Duration</th>
                    <th>Activity</th>
                    <th>Activity Duration</th>
                    <th>Level/Weight</th>
                    <th>User Weight</th>
                </tr>

                <c:forEach var="record" items="${gymRecords}">
                    <tr>
                        <td><c:out value="${record.date}"/> </td>
                        <td><c:out value="${record.duration}"/> </td>
                        <td><c:out value="${record.activity}"/> </td>
                        <td><c:out value="${record.activityDuration}"/> </td>
                        <td><c:out value="${record.levelOrWeight}"/> </td>
                        <td><c:out value="${record.userWeight}"/> </td>
                    </tr>
                </c:forEach>

            </table>

        </div>
    </body>

</html>