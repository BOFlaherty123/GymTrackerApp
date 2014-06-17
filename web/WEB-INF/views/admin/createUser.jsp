<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Admin: Create User</title>

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
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/userLog/show">
                        <img style="max-width:80px; margin-top: -15px;" src="${pageContext.request.contextPath}/resources/images/gymTrackerLogo.png">
                    </a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/admin/dashboard">Back</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- submitUser -->
        <div style="padding-left: 15%; padding-right: 15%;">

            <h3>Create User Form</h3>

            <form:form method="post" commandName="gymUser" action="/GymTrackerApp/admin/submitUser">

                <form role="form">

                    <div class="row">
                        <form:errors path="*" cssClass="errorblock" element="div" title="Errors"/>
                    </div>

                    <fieldset>
                        <legend><h4>Personal Information</h4></legend>

                        <div class="form-group">
                            <form:input path="firstName" class="form-control" placeholder="Enter First Name"/>
                        </div>

                        <div class="form-group">
                            <form:input path="lastName" class="form-control" placeholder="Enter Last Name"/>
                        </div>

                        <div class="form-group">
                            <form:input path="age" class="form-control" placeholder="Enter Age"/>
                        </div>

                        <div class="form-group">
                            <form:input path="email" class="form-control" placeholder="Enter Email"/>
                        </div>

                    </fieldset>

                    <fieldset>
                        <legend><h4>Account Information</h4></legend>

                        <div class="form-group">
                            <form:input path="username" class="form-control" placeholder="Enter Username"/>
                        </div>

                        <div class="form-group">
                            <form:input type="password" path="password" class="form-control" placeholder="Enter Password"/>
                        </div>

                        <div class="form-group">
                            <form:input type="password" path="confirmPassword" class="form-control" placeholder="Confirm Password"/>
                        </div>

                        <div class="form-group">
                            <form:select path="role" items="${userRoles}" class="form-control"/>
                        </div>

                    </fieldset>

                    <div class="row">
                        <input type="submit" value="Submit"/>
                    </div>

                </form>

            </form:form>

        </div>

    </body>
</html>
