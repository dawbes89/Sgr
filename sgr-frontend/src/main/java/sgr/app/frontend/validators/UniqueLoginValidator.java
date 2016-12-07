package sgr.app.frontend.validators;

import org.springframework.beans.factory.annotation.Autowired;
import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import java.util.Optional;

/**
 * Validator for login. Not allows to use twice and more times the same login.
 *
 * @author leonzio
 */
public class UniqueLoginValidator extends AbstractValidator<String>
{

	@Autowired
	private AccountService accountService;

	public UniqueLoginValidator()
	{
		super(FacesMessage.SEVERITY_ERROR, "validation_userNameIsUsed");
	}

	@Override
	protected boolean isValidValue(String value, final UIComponent component)
	{
		final Optional<Account> findAccountByLogin = accountService.findAccountByLogin(value);
		return !findAccountByLogin.isPresent();
	}

}
