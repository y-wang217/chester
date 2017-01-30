package Tracking.user.servlet;

/**
 * Created by yale on 27/09/16.
 */
import Tracking.user.action.UserAction;
import Tracking.user.util.Status;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserInfoServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("uname");

        HashMap<String,Object> userInfoMap = UserAction.getUserInfo(uname);

        //if login succeeds
        if (userInfoMap.get("status").equals(Status.SUCC_MSG)) {
            String json = new Gson().toJson(userInfoMap);
            request.getSession().setAttribute("user", uname);
            response.getWriter().write(json);
            System.out.println(json);
        }else{
            request.getSession().setAttribute("login_fail_msg", userInfoMap.get("status"));
            String json = new Gson().toJson(userInfoMap);
            response.getWriter().write(json);
        }

    }

    public static void main(String [] args){
        HashMap<String,String> someMap = new HashMap<>();
        someMap.put("col1", "stuff");
        someMap.put("col2", "stuff");
        someMap.put("col3", "stuff");
        String test = new Gson().toJson(someMap);
        System.out.println("GSON class test" + test);
    }
}
