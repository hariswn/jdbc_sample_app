package com.tekicons;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("MySQL JDBC Connection Testing !!!");

        List<Employee> result = new ArrayList<>();

        String SQL_SELECT = "Select * from employee";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", "Harry@12345");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("emp_id");
                String name = resultSet.getString("emp_name");
                String address = resultSet.getString("emp_address");
                long salary = resultSet.getLong("salary");

                Employee obj = new Employee();
                obj.setId(id);
                obj.setName(name);
                obj.setAddress(address);
                obj.setSalary(salary);

                result.add(obj);

            }
            result.forEach(x -> System.out.println(x));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
