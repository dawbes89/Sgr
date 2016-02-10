package sgr.app.frontend.authorization;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sgr.app.api.authentication.AuthenticationService;

/**
 * Filter used to filter pages with proper restrictions.
 *
 * @author leonzio
 */
public class AuthorizationFilter implements Filter
{

   private final static String URL_FORMAT = "%s/app/%s.xhtml";

   @Override
   public void init(FilterConfig filterConfig) throws ServletException
   {}

   @Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
         throws IOException, ServletException
   {
      final HttpServletRequest request = (HttpServletRequest) req;
      final HttpServletResponse response = (HttpServletResponse) res;
      final HttpSession session = request.getSession(false);

      if (request.getRequestURI()
            .startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER))
      {
         chain.doFilter(request, response);
         return;
      }

      Object user = session != null ? session.getAttribute(AuthenticationService.USER_ATTRIBUTE)
            : null;

      final String loginURL = String.format(URL_FORMAT, request.getContextPath(),
            AuthenticationService.LOGIN_PAGE);
      final String mainURL = String.format(URL_FORMAT, request.getContextPath(),
            AuthenticationService.MAIN_PAGE);

      if (user != null && request.getRequestURI().contains(AuthenticationService.LOGIN_PAGE))
      {
         response.sendRedirect(mainURL);
         chain.doFilter(request, response);
         return;
      }

      if (user == null && !request.getRequestURI().contains(AuthenticationService.LOGIN_PAGE))
      {
         response.sendRedirect(loginURL);
         return;
      }
      else
      {
         chain.doFilter(request, response);
         return;
      }
   }

   @Override
   public void destroy()
   {}

}
