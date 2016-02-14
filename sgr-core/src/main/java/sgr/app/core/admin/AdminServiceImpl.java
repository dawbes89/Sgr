package sgr.app.core.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.admin.Admin;
import sgr.app.api.admin.AdminService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes
 */
class AdminServiceImpl extends DaoSupport implements AdminService
{

   private AccountService accountService;

   @Override
   public List<Admin> search()
   {
      Criteria criteria = createCriteria(Admin.class);
      return search(criteria);
   }

   @Override
   public Admin get(Long id)
   {
      return getEntity(Admin.class, id);
   }

   @Override
   public void create(Admin admin)
   {
      final Account account = admin.getAccount();
      account.setType(AccountType.ADMIN);
      admin.setAccount(accountService.createAccount(account));
      createEntity(admin);
   }

   @Override
   public void remove(Long id)
   {
      Admin admin = get(id);
      removeEntity(admin);
   }

   @Override
   public void update(Admin admin)
   {
      updateEntity(admin);
   }

   @Required
   public void setAccountService(AccountService accountService)
   {
      this.accountService = accountService;
   }

}
