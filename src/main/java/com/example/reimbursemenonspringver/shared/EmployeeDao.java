package com.example.reimbursemenonspringver.shared;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.data.entity.Reimbursement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = ""
            + "SELECT * FROM employee WHERE employee.email = (?) AND employee.password = (?)";
    private static final String SELECT_EMPLOYEE_BY_ID = ""
            + "SELECT * FROM employee WHERE employee.employee_id = (?)";
    private static final String INSERT_REIMBURSEMENT_REQUEST = "INSERT INTO reimbursement(employee_id, amount, receipt_link, comment) VALUES( ?, ?, ?, ?)";
    private static final String SELECT_REQUEST_BY_EMPLOYEE_ID = "SELECT * FROM reimbursement WHERE reimbursement.employee_id = (?)";

    private static final String SELECT_REQUEST_BY_EMPLOYEE_ID_AND_STATUS = "SELECT * FROM reimbursement WHERE reimbursement.employee_id = (?) AND reimbursement.status = (?)";

    private static final String UPDATE_EMPLOYEE_INFORMATION = "UPDATE employee SET name = (?), email = (?), password = (?) WHERE employee.employee_id = (?)";
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(SecretFile.dbURL, SecretFile.dbUsername, SecretFile.dbPassword);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public Employee login(String email, String password){
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employee.setEmployeeId(resultSet.getInt("employee_id"));
            employee.setDept(resultSet.getString("dept"));
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setName(resultSet.getString("name"));
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    public Employee getEmployeeById(int id){
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employee.setName(resultSet.getString("name"));
            employee.setDept(resultSet.getString("dept"));
            employee.setPassword(resultSet.getString("password"));
            employee.setEmail(resultSet.getString("email"));
            employee.setEmployeeId(id);
            return employee;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return employee;
    }

    //employee_id, amount, receipt_link, comment
    public void requestReimbursement(int employeeId, double amount, String receiptLink, String comment){
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_REIMBURSEMENT_REQUEST);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDouble(2,amount);
            preparedStatement.setString(3,receiptLink);
            preparedStatement.setString(4,comment);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Reimbursement> getReimbursementRequestsById(Employee employee) {
        ArrayList<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_REQUEST_BY_EMPLOYEE_ID);
            preparedStatement.setInt(1, employee.getEmployeeId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setEmployeeId(employee.getEmployeeId());
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setStatus(resultSet.getString("status"));
                reimbursement.setRequestTimestamp(resultSet.getString("request_timestamp"));
                reimbursement.setReceiptLink(resultSet.getString("receipt_link"));
                reimbursement.setTransactionId(resultSet.getInt("transaction_id"));
                reimbursement.setComment(resultSet.getString("comment"));
                reimbursementList.add(reimbursement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    public ArrayList<Reimbursement> getReimbursementRequestsByIdAndStatus(Employee employee, String status) {
        ArrayList<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_REQUEST_BY_EMPLOYEE_ID_AND_STATUS);
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setEmployeeId(employee.getEmployeeId());
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setStatus(resultSet.getString("status"));
                reimbursement.setRequestTimestamp(resultSet.getString("request_timestamp"));
                reimbursement.setReceiptLink(resultSet.getString("receipt_link"));
                reimbursement.setTransactionId(resultSet.getInt("transaction_id"));
                reimbursement.setComment(resultSet.getString("comment"));
                reimbursementList.add(reimbursement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }



    public void updateEmployeeInformation(Employee employee){

        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_EMPLOYEE_INFORMATION);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.setInt(4, employee.getEmployeeId());
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
