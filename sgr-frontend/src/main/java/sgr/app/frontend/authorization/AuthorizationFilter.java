package sgr.app.frontend.authorization;

import sgr.app.api.authentication.AuthenticationService;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter used to filter pages with proper restrictions.
 *
 * @author leonzio
 */
public class AuthorizationFilter implements Filter
{

	private final static String URL_FORMAT = "%s/app/%s.xhtml";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException
	{
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final HttpSession session = request.getSession(false);

		if (request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER))
		{
			chain.doFilter(request, response);
			return;
		}

		Object user = session != null ? session.getAttribute(AuthenticationService.USER_ATTRIBUTE) : null;

		final String loginURL = String.format(URL_FORMAT, request.getContextPath(), AuthenticationService.LOGIN_PAGE);
		final String mainURL = String.format(URL_FORMAT, request.getContextPath(), AuthenticationService.MAIN_PAGE);

		final boolean validRequestPath = request.getRequestURI().startsWith(
				request.getContextPath()) && request.getRequestURI().endsWith("/");

		if (user != null && (request.getRequestURI().contains(AuthenticationService.LOGIN_PAGE) || validRequestPath))
		{
			response.sendRedirect(mainURL);
			return;
		}

		if (user == null && !request.getRequestURI().contains(AuthenticationService.LOGIN_PAGE))
		{
			response.sendRedirect(loginURL);
			return;
		} else
		{
			chain.doFilter(request, response);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

	@Override
	public void destroy()
	{
	}

}
