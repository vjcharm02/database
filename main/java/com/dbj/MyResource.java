package com.dbj;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;

@Path("/myresource")

public class MyResource {

    private Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }

        String url = "jdbc:mysql://localhost:3306/employeedetail";
        String username = "root";
        String password = "@Zoho2304";
        return DriverManager.getConnection(url, username, password);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addEmployee(@FormParam("id") int id,
                              @FormParam("name") String name,
                              @FormParam("department") String department,
                              @FormParam("designation") String designation)
    {
        try {
            Connection conn = connect();

            PreparedStatement ps = conn.prepareStatement("SELECT Id FROM data");
            ResultSet rs = ps.executeQuery();

            String str = String.valueOf(id);
            while (rs.next()) {
                String vj= rs.getString("Id");
                if(vj.equals(str)){
                    return "ID already exists Try again " + vj;
                }

            }


            PreparedStatement statement = conn.prepareStatement("INSERT INTO data (Id, EName, Designation, Department) VALUES (?, ?, ?,?)");
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, designation);
            statement.setString(4, department);
            statement.executeUpdate();
            conn.close();
            return "Employee Added Successfully";

        }
        catch (SQLException e) {
            String j  = e.toString();
            return j;

        }
    }


    @POST
        @Path("/delete")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String delete( @FormParam("id") int id){
        try{
            Connection conn = connect();
            PreparedStatement ps= conn.prepareStatement("SELECT Id FROM data");
            ResultSet rs =ps.executeQuery();
            String str = String.valueOf(id);
            while(rs.next()){
                String vj= rs.getString("Id");
                if(str.equals(vj)){
                    PreparedStatement ps1 = conn.prepareStatement("DELETE FROM data WHERE Id = ? ");
                    ps1.setInt(1,id);
                    ps1.executeUpdate();
                    conn.close();
                    return id + " Deleted Successfully";
                }

            }
            return "Id Doesn't Exists Give valid ID";
        }
        catch(Exception e){
            String err  = e.toString();
            return err;
            }
        }


    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String udpate(@FormParam("id")int id,
                         @FormParam("designation") String design,
                         @FormParam("department") String dept){
        String t=String.valueOf(id);
        try{
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement("Select Id FROM data");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                String str= rs.getString("id");

                if(str.equals(t)){

                    if(design.isEmpty() == false){
                        PreparedStatement ps1= conn.prepareStatement("UPDATE data SET Designation = ? WHERE Id = ?");
                        ps1.setString(1, design);
                        ps1.setInt(2, id);
                        ps1.executeUpdate();
                    }

                    if(dept.isEmpty() == false){
                        PreparedStatement ps2= conn.prepareStatement("UPDATE data SET Department = ? WHERE Id = ?");
                        ps2.setString(1, dept);
                        ps2.setInt(2, id);
                        ps2.executeUpdate();
                    }

                    conn.close();

                return "Updated Successfully";
                }

            }

            return  id + " doesn't Exists";
        }
        catch(Exception e){
        String err= e.toString();
        return err;
        }

    }
    

    @GET
    @Path("/display")
    @Produces(MediaType.TEXT_HTML)
    public String display()
    {
        ArrayList<String[]> records = new ArrayList<>();

        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM data");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString("Id"), rs.getString("EName"), rs.getString("Designation"), rs.getString("Department")};
                records.add(data);
            }
        }
        catch (SQLException e) {
            String j  = e.toString();
            return j;
        }

        StringBuilder newString = new StringBuilder("<!DOCTYPE html>");
        newString.append("<html>");
        newString.append("<head><title>Employee List</title></head>");
        newString.append("<body style=': center;'><h1 style='text-align: center;'>List of Employees</h1>");
        newString.append("<table border='2' style='width: 60%; border-collapse: collapse;  margin: 0 auto; ' >");
        newString.append("<tr><th style='width: 10%;'>Id</th><th style='width: 17%;'>Name</th><th style='width: 16-%;'>Designation</th><th style='width: 17%;'>Department</th></tr>");
        for (String[] record : records) {
            newString.append("<tr>");
            for (String value : record) {
                newString.append("<td style='text-align: center;'>").append(value).append("</td>");
            }
            newString.append("</tr>");
        }
        newString.append("</table>");
        newString.append("</body></html>");

        return newString.toString();
    }

//Testing push on git
}

