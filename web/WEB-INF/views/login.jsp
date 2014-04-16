<%@ taglib prefix ="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Please Login.</title>
    </head>

    <body>

        <h3>User Login</h3>

        <spring:url var="authUrl" value="/static/j_spring_security_check" />

        <form method="post" action="${authUrl}">

            <fieldset>

                <table>

                    <tr>
                        <th>Username or Email</th>
                        <td>
                            <input id=username_or_email" name="j_username" type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td>
                            <input id="password" name="j_password" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input name="submit" type="submit" value="Login"/>
                        </td>
                    </tr>

                </table>

            </fieldset>

        </form>

    </body>

</html>