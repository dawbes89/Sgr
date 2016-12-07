package sgr.app.frontend.panels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.account.AccountService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.translation.TranslationService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Panel used for changing password.
 *
 * @author leonzio
 */
public class ChangePasswordPanel implements Serializable
{

	private static final long serialVersionUID = 7913922316934584804L;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TranslationService translationService;

	private String newPassword;

	public ChangePasswordPanel()
	{
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public void changePassword()
	{
		final AccountEntity currentUser = authenticationService.getCurrentUser();
		final Account account = currentUser.getAccount();

		accountService.updateAccountPassword(account.getUserName(), newPassword);

		final String messageContent = translationService.translate("validation_passwordChanged");
		final FacesMessage message = new FacesMessage(messageContent);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage("passwordUpdate", message);
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

}
