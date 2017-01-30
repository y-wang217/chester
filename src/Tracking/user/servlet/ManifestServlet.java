package Tracking.user.servlet;

import Tracking.user.action.UserAction;
import Tracking.user.util.Status;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yale on 28/09/16.
 */
public class ManifestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");

        HashMap<String,Object> manifestInfoMap = UserAction.getManifestInfo(code);

        //if login succeeds
        if (manifestInfoMap.get("status").equals(Status.SUCC_MSG)) {
            String json = new Gson().toJson(manifestInfoMap);
            request.getSession().setAttribute("code", code);
            response.getWriter().write(json);
            System.out.println(json);
        }else{
            request.getSession().setAttribute("login_fail_msg", manifestInfoMap.get("status"));
            String json = new Gson().toJson(manifestInfoMap);
            response.getWriter().write(json);
        }

    }
}
