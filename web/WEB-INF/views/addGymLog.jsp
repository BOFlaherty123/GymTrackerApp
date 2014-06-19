<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

    <head>
        <title>Gym Tracker App</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">

        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/form.css">

        <script>
            $(function() {
                $("#date").datepicker();
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

                <form:form method="post" commandName="gymSessionForm" action="/GymTrackerApp/addGymSession">

                    <form role="form">

                        <div class="row">

                            <div class="row">
                                <div class="col-md-6">
                                    <form:errors path="*" cssClass="errorblock" element="div" title="Errors"/>
                                </div>
                            </div>

                            <div class="gym_layout_padding">

                                <fieldset>
                                    <legend><h4>Session Information</h4></legend>

                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="date">Date</form:label>
                                            <form:input path="date" id="date" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="duration">Duration</form:label>
                                            <form:input path="duration" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="userWeight">User Weight</form:label>
                                            <form:input path="userWeight" class="form-control"/>
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
                                    <legend><h4>Activity Data</h4></legend>

                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="activity">Activity</form:label>
                                            <form:select path="activity" items="${activity}" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="activityDuration">Duration</form:label>
                                            <form:select path="activityDuration" items="${activityDuration}" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="distance">Distance</form:label>
                                            <form:input path="distance" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="levelOrWeight">Level/Weight</form:label>
                                            <form:input path="levelOrWeight" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <form:label path="calories">Calories</form:label>
                                            <form:input path="calories" class="form-control"/>
                                        </div>
                                    </div>

                                </fieldset>

                            </div>

                        </div>

                        <!--
                            Additional rows example, Needs to be removed once refactor has been completed
                            Change to List<Activity> activities and allow for a show/hide function on form input
                        -->
                        <div class="row">
                            <div class="col-md-10">
                                <button type="submit" class="btn btn-default">Submit</button>
                            </div>
                        </div>

                    </form>

                </form:form>

            </div>

    </body>

</html>