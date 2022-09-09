package com.example.reimbursemenonspringver.shared;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.data.entity.Reimbursement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDao extends EmployeeDao{
    public static final String SELECT_ALL_REIMBURSEMENTS = "" +
            "SELECT * FROM reimbursement";
    public static final String SELECT_ALL_REQUESTS_BY_STATUS = "" +
            "SELECT * FROM reimbursement WHERE status = (?)";
    public static final String SELECT_ALL_EMPLOYEES = "" +
            "SELECT * FROM employee";
    public static final String SELECT_REIMBURSEMENT_BY_EMPLOYEE_ID = "SELECT * FROM reimbursement WHERE employee_id = (?)";

    public static final String UPDATE_REIMBURSEMENT_STATUS = "" +
            "UPDATE reimbursement SET status = (?) WHERE reimbursement.transaction_id = (?)";

    public ArrayList<Reimbursement> getAllReimbursements(){
        ArrayList<Reimbursement> allReimbursementList = new ArrayList<Reimbursement>();

        try{
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(SELECT_ALL_REIMBURSEMENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setComment(resultSet.getString("comment"));
                reimbursement.setReceiptLink(resultSet.getString("receipt_link"));
                reimbursement.setTransactionId(resultSet.getInt("transaction_id"));
                reimbursement.setStatus(resultSet.getString("status"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setEmployeeId(resultSet.getInt("employee_id"));
                reimbursement.setRequestTimestamp(resultSet.getTimestamp("request_timestamp").toString());
                allReimbursementList.add(reimbursement);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return allReimbursementList;
    }

    public ArrayList<Reimbursement> getReimbursementByStatus(String status){
        ArrayList<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_REQUESTS_BY_STATUS);
            preparedStatement.setString(1,status);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setRequestTimestamp(resultSet.getTimestamp("request_timestamp").toString());
                reimbursement.setStatus(resultSet.getString("status"));
                reimbursement.setComment(resultSet.getString("comment"));
                reimbursement.setTransactionId(resultSet.getInt("transaction_id"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setEmployeeId(resultSet.getInt("employee_id"));
                reimbursement.setReceiptLink(resultSet.getString("receipt_link"));
                reimbursementList.add(reimbursement);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return reimbursementList;
    }


    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> allEmployeeList = new ArrayList<Employee>();
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_EMPLOYEES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setDept(resultSet.getString("dept"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPassword(resultSet.getString("password"));
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                allEmployeeList.add(employee);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return allEmployeeList;
    }

    public void setReimbursementStatus(String status, int reimbursementId) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_REIMBURSEMENT_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reimbursementId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reimbursement> getReimbursementByEmployeeId(Employee employee){
        ArrayList<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
        try{
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(SELECT_REIMBURSEMENT_BY_EMPLOYEE_ID);
            preparedStatement.setInt(1, employee.getEmployeeId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setRequestTimestamp(resultSet.getTimestamp("request_timestamp").toString());
                reimbursement.setStatus(resultSet.getString("status"));
                reimbursement.setComment(resultSet.getString("comment"));
                reimbursement.setTransactionId(resultSet.getInt("transaction_id"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setEmployeeId(resultSet.getInt("employee_id"));
                reimbursement.setReceiptLink(resultSet.getString("receipt_link"));
                reimbursementList.add(reimbursement);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursementList;
    }
}
