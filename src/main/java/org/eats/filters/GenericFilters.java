package org.eats.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eats.errors.ApiExceptions;
import org.eats.errors.PagesExceptions;
import org.eats.response.ResponseApi;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class GenericFilters implements Filter {

    private ObjectMapper maper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req =
                (HttpServletRequest) request;
        HttpServletResponse resp =
                (HttpServletResponse) response;
        try{
            chain.doFilter(request, response);
        } catch (PagesExceptions e) {
            e.printStackTrace();
            if(!resp.isCommitted()){
                resp.setStatus(e.getStatus());
                req.setAttribute("status", e.getStatus());
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("WEB-INF/views/error.jsp").forward(req, resp);
            }
        } catch (ApiExceptions e) {
            e.printStackTrace();
            resp.setContentType("application/json");
            resp.setStatus(e.getStatus());
            ResponseApi resData = new ResponseApi(e.getStatus(), e.getMessage(), null, null, true);
            response.getWriter().println(maper.writeValueAsString(resData));
        } catch (Exception e){
            e.printStackTrace();
            if(!resp.isCommitted()){
                resp.setStatus(500);
                req.setAttribute("status", 500);
                req.setAttribute("message", "Internal server error");
                req.getRequestDispatcher("WEB-INF/views/error.jsp").forward(req, resp);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            if(!resp.isCommitted()){
                resp.setStatus(500);
                req.setAttribute("status", 500);
                req.setAttribute("message", "Internal server error");
                req.getRequestDispatcher("WEB-INF/views/error.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        maper = new ObjectMapper();
    }
}

