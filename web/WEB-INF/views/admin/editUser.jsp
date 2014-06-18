<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

    <head>
        <title>Admin: Edit User</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet"

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
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/admin/dashboard"> Back</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div style="padding-left: 5%; padding-right: 5%;">

            <fieldset>
                <legend><h4>Edit Users</h4></legend>

                <div class="table-responsive">

                    <table class="table table-hover">
                        <tr>
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach var="gymUser" items="${gymUserList}">
                            <tr>
                                <td><c:out value="${gymUser.username}"/> </td>
                                <td><c:out value="${gymUser.firstName}"/> </td>
                                <td><c:out value="${gymUser.lastName}"/> </td>
                                <td><c:out value="${gymUser.email}"/> </td>
                                <td><c:out value="${gymUser.role}"/> </td>
                                <td><a href="${pageContext.request.contextPath}/admin/deleteUser/<c:out value="${gymUser.username}"/>">Delete</a></td>
                            </tr>
                        </c:forEach>

                    </table>

                 </div>

            </fieldset>

        </div>

    </body>

</html>