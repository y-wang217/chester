package Tracking.user.servlet;

import Tracking.user.action.UserAction;
import Tracking.user.util.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Created by yale on 27/01/17.
 */
@MultipartConfig
public class UploadServlet extends HttpServlet {
    //upload image from home.jsp, into mysql:imaging.signatures;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part userPart = request.getPart("user");
        Part photoPart = request.getPart("photo");

        String user = userPart.getName();

        //check if uname not null
        if(user.equals(null)){
            request.getSession().setAttribute("msg", "username empty, please enter username.");
            response.sendRedirect("/home.jsp");

        }
        InputStream photo = null;
        if(photoPart.equals(null)){
            request.getSession().setAttribute("msg", "photo did not upload, please try again.");
            response.sendRedirect("/home.jsp");

        }else{
            // prints out some information for debugging
            System.out.println(photoPart.getName());
            System.out.println(photoPart.getSize());
            System.out.println(photoPart.getContentType());

            // obtains input stream of the upload file
            photo = photoPart.getInputStream();
        }

        String uploadStatus = UserAction.upload(user, photo);


        //if login succeeds
        if (uploadStatus.contains(Status.SUCC_MSG)) {
            request.getSession().setAttribute("upload_status_message", "Upload Success");
            response.sendRedirect("/home.jsp");
        }else{
            request.getSession().setAttribute("upload_status_message", "Upload Failed. Please try again");
            response.sendRedirect("/home.jsp");
        }

    }

}
