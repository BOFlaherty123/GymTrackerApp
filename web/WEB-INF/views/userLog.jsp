<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

    <head>
        <title>Gym Tracker App</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/style/generic.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/style/form.css" rel="stylesheet">
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
                        <li><a href="${pageContext.request.contextPath}/addGymSessionForm"><span class="glyphicon glyphicon-file nav_icon"></span> Add Gym Session</a></li>
                        <li><a href="${pageContext.request.contextPath}/userLog/view.pdf"><span class="glyphicon glyphicon-list-alt nav_icon"></span> Download Results PDF</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/dashboard"><span class="glyphicon glyphicon-cog nav_icon"></span> Admin Panel</a></li>
                        <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout"><span class="glyphicon glyphicon-log-out nav_icon"></span> Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="gym_layout_padding">

            <fieldset>
                <legend><h4>Search</h4></legend>

                <form:form method="post" commandName="gymLogSearch" action="${pageContext.request.contextPath}/userLog/search">
                    <form role="form">

                        <div class="row">
                            <div class="col-md-6">
                                <form:errors path="*" cssClass="errorblock" element="div" title="Errors"/>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="form-group">
                                <form:label path="cardioExercise">By Activity</form:label>
                                <form:select path="cardioExercise" class="form-control">
                                    <form:option value="ALL" label="--- ALL ---"/>
                                    <form:options items="${exercises}" />
                                </form:select>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="form-group">
                                <form:label path="startDate">From</form:label>
                                <form:input path="startDate" id="toDate" class="form-control" placeholder="From Date"/>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="form-group">
                                <form:label path="endDate">To</form:label>
                                <form:input path="endDate" id="fromDate" class="form-control" placeholder="To Date"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-10">
                                <button type="submit" class="btn btn-default">Submit</button>
                            </div>
                        </div>

                    </form>
                </form:form>

            </fieldset>
        </div>

        <div class="table-responsive">

            <table class="table">

                <c:forEach var="record" items="${gymLogDataList}">

                    <tr class="active">
                        <th>Date</th>
                        <th>Total Duration (mins)</th>
                        <th>User Weight</th>
                        <th/>
                        <th/>
                    </tr>

                    <tr>
                        <td><b><c:out value="${record.date}"/></b></td>
                        <td><b><c:out value="${record.duration}"/></b></td>
                        <td><b><c:out value="${record.userWeight}"/></b></td>
                        <td/>
                        <td/>
                    </tr>

                    <c:if test="${record.exerciseCardio != null}">

                        <tr class="active">
                            <th>Exercise</th>
                            <th>Activity Duration</th>
                            <th>Distance</th>
                            <th>Level/Weight</th>
                            <th>Calories</th>
                        </tr>

                        <c:forEach varStatus="i" items="${record.exerciseCardio}">

                            <tr>
                                <td><c:out value="${record.exerciseCardio[i.index].exercise}"/> </td>
                                <td><c:out value="${record.exerciseCardio[i.index].distance}"/> </td>
                                <td><c:out value="${record.exerciseCardio[i.index].duration}"/> </td>
                                <td><c:out value="${record.exerciseCardio[i.index].level}"/> </td>
                                <td><c:out value="${record.exerciseCardio[i.index].calories}"/> </td>
                            </tr>

                        </c:forEach>

                    </c:if>

                    <c:if test="${record.exerciseWeight != null}">

                        <tr class="active">
                            <th>Exercise</th>
                            <th>Reps</th>
                            <th>Weight Lifted</th>
                            <th/>
                            <th/>
                        </tr>

                        <c:forEach varStatus="i" items="${record.exerciseWeight}">

                            <tr>
                                <td/>
                                <td><c:out value="${record.exerciseWeight[i.index].reps}"/> </td>
                                <td><c:out value="${record.exerciseWeight[i.index].weightLifted}"/> </td>
                                <td/>
                                <td/>
                            </tr>

                        </c:forEach>

                    </c:if>

                    <tr style="background-color: #B4B4B4;">
                        <td/>
                        <td/>
                        <td/>
                        <td/>
                        <td/>
                    </tr>

                </c:forEach>

            </table>

        </div>

    </body>

</html>