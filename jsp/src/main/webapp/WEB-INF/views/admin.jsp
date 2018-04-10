<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Records</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }
    </style>
    <script>
        $(document).ready(function() {
            getItems();
        });

        function getItems() {
            $.ajax('./', {
                method: 'get',
                dataType: "json",
                complete: function(data) {
                    document.getElementById("items").innerHTML = "";
                    <c:forEach items="${orders}" var="el">
                    var line = "<tr>"
                        + "<td><c:out value="${el.user.login}"/></td>"
                        + "<td><c:out value="${el.comeIn}"/></td>"
                        + "<td><c:out value="${el.goAway}"/></td>"
                        + "<td><c:out value="${el.status}"/></td>"
                        + "<td><c:out value="${el.totaltime}"/></td></tr>";
                    document.getElementById("items").innerHTML += line;
                    </c:forEach>
                }
            })
        }

    </script>


</head>
<body>
<a id="add" href="./"><h4><u>Back</u></h4></a>
<div class="container" style="margin-left: 10px; padding-left: 10px; margin-right: 10px; padding-right: 10px">
    <div class="row">
        <div class="col-md-10">
            <h3 align="center">Staff Worktime Registration</h3>
            <table class="table table-bordered">
                <thead style="background: #53e1ff">
                <tr>
                    <td>User</td>
                    <td>Come In</td>
                    <td>Go Away</td>
                    <td>Status</td>
                    <td>Total Work Time</td>
                </tr>
                </thead>
                <tbody id="items"></tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
