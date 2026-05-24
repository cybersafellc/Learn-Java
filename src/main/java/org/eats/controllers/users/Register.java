package org.eats.controllers.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eats.errors.ApiExceptions;
import org.eats.errors.PagesExceptions;
import org.eats.request.RegisterReq;
import org.eats.response.ResponseApi;
import org.eats.services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {

    private UsersServices usersServices;
    private ObjectMapper maper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        if(!resp.isCommitted()){
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterReq data;
        try{
            data = maper.readValue(req.getReader(), RegisterReq.class);
        } catch (IOException e) {
            throw new ApiExceptions(400, "body required");
        }
        ResponseApi responseData = usersServices.register(data.getUsername(), data.getPassword(), data.getEmail(), data.getName());
        resp.setStatus(responseData.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(maper.writeValueAsString(responseData));
    }

    @Override
    public void init() throws ServletException {
        this.maper = new ObjectMapper();
        this.usersServices = new UsersServices();
        usersServices.init();
    }
}
