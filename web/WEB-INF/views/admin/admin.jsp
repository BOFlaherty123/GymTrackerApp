<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

    <head>
        <title>Admin: Overview</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

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
                        <li><a href="${pageContext.request.contextPath}/userLog/show"> Back</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/createUser"><span class="glyphicon glyphicon-user nav_icon"></span> Create a new User</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/editUser"><span class="glyphicon glyphicon-user nav_icon"></span> User Administration</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/editAppOptions"><span class="glyphicon glyphicon-user nav_icon"></span> Customise Options</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
            <li class="active"><a href="#appStats" data-toggle="tab">Statistics</a></li>
            <li><a href="#performance" data-toggle="tab">Performance</a></li>
            <li><a href="#audit" data-toggle="tab">Audit</a></li>
        </ul>

        <div id="my-tab-content" class="tab-content">

            <div class="tab-pane active application_stats" id="appStats">

                - App Stats Number of Users Registered / Number of Gym Sessions Recorded

            </div>

            <div class="tab-pane application_performance" id="performance">

                - Application Performance (slow queries etc)

            </div>

            <div class="tab-pane application_audit" id="audit">

                - Recorded Audits

            </div>

        </div>

    </body>

</html>