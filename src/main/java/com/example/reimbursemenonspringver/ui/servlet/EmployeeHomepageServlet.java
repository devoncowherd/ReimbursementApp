package com.example.reimbursemenonspringver.ui.servlet;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.data.entity.Reimbursement;
import com.example.reimbursemenonspringver.shared.EmployeeDao;
import com.example.reimbursemenonspringver.shared.ManagerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "EmployeeHomepageServlet", value = "/EmployeeHomepageServlet")
public class EmployeeHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(getEmployeeHomepageHTML(employee));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String logout = request.getParameter("hiddenLogout");
        String update = request.getParameter("hiddenEditProfile");
        if(logout.equals("true")){
            System.out.println("logout = true");
            request.getSession().removeAttribute("manager");
            response.sendRedirect(".");
        } else if(update == "true") {
            System.out.println("true");
            response.sendRedirect("editprofile");
        } else {
            response.sendRedirect("request");
        }
    }

    public String getEmployeeHomepageHTML(Employee employee){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "\n" +
                "            <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "            <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "            <link href=\"https://fonts.googleapis.com/css2?family=Oswald&display=swap\" rel=\"stylesheet\">\n" +
                "            <style>\n" +
                "                body {\n" +
                "                    background-color: rgba(20,20,20);\n" +
                "                    color: whitesmoke;\n" +
                "                    font-family: 'Oswald', sans-serif;\n" +
                "                    text-align: center;\n" +
                "                    font-size: 1.5em;\n" +
                "                }\n" +
                "\n" +
                "                .dividerLight {\n" +
                "                    border-bottom: dotted white 2px;\n" +
                "                    width: 100%;\n" +
                "                }\n" +
                "\n" +
                "                .dividerDark {\n" +
                "                    border-bottom: dotted rgb(20,20,20) 2px;\n" +
                "                    width: 100%;\n" +
                "                }\n" +
                "                main {\n" +
                "                    text-align: left;\n" +
                "                    margin-top: 5%;\n" +
                "                    margin-left:25%;\n" +
                "                    margin-right:25%;\n" +
                "                    margin-bottom:5%;\n" +
                "\n" +
                "                }\n" +
                "\n" +
                "                form {\n" +
                "                    margin: 0px;\n" +
                "                    padding:0px;\n" +
                "                }\n" +
                "\n" +
                "                h2 {\n" +
                "                    margin: 0px;\n" +
                "                }\n" +
                "\n" +
                "                input {\n" +
                "\n" +
                "                    font-size:1em;\n" +
                "                }\n" +
                "\n" +
                "            </style>\n" +
                "        </head>\n" +
                "    <body>\n" +
                "        <header>\n" +
                "            <h1>Welcome, $ate Employee!</h1>\n" +
                "            <div class=\"dividerLight\"></div>\n" +
                "        </header>\n" +
                "        <main>\n" +
                "            <input class=\"profileButton\" type=\"submit\" value=\"View Profile\">\n" +
                "            <table class=\"profile\" hidden>\n" +
                "                    <td>EmployeeId</td>\n" +
                "                    <td>Name</td>\n" +
                "                    <td>Email</td>\n" +
                "" + getEmployeeProfile(employee) +
                "            </table>\n" +
                "            <form method=\"get\" action=\"editprofile\"><input type=\"text\" name=\"hiddenEditProfile\" hidden><input class=\"editProfileButton\" value=\"Edit Profile\" type=\"submit\"></form>\n" +
                "            <input class=\"allPendingButton\" type=\"submit\" value=\"View Pending\"><br>\n" +
                "            <table  class=\"allPendingTable\" hidden>\n" +
                "                    <td>TransID</td>\n" +
                "                    <td>EmpID</td>\n" +
                "                    <td>Amount</td>\n" +
                "                    <td>ReceiptLink</td>\n" +
                "                    <td>Desc.</td>\n" +
                "                    <td>Status</td>\n" +
                "                    <td>Date</td>\n" + getEmployeePendingRequests(employee) +
                "            </table >\n" +
                "            <input class=\"allResolvedButton\" type=\"submit\" value=\"View Resolved\"><br>\n" +
                "            <table  class=\"allResolvedTable\" hidden>\n" +
                "                    <td>TransID</td>\n" +
                "                    <td>EmpID</td>\n" +
                "                    <td>Amount</td>\n" +
                "                    <td>ReceiptLink</td>\n" +
                "                    <td>Desc.</td>\n" +
                "                    <td>Status</td>\n" +
                "                    <td>Date</td>\n" + getAllApprovedRequests(employee) + getAllDeniedRequests(employee) +
                "            </table >\n" +
                "            <form method=\"get\" action=\"request\"><input type=\"submit\" value=\"New Request\"></form>\n" +
                "            <form method=\"post\" action=\".\"><input type=\"submit\" value=\"Logout\" class=\"logoutButton\"><input name=\"hiddenLogout\" class=\"hiddenLogout\" type=\"text\" hidden></form>" +
                "        </main>\n" +
                "        <footer></footer>\n" +
                "    </body>\n" +
                "</html>\n" +
                "<script>\n" +
                "    console.log(\"Script Loaded\");\n" +
                "    let allPendingTable = document.querySelector(\".allPendingTable\");\n" +
                "    let allResolvedTable = document.querySelector(\".allResolvedTable\");\n" +
                "    let allPendingButton = document.querySelector(\".allPendingButton\");\n" +
                "    let allResolvedButton = document.querySelector(\".allResolvedButton\");\n" +
                "    let profile = document.querySelector(\".profile\");\n" +
                "    let profileButton = document.querySelector(\".profileButton\");\n" +
                "    let logoutButton = document.querySelector(\".logoutButton\")\n" +
                "    let editProfileButton = document.querySelector(\".editProfileButton\")" +

                "\n" +
                "    profileButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(profile.hasAttribute(\"hidden\")){\n" +
                "            profile.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            profile.setAttribute(\"hidden\", \"true\");\n" +
                "        }\n" +
                "    });\n" +
                "\n" +
                "    allPendingButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(allPendingTable.hasAttribute(\"hidden\")){\n" +
                "            allPendingTable.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            allPendingTable.setAttribute(\"hidden\",\"true\")\n" +
                "        }\n" +
                "    });\n" +
                "\n" +
                "    allResolvedButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(allResolvedTable.hasAttribute(\"hidden\")){\n" +
                "            allResolvedTable.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            allResolvedTable.setAttribute(\"hidden\",\"true\")\n" +
                "        }\n" +
                "    });\n" +
                "   editProfileButton.addEventListener(\"click\", ()=> {" +
                "   hiddenEditProfile.setAttribute(\"value\", \"true\")})" +
                "\n" +
                "</script>";
    }

    public String getEmployeeProfile(Employee employee){
        return "" +
                "<tr>" +
                "<td>" + employee.getEmployeeId() + "</td>" +
                "<td>" + employee.getName() + "</td>" +
                "<td>" + employee.getEmail() + "</td>";
    }

    public String getEmployeePendingRequests(Employee employee){
        EmployeeDao employeeDao = new EmployeeDao();
        ArrayList<Reimbursement> reimbursementList = employeeDao.getReimbursementRequestsByIdAndStatus(employee, "pending");
        String employeeReimbursementTable = "";
        for(Reimbursement reimbursement : reimbursementList){
            employeeReimbursementTable += "<tr>" +
                    "<tr>" +
                    "<td>" + reimbursement.getTransactionId() + "</td>" +
                    "<td>" + reimbursement.getEmployeeId() + "</td>" +
                    "<td>" + reimbursement.getAmount() + "</td>" +
                    "<td>" + reimbursement.getReceiptLink() + "</td>" +
                    "<td>" + reimbursement.getComment() + "</td>" +
                    "<td>" + reimbursement.getStatus() + "</td>" +
                    "<td>" + reimbursement.getRequestTimestamp() + "</td>" +
                    "</tr>";
        }


        return employeeReimbursementTable;
    }

    public String getAllApprovedRequests(Employee employee){
        EmployeeDao employeeDao = new EmployeeDao();
        ArrayList<Reimbursement> allReimbursementList = employeeDao.getReimbursementRequestsByIdAndStatus(employee,"approved");
        String totalPendingList = "";
        for(Reimbursement reimbursement : allReimbursementList){
            String newReimbursemenet =
                    "<tr>" +
                            "<td>" + reimbursement.getTransactionId() + "</td>" +
                            "<td>" + reimbursement.getEmployeeId() + "</td>" +
                            "<td>" + reimbursement.getAmount() + "</td>" +
                            "<td>" + reimbursement.getReceiptLink() + "</td>" +
                            "<td>" + reimbursement.getComment() + "</td>" +
                            "<td>" + reimbursement.getStatus() + "</td>" +
                            "<td>" + reimbursement.getRequestTimestamp() + "</td>" +
                            "</tr>";
            totalPendingList += newReimbursemenet;

        }
        return totalPendingList;
    }

    public String getAllDeniedRequests(Employee employee){
        EmployeeDao employeeDao = new EmployeeDao();
        ArrayList<Reimbursement> allReimbursementList = employeeDao.getReimbursementRequestsByIdAndStatus(employee,"denied");
        String totalPendingList = "";
        for(Reimbursement reimbursement : allReimbursementList){
            String newReimbursemenet =
                    "<tr>" +
                            "<td>" + reimbursement.getTransactionId() + "</td>" +
                            "<td>" + reimbursement.getEmployeeId() + "</td>" +
                            "<td>" + reimbursement.getAmount() + "</td>" +
                            "<td>" + reimbursement.getReceiptLink() + "</td>" +
                            "<td>" + reimbursement.getComment() + "</td>" +
                            "<td>" + reimbursement.getStatus() + "</td>" +
                            "<td>" + reimbursement.getRequestTimestamp() + "</td>" +
                            "</tr>";
            totalPendingList += newReimbursemenet;

        }
        return totalPendingList;
    }
}
