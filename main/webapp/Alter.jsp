
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations</title>
    <link  rel="stylesheet" href="design.css">
</head>
<body>

<form class="form-inline" action="/database_war/api/myresource/update" method="POST">
    <label>To Update Data Base</label><br><br>
    <label>Enter ID : </label><br>
    <input type="number" name="id" required><br><br>
    <label>Enter the new Designation : </label><br>
    <input type="text" name="designation" ><br><br>
    <label>Enter the Deparment</label><br>
    <input type="text" name="department" ><br><br>

    <input type="submit" value="Submit"><br><br>

</form>

<form class="form-inline" action="/database_war/api/myresource/delete" method="POST">
    <label>To Delete from database</label><br><br>

    <label>Enter ID :</label><br>
    <input type="number" name="id" required><br><br>
    <input type="submit" value="Submit"><br><br>

</form>

</body>
</html>
