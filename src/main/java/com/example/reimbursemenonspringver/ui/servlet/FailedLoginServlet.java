package com.example.reimbursemenonspringver.ui.servlet;

import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.shared.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FailedLoginServlet extends HttpServlet {
    EmployeeDao employeeDao = new EmployeeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write(getFailedLoginHTML());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginEmail = request.getParameter("loginEmail");
        System.out.println(loginEmail);
        String loginPassword = request.getParameter("loginPassword");
        System.out.println(loginPassword);
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.login(loginEmail, loginPassword);
        if (employee == null) {
            response.sendRedirect("failed");
            PrintWriter writer = response.getWriter();

        } else if (employee.getDept().equals("MGMT")) {
            request.setAttribute("manager", employee);
            response.sendRedirect("manager");
        } else {
            request.setAttribute("employee", employee);
            response.sendRedirect("employee");
        }
    }

    public String getFailedLoginHTML(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: rgba(20,20,20);\n" +
                "            color: whitesmoke;\n" +
                "            font-family: 'Oswald', sans-serif;\n" +
                "            text-align: center;\n" +
                "            font-size: 1.5em;\n" +
                "        }\n" +
                "\n" +
                "        .dividerLight {\n" +
                "            border-bottom: dotted white 2px;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        .dividerDark {\n" +
                "            border-bottom: dotted rgb(20,20,20) 2px;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "        main {\n" +
                "            text-align: left;\n" +
                "            margin-top: 5%;\n" +
                "            margin-left:25%;\n" +
                "            margin-right:25%;\n" +
                "            margin-bottom:5%;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        form {\n" +
                "            max-width: 500px;\n" +
                "            background-color: whitesmoke;\n" +
                "            color: rgb(20,20,20);\n" +
                "            padding:24px;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            margin: 0px;\n" +
                "        }\n" +
                "\n" +
                "        input {\n" +
                "\n" +
                "            font-size:1em;\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            bottom: 0px;\n" +
                "            width: 100%;\n" +
                "            color: red;\n" +
                "            margin-left: 0px;\n" +
                "            margin-right: 0px;\n" +
                "            margin-bottom: 0px;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <script defer>\n" +
                "\n" +
                "    </script>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Oswald&display=swap\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<header>\n" +
                "    <h1>$ate</h1>\n" +
                "</header>\n" +
                "<div class=\"dividerLight\"></div>\n" +
                "<main>\n" +
                "    <form name=\"loginForm\" method=\"post\" action=\"login\">\n" +
                "        <h2>Login</h2>\n" +
                "            <div class=\"dividerDark\"></div>\n" +
                "            <label for=\"loginEmail\">Email:</label>\n" +
                "            <br>\n" +
                "            <input type=\"text\"\n" +
                "             name=\"loginEmail\"" +
                "                   id=\"loginEmail\"\n" +
                "                   class=\"loginEmail\"\n" +
                "                   min=\"5\" max=\"150\"\n" +
                "            >\n" +
                "            <br>\n" +
                "            <label for=\"loginPassword\">Password:</label>\n" +
                "            <br>\n" +
                "            <input id=\"loginPassword\" name=\"loginPassword\" type=\"password\" min=\"5\" max=\"150\">\n" +
                "            <br>\n" +
                "            <input class=\"loginSubmit\" type=\"submit\" value=\"Submit\">\n" +
                "    </form>\n" +
                "</main>\n" +
                "<footer>\n" +
                "    <p>Login Unsuccessful. Please Check Email And Password</p>\n" +
                "</footer>\n" +
                "</body>\n" +
                "</html>";
    }
}
