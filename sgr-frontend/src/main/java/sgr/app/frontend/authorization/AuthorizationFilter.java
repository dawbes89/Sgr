package sgr.app.frontend.authorization;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter
{

   @Override
   public void init(FilterConfig filterConfig) throws ServletException
   {
      String b = "d";
      String d = b;
   }

   @Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
         throws IOException, ServletException
   {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) res;
      HttpSession session = request.getSession(false);
      Object user = (session != null) ? session.getAttribute("user") : null;
      String loginURL = request.getContextPath() + "/app/loginPanel.xhtml";

      if (user == null && !request.getRequestURI().equals(loginURL))
      {
         response.sendRedirect(loginURL);
      }
      else
      {
         chain.doFilter(request, response);
      }
   }

   @Override
   public void destroy()
   {

   }

}
