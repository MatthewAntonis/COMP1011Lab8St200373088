package advjavamidterm200373088;

import advjavamidterm200373088.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

public class EmployeeModel {
    public static class Employee {

        private int id;
        private String name;
        private Date birthday;
        private String department;
        private String email;
        private double salary;

        private static final List<String> VALID_DEPARTMENTS = Arrays.asList(
                "sales", "marketing", "accounting", "human resources", "information technology", "research and development"
        );

        public Employee(int id, String name, Date birthday, String department, String email, double salary) {
            setId(id);
            setName(name);
            setBirthday(birthday);
            setDepartment(department);
            setEmail(email);
            setSalary(salary);
        }

        private void setId(int id) {
            if (id > 0) {
                this.id = id;
            } else {
                throw new IllegalArgumentException("Invalid ID");
            }
        }

        private void setName(String name) {
            if (name != null && name.trim().length() >= 5) {
                this.name = name.trim();
            } else {
                throw new IllegalArgumentException("Invalid Name");
            }
        }

        private void setBirthday(Date birthday) {
            if (birthday != null && !birthday.after(new Date())) {
                this.birthday = birthday;
            } else {
                throw new IllegalArgumentException("Invalid Birthday");
            }
        }

        private void setDepartment(String department) {
            if (VALID_DEPARTMENTS.contains(department.toLowerCase())) {
                this.department = department;
            } else {
                throw new IllegalArgumentException("Invalid Department");
            }
        }

        private void setEmail(String email) {
            email = email.trim();
            if (email.length() >= 5 && email.contains("@") && email.contains(".")) {
                this.email = email;
            } else {
                throw new IllegalArgumentException("Invalid Email");
            }
        }

        private void setSalary(double salary) {
            if (salary > 0) {
                this.salary = salary;
            } else {
                throw new IllegalArgumentException("Invalid Salary");
            }
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public String getDepartment() {
            return department;
        }

        public String getEmail() {
            return email;
        }

        public double getSalary() {
            return salary;
        }
    }

    public Map<Integer, Employee> fetchEmployees() {
        Map<Integer, Employee> employees = new HashMap<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT id, name, birthday, department, email, salary FROM employees WHERE id > 0";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getDouble("salary")
                );
                employees.put(employee.getId(), employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT id, name, birthday, department, email, salary FROM employees WHERE id > 0";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getDouble("salary")
                );
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public boolean deleteEmployeeById(int id) {
        String deleteQuery = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
