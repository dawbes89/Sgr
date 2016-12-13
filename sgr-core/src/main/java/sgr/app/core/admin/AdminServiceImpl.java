package sgr.app.core.admin;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;
import sgr.app.core.account.Account;
import sgr.app.core.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.admin.Admin;
import sgr.app.api.admin.AdminQuery;
import sgr.app.api.admin.AdminService;
import sgr.app.core.util.SgrDaoSupport;

import java.util.List;

/**
 * @author dawbes89
 */
class AdminServiceImpl extends SgrDaoSupport implements AdminService
{

	private AccountService accountService;

	@Override
	public List<Admin> search(AdminQuery query)
	{
		final Criteria criteria = createCriteria(query);
		criteria.addOrder(Order.desc(Admin.PROPERTY_ID));
		return search(criteria);
	}

	@Override
	public Admin get(Long id)
	{
		return get(Admin.class, id);
	}

	@Override
	public void create(Admin admin)
	{
		final Account account = admin.getAccount();
		account.setType(AccountType.ADMIN);
		admin.setAccount(accountService.create(account));
		create(admin);
	}

	@Override
	public void remove(Long id)
	{
		remove(Admin.class, id);
	}

	@Override
	public void update(Admin admin)
	{
		update(admin);
	}

	private Criteria createCriteria(AdminQuery query)
	{
		final Criteria criteria = createCriteria(Admin.class);
		criteria.add(Restrictions.ne(Admin.PROPERTY_ID, 1L));
		criteria.add(Restrictions.ne(Admin.PROPERTY_ID, query.getExcludedId()));
		return criteria;
	}

	@Required
	public void setAccountService(AccountService accountService)
	{
		this.accountService = accountService;
	}

}
