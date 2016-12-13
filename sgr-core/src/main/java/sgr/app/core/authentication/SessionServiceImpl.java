package sgr.app.core.authentication;

import sgr.app.api.authentication.SessionService;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author leonzio
 */
class SessionServiceImpl implements SessionService
{

	private static final ExternalContext EXTERNAL_CONTEXT = FacesContext.getCurrentInstance().getExternalContext();

	@Override
	public HttpSession getSession()
	{
		return (HttpSession) EXTERNAL_CONTEXT.getSession(false);
	}

	@Override
	public HttpServletRequest getRequest()
	{
		return (HttpServletRequest) EXTERNAL_CONTEXT.getRequest();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAttributeValue(String attributeKey) throws NullPointerException
	{
		if (getSession() == null)
		{
			throw new NullPointerException("Session is null");
		}
		return (T) getSession().getAttribute(attributeKey);
	}

	@Override
	public <T> void setAttributeValue(String name, T value)
	{
		getSession().setAttribute(name, value);
	}

}
