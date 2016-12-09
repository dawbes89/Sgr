package sgr.app.core.account;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import sgr.app.api.admin.Admin;
import sgr.app.api.student.Student;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.core.SgrDaoSupport;

import java.util.Optional;

/**
 * Created by leonzio on 2016-12-09.
 */
class AccountDao extends SgrDaoSupport
{
	Account create(Account account)
	{
		return createEntity(account);
	}

	Account update(Account account)
	{
		return updateEntity(account);
	}

	Account getForUpdate(Long id)
	{
		return getForUpdate(id);
	}

	Optional<Account> find(AccountQuery query)
	{
		Criteria criteria = createCriteria(Account.class);
		query.apply(AccountQueryApplier.of(criteria));
		return find(criteria);
	}

	<T> Optional<T> findUserByAccount(Account account)
	{
		Criteria criteria;
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

		final SimpleExpression accountRestriction = Restrictions.eq("account", account);
		criteria.add(accountRestriction);
		return Optional.of((T) criteria.uniqueResult());
	}

	private final static class AccountQueryApplier implements AccountQuery.Applier
	{
		private final Criteria criteria;

		private AccountQueryApplier(Criteria criteria)
		{
			this.criteria = criteria;
		}

		public static AccountQueryApplier of(Criteria criteria)
		{
			return new AccountQueryApplier(criteria);
		}

		@Override
		public void withLogin(String login)
		{
			criteria.add(Restrictions.eq(Account.PROPERTY_USER_NAME, login));
		}
	}
}
