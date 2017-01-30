package Tracking.user.servlet;

/**
 * Created by yale on 23/09/16.
 */

import Tracking.user.action.UserAction;
import Tracking.user.util.Status;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        //check if uname and pass are not null
        if(uname.equals(null)){
            request.getSession().setAttribute("msg", "username empty, please enter username.");
            response.sendRedirect("/index.jsp");

        } else if(pass.equals(null)){
            request.getSession().setAttribute("msg", "password empty, please enter password.");
            response.sendRedirect("/index.jsp");

        }

        String loginStatus = UserAction.login(uname, pass);


        //if login succeeds
        if (loginStatus.contains(Status.SUCC_MSG)) {
            request.getSession().setAttribute("user", uname);
            response.sendRedirect("/home.jsp");
        }else{
            request.getSession().setAttribute("login_fail_msg", loginStatus);
            response.sendRedirect("/index.jsp");
        }

    }

}
