package com.example.reimbursemenonspringver.ui.servlet;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.shared.EmployeeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

@WebServlet(name = "RequestReimbursementServlet", value = "/RequestReimbursementServlet")
public class RequestReimbursementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();
        printWriter.write(getReimbursementRequestHTML());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        double amount = 0.0;

        try{
            amount = Double.parseDouble(request.getParameter("amount").toString());
        } catch(InputMismatchException e){
            e.printStackTrace();
        }
        String receiptLink = request.getParameter("receiptLink").toString();
        String description = request.getParameter("comment").toString();

        System.out.printf("\n%f %s %s\n", amount,receiptLink,description);

        if(amount > 0.0
                && receiptLink.length() > 6
                && description.length() > 6
                && employee.getEmployeeId() > -1){
            employeeDao.requestReimbursement(employee.getEmployeeId(),amount, receiptLink, description);
        }
        response.sendRedirect("employee");
    }

    public String getReimbursementRequestHTML(){
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
                "            <h1>Reimbursement Request Form</h1>\n" +
                "        </header>\n" +
                "        <div class=\"dividerLight\"></div>\n" +
                "        <main>\n" +
                "            <form method=\"post\" action=\"request\">\n" +
                "                <Label for=\"amount\">Amount</label><br>\n" +
                "                <input name=\"amount\" type=\"text\"><br>\n" +
                "                <Label for=\"receiptLink\">Receipt Link</label><br>\n" +
                "                <input name=\"receiptLink\" type=\"text\"><br>\n" +
                "                <Label>Description</label><br>\n" +
                "                <input name=\"comment\" type=\"text\"><br>\n" +
                "                <input type=\"submit\" value=\"Submit Request\"> \n" +
                "            </form>\n" +
                "        </main>\n" +
                "        <footer></footer>\n" +
                "    </body>\n" +
                "</html>";
    }
}
