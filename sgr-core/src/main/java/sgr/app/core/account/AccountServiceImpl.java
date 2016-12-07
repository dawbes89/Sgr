package sgr.app.core.account;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.admin.Admin;
import sgr.app.api.student.Student;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.core.DaoSupport;

import java.util.Date;
import java.util.Optional;

/**
 * @author dawbes89
 */
class AccountServiceImpl extends DaoSupport implements AccountService
{

	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Override
	public Optional<Account> findAccountByLogin(String login)
	{
		final Criteria criteria = createCriteria(Account.class);
		criteria.add(Restrictions.eq(Account.PROPERTY_USER_NAME, login));
		final Account result = (Account) criteria.uniqueResult();
		return Optional.ofNullable(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Optional<T> findUserByAccount(Account account)
	{
		Criteria criteria = null;
		switch (account.getType())
		{
			case TEACHER:
				criteria = createCriteria(TeachingStaff.class);
				break;
			case STUDENT:
				criteria = createCriteria(Student.class);
				break;
			case ADMIN:
				criteria = createCriteria(Admin.class);
				break;
			default:
				return Optional.empty();
		}

		Optional<T> user = Optional.empty();
		final SimpleExpression accountRestriction = Restrictions.eq("account", account);
		criteria.add(accountRestriction);
		user = Optional.of((T) criteria.uniqueResult());
		return user;
	}

	@Override
	public Account createAccount(Account account)
	{
		final String accountPassword = account.getPassword();

		account.setCreated(new Date());
		account.setPassword(PASSWORD_ENCODER.encode(accountPassword));
		return createEntity(account);
	}

	@Override
	public void updateAccountPassword(String login, String password)
	{
		final Optional<Account> foundAccount = findAccountByLogin(login);
		final Account account = foundAccount.get();
		account.setPassword(PASSWORD_ENCODER.encode(password));
		updateEntity(account);
	}

}
