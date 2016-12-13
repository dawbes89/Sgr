package sgr.app.core.account;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * @author dawbes89
 */
//TODO create method to matching passwords for account
class AccountServiceImpl implements AccountService
{
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private AccountDao accountDao;

	@Override
	public Optional<Account> findByLogin(String login)
	{
		AccountQuery query = AccountQuery.builder().withLogin(login).build();
		return accountDao.find(query);
	}

	// TODO refactor this in dao
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
	public void updateAccountPassword(AccountPasswordUpdateRequest request)
	{
		Account account = request.updatePassword(accountDao::getForUpdate, PASSWORD_ENCODER::encode);
		accountDao.update(account);
	}

	@Required
	public void setAccountDao(AccountDao accountDao)
	{
		this.accountDao = accountDao;
	}
}
