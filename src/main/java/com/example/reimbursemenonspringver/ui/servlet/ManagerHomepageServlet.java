package com.example.reimbursemenonspringver.ui.servlet;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.data.entity.Reimbursement;
import com.example.reimbursemenonspringver.shared.ManagerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "HomepageServlet", value = "/HomepageServlet")
public class ManagerHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getSession().getAttribute("manager").toString());
        if(request.getSession().getAttribute("manager") == null){
            response.sendRedirect("failed");
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(getEntirePage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String logout = request.getParameter("hiddenLogout");
        String update = request.getParameter("hiddenUpdate");

        if(logout.equals("true")){
            System.out.println("logout = true");
            request.getSession().removeAttribute("manager");
            response.sendRedirect(".");
        } else {
            response.sendRedirect("edit");
        }

    }

    public String getAllEmployeeTable(){
        ManagerDao managerDao = new ManagerDao();
        ArrayList<Employee> allEmployeeList = managerDao.getAllEmployees();
        String totalEmployeeList = "";
        for(Employee employee : allEmployeeList){
            String newEmployee =
               "<tr>" +
                "<td>" + employee.getEmployeeId() + "</td>" +
                "<td>" + employee.getName() + "</td>" +
                "<td>" + employee.getEmail() + "</td>" +
                "<td>" + employee.getDept() + "</td>" +
                "</tr>";

            totalEmployeeList += newEmployee;
        }
        return totalEmployeeList;
    }

    public String getAllReimbursementTable(){
        ManagerDao managerDao = new ManagerDao();
        ArrayList<Reimbursement> allReimbursementList = managerDao.getAllReimbursements();
        String totalReimbursementList = "";
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
            totalReimbursementList += newReimbursemenet;

        }
        return totalReimbursementList;
    }

    public String getAllPendingRequests(){
        ManagerDao managerDao = new ManagerDao();
        ArrayList<Reimbursement> allReimbursementList = managerDao.getReimbursementByStatus("pending");
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

    public String getAllApprovedRequests(){
        ManagerDao managerDao = new ManagerDao();
        ArrayList<Reimbursement> allReimbursementList = managerDao.getReimbursementByStatus("approved");
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

    public String getAllDeniedRequests(){
        ManagerDao managerDao = new ManagerDao();
        ArrayList<Reimbursement> allReimbursementList = managerDao.getReimbursementByStatus("denied");
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

    public String getEntirePage(){
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
                "th , td, tr {width: 240px; text-align: left; margin: 8px; padding: 8px;}" +
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
                "        <header></header>\n" +
                "        <main>\n" +
                "            <h3>Welcome, $ate Management!</h3>\n" +
                "            <div class=\"dividerLight\"></div>\n" +
                "            <br>\n" +
                "            <input type=\"submit\" value=\"Show All Employees\" class=\"allEmployeeButton\">\n" +
                "            <br>\n" +
                "            <!--Chunk1-->\n" +
                "            <table class=\"allEmployeeTable\">\n" +
                "                    <td>EmpID</td>\n" +
                "                    <td>Name</td>\n" +
                "                    <td>Email</td>\n" +
                "                    <td>Dept.</td>\n" + getAllEmployeeTable() +
                "            </table>\n" +
                "            <!--Chunk2-->\n" +
                "            <input type=\"submit\" value=\"Show All Reimbursement Requests\" class=\"allReimbursementButton\">\n" +
                "            <br>\n" +
                "            <table class=\"allReimbursementTable\">\n" +
                "                    <td>ReimbursementId</td>\n" +
                "                    <td>EmpID</td>\n" +
                "                    <td>Amount</td>\n" +
                "                    <td>ReceiptLink</td>\n" +
                "                    <td>Desc.</td>\n" +
                "                    <td>Status</td>\n" +
                "                    <td>Date</td>\n" + getAllReimbursementTable() +
                "            </table>\n" +
                "            <!--Chunk3-->\n" +
                "            <input type=\"submit\" value=\"Show All Pending Reimbursements\" class=\"allPendingButton\">\n" +
                "            <br>\n" +
                "            <table  class=\"allPendingTable\">\n" +
                "                    <td>ReimbursementId</td>\n" +
                "                    <td>EmpID</td>\n" +
                "                    <td>Amount</td>\n" +
                "                    <td>ReceiptLink</td>\n" +
                "                    <td>Desc.</td>\n" +
                "                    <td>Status</td>\n" +
                "                    <td>Date</td>\n" + getAllPendingRequests() +
                "            </table >\n" +
                "            <!--Chunk4-->\n" +
                "            <input type=\"submit\" value=\"Show All Resolved Requests\" class=\"allResolvedButton\">\n" +
                "            <br>\n" +
                "            <table class=\"allResolvedTable\">\n" +
                "                    <td>ReimbursementId</td>\n" +
                "                    <td>EmpID</td>\n" +
                "                    <td>Amount</td>\n" +
                "                    <td>ReceiptLink</td>\n" +
                "                    <td>Desc.</td>\n" +
                "                    <td>Status</td>\n" +
                "                    <td>Date</td>\n" + getAllApprovedRequests() + getAllDeniedRequests() +
                "            </table>\n" +
                "            <!--Chunk5-->\n" +
                "            <form action=\"edit\" method=\"get\"> " +
                "                <input type=\"submit\" value=\"Update Reimbursement Request\">\n" +
                "            </form>\n" +
                "            <!--Chunk6-->\n" +
                "            <form method=\"post\" action=\".\"><input type=\"submit\" value=\"Logout\" class=\"logoutButton\"><input name=\"hiddenLogout\" class=\"hiddenLogout\" type=\"text\" hidden></form>" +
                "            \n" +
                "        </main>\n" +
                "        <footer></footer>\n" +
                "    </body>\n" +
                "</html>\n" +
                "<script>\n" +
                "    console.log(\"Script Loaded\");\n" +
                "    let allEmployeeTable = document.querySelector(\".allEmployeeTable\");\n" +
                "    let allReimbursementTable = document.querySelector(\".allReimbursementTable\");\n" +
                "    let allPendingTable = document.querySelector(\".allPendingTable\");\n" +
                "    let allResolvedTable = document.querySelector(\".allResolvedTable\");\n" +
                "    \n" +
                "    let allEmployeeButton = document.querySelector(\".allEmployeeButton\");\n" +
                "    let allReimbursementButton = document.querySelector(\".allReimbursementButton\");\n" +
                "    let allPendingButton = document.querySelector(\".allPendingButton\");\n" +
                "    let allResolvedButton = document.querySelector(\".allResolvedButton\");\n" +
                "    let logoutButton = document.querySelector(\".logoutButton\")\n" +
                "\n" +
                "\n" +
                "    \n" +
                "    allEmployeeTable.setAttribute(\"hidden\", \"true\");\n" +
                "    allReimbursementTable.setAttribute(\"hidden\", \"true\");\n" +
                "    allPendingTable.setAttribute(\"hidden\", \"true\");\n" +
                "    allResolvedTable.setAttribute(\"hidden\", \"true\");\n" +
                "\n" +
                "    allEmployeeButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(allEmployeeTable.hasAttribute(\"hidden\")){\n" +
                "            allEmployeeTable.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            allEmployeeTable.setAttribute(\"hidden\",\"true\")\n" +
                "        }\n" +
                "    });\n" +
                "    allReimbursementButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(allReimbursementTable.hasAttribute(\"hidden\")){\n" +
                "            allReimbursementTable.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            allReimbursementTable.setAttribute(\"hidden\",\"true\")\n" +
                "        }\n" +
                "    });\n" +
                "    allPendingButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(allPendingTable.hasAttribute(\"hidden\")){\n" +
                "            allPendingTable.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            allPendingTable.setAttribute(\"hidden\",\"true\")\n" +
                "        }\n" +
                "    });\n" +
                "    allResolvedButton.addEventListener(\"click\", ()=>{\n" +
                "        console.log(\"clicked\");\n" +
                "        if(allResolvedTable.hasAttribute(\"hidden\")){\n" +
                "            allResolvedTable.removeAttribute(\"hidden\");\n" +
                "        } else {\n" +
                "            allResolvedTable.setAttribute(\"hidden\",\"true\")\n" +
                "        }\n" +
                "    });\n" +
                "\n" +
                "\n" +
                "</script>";
    }
}
