package com.example.reimbursemenonspringver.ui.servlet;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.shared.EmployeeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditProfileServlet", value = "/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write(getEditProfileHTML());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("name");
        String newEmail = request.getParameter("email");

        if(newName.length() > 3 && newEmail.length() > 5){
            Employee employee = (Employee) request.getSession().getAttribute("employee");
            EmployeeDao employeeDao = new EmployeeDao();
            employee.setEmail(newEmail);
            employee.setName(newName);
            employeeDao.updateEmployeeInformation(employee);
            response.sendRedirect("employee");
        }
    }

    public String getEditProfileHTML(){
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
                "            <h1>Edit Profile</h1>\n" +
                "        </header>\n" +
                "        <div class=\"dividerLight\"></div>\n" +
                "        <main>\n" +
                "            <form method=\"post\" action=\"editprofile\">\n" +
                "                <label for=\"name\">Name</label><br>\n" +
                "                <input name=\"name\" type=\"text\"><br>\n" +
                "                <label for=\"email\">Email</label><br>\n" +
                "                <input name=\"email\" type=\"text\"><br>\n" +
                "                <input type=\"submit\" value=\"Update Profile\"> \n" +
                "            </form>\n" +
                "        </main>\n" +
                "        <footer></footer>\n" +
                "    </body>\n" +
                "</html>";
    }
}

