package sgr.app.frontend.panels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sgr.app.api.account.AccountType;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.exceptions.AuthenticationException;
import sgr.app.api.exceptions.CustomException;
import sgr.app.api.translation.TranslationService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract base for login panels.
 *
 * @author leonzio
 */
public abstract class AbstractLoginPanel implements Serializable
{
	private static final long serialVersionUID = 8771925513486552329L;

	private static final String MAIN_PAGE = String.format("/app/%s.xhtml", AuthenticationService.MAIN_PAGE);

	@Autowired
	protected AuthenticationService authenticationService;

	@Autowired
	private TranslationService translationService;

	private LoginBean loginBean;

	public AbstractLoginPanel()
	{
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@PostConstruct
	public void init()
	{
		loginBean = new LoginBean();
	}

	// TODO javadoc
	protected abstract List<AccountType> supportedAccountTypes();

	public void checkLogin() throws IOException
	{
		try
		{
			authenticationService.authenticateUser(loginBean.getUserName(), loginBean.getPassword(),
			                                       supportedAccountTypes());
			final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
		}
		catch (AuthenticationException e)
		{
			handleException(e);
		}
	}

	public void logout() throws IOException
	{
		authenticationService.logoutUser();
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath());
	}

	private void handleException(CustomException throwable)
	{
		final String validationMessage = translationService.translate(throwable.getMessage());
		final FacesMessage message = new FacesMessage(validationMessage);
		message.setSeverity(throwable.getSeverity());
		FacesContext.getCurrentInstance().addMessage("loginForm", message);
	}

	public LoginBean getLoginBean()
	{
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean)
	{
		this.loginBean = loginBean;
	}

}
