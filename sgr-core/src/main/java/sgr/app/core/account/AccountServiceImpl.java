package sgr.app.core.account;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * @author dawbes89
 */
class AccountServiceImpl implements AccountService
{
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	//TODO
	private AccountDao accountDao;

	@Override
	public Optional<Account> find(String login)
	{
		AccountQuery query = AccountQuery.builder().withLogin(login).build();
		return accountDao.find(query);
	}

	// TODO refactor this
	@Override
	public <T> Optional<T> findUserByAccount(Account account)
	{
		return accountDao.findUserByAccount(account);
	}

	@Override
	public Account create(AccountCreateRequest request)
	{
		Account account = request.create(PASSWORD_ENCODER::encode);
		return accountDao.create(account);
	}

	@Override
	public void updateAccountPassword(String login, String password)
	{
		final Optional<Account> foundAccount = find(login);
		final Account account = foundAccount.get();
		account.setPassword(PASSWORD_ENCODER.encode(password));
		updateEntity(account);
	}

	@Required
	public void setAccountDao(AccountDao accountDao)
	{
		this.accountDao = accountDao;
	}
}
