<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check In</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <script>

        function come() {
            $.ajax({
                url: "come",
                method: "get",
                dataType: 'json'
//                complete : function(data){
//                    alert("Вы отметились о приходе!");
//                }
            });
        }

        function away() {
            $.ajax({
                url: "away",
                method: "get",
                dataType: 'json'
//                complete : function(data){
//                    alert("Вы отметились о выходу!");
//                }
            });
        }

        function finish() {
            $.ajax({
                url: "finish",
                method: "get",
                dataType: 'json'
//                complete : function(data){
//                    alert("Вы отметились об окончании рабочего дня!");
//                }
            });
        }

    </script>

</head>
<body>
<div class="container" style="margin-left: 10px; padding-left: 10px; margin-right: 10px; padding-right: 10px">
    <div class="row">
        <a id="add" href="./admin"><h4><u>Admin Panel</u></h4></a>
        <div align="center" class="col-md-12">
            <br>
            <h3 align="center">Выберите действие</h3>
            <input type="button" width="150" class="btn btn-success" value="Пришел" onclick="return come();">&nbsp;&nbsp;
            <input type="button" width="150" class="btn btn-success" value="Ушел" onclick="return away();">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="finish" type="button"  class="btn btn-success" value="Отработал" onclick="return finish();">
        </div>
    </div>
</div>
</body>
</html>
