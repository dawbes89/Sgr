package sgr.app.core.account;

import sgr.app.api.account.AccountType;

import java.util.Date;
import java.util.Optional;

/**
 * Created by leonzio on 2016-12-09.
 */
public class AccountCreateRequest
{
	private String login;
	private String password;
	private AccountType type;
	private Optional<Date> validTo = Optional.empty();

	public Account create(PasswordEncoder passwordEncoder)
	{
		validate(); //TODO
		String encodedPassword = passwordEncoder.encode(password);

		Account account = new Account();
		account.setUserName(login);
		account.setPassword(encodedPassword);
		account.setType(type);
		account.setCreated(new Date()); //TODO currentDateTimeService
		validTo.ifPresent(account::setValidTo);
		return account;
	}

	private void validate()
	{

	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setType(AccountType type)
	{
		this.type = type;
	}

	public void setValidTo(Date validTo)
	{
		this.validTo = Optional.of(validTo);
	}
}
