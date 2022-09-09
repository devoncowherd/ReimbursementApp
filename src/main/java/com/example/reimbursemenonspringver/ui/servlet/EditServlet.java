package com.example.reimbursemenonspringver.ui.servlet;

import com.example.reimbursemenonspringver.data.entity.Manager;
import com.example.reimbursemenonspringver.shared.ManagerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(getEditPageHTML());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManagerDao managerDao = new ManagerDao();
        String status = request.getParameter("selectStatus");
        String reimbursementId = request.getParameter("reimbursementId");
        System.out.println(status);
        System.out.println(reimbursementId);
        managerDao.setReimbursementStatus(status, Integer.parseInt(reimbursementId));
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(getEditPageHTML());

    }

    private String getEditPageHTML(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en-US\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "        <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "        <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "        <link href=\"https://fonts.googleapis.com/css2?family=Oswald&display=swap\" rel=\"stylesheet\">\n" +
                "        <style>\n" +
                "            body {\n" +
                "                background-color: rgb(20,20,20);\n" +
                "                color: whitesmoke;\n" +
                "                font-family: 'Oswald', sans-serif;\n" +
                "                text-align: center;\n" +
                "                font-size: 2.5em;\n" +
                "            }\n" +
                "\n" +
                "            a {\n" +
                "                color: whitesmoke;\n" +
                "                font-family: 'Oswald', sans-serif;\n" +
                "                text-align: center;\n" +
                "                font-size: 2.5em;\n" +
                "                text-decoration: none;\n" +
                "                color: rgb(20,20,20)\n" +
                "            }\n" +
                "\n" +
                "            button {\n" +
                "                font-size: 1.5em;\n" +
                "            }\n" +
                "\n" +
                "            .dividerLight {\n" +
                "            border-bottom: dotted white 2px;\n" +
                "            width: 100%;\n" +
                "            }\n" +
                "\n" +
                "            select, input {\n" +
                "                margin: 16px;\n" +
                "                padding: 16px;\n" +
                "                font-size: 1.5em;\n" +
                "            }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <header>\n" +
                "            <h1>Edit Reimbursement</h1>\n" +
                "        </header>\n" +
                "        <main>\n" +
                "            <form method=\"post\" action=\"edit\">\n" +
                "            <input name=\"reimbursementId\" type=\"text\" placeholder=\"Enter Reimbursement Id\"> \n" +
                    "            <select name=\"selectStatus\">\n" +
                    "                <option>approved</option>\n" +
                    "                <option>denied</option>\n" +
                    "            </select>\n" +
                    "                <input type=\"submit\" value=\"Update\"> \n" +
                "            </form>\n" +
                "\n" +
                "        </main>\n" +
                "        <footer></footer>\n" +
                "    </body>\n" +
                "    <script>\n" +
                "    </script>\n" +
                "</html>";
    }
}
