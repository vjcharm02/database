<html>
<head>
    <Title>Employee Details</title>
    <link  rel="stylesheet" href="design.css">
</head>

<body>
    <h2>Welcome</h2><br><br>
<form action="/database_war/api/myresource/add" method="post" target="_blank" >
    <label >Add Employee</label><br><br>

    <label>Enter Employee's ID</label><br>
    <input type="number" id="id" name="id" required><br><br>

    <label>Enter Employee's Name</label><br>
    <input type="text" id="name" name="name" required><br><br>

    <label>Enter Employee's Designation</label><br>
    <input type="text" id="designation" name="designation" required><br><br>

    <label>Enter Employee's Department</label><br>
    <input type="text" id="department" name="department" required><br><br>

    <input type="submit" value="Submit">
</form><br><br>

<form action="/database_war/api/myresource/display"  method="get" target="_blank">
    <label>To Display list of employees</label><br><br>
    <input type="submit" value="Submit"><br><br>

    <label>Data Base Manipulation</label><br><br>
    <a href="http://localhost:8080/database_war/Alter.jsp" class="button">Manipulate</a>

</form>


</body>
</html>