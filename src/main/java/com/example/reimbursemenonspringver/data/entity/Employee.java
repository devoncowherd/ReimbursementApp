package com.example.reimbursemenonspringver.data.entity;

public class Employee {
    int employeeId = -1;
    String name = null;
    String email = null;
    String password = null;
    String dept = null;


    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDept() {
        return dept;
    }


    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString(){
        return "ID: " + employeeId
                + "\nName: " + name
                + "\nEmail: " + email
                + "\nPassword: " + password
                + "\nDepartment: " + dept;
    }
}
