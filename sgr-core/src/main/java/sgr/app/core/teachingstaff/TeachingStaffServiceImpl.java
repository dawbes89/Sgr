package sgr.app.core.teachingstaff;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.api.teachingstaff.TeachingStaffService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes89
 */
class TeachingStaffServiceImpl extends DaoSupport implements TeachingStaffService
{

   private AccountService accountService;

   @Override
   public List<TeachingStaff> search()
   {
      Criteria criteria = createCriteria(TeachingStaff.class);
      criteria.addOrder(Order.desc(TeachingStaff.PROPERTY_ID));
      return search(criteria);
   }

   @Override
   public TeachingStaff get(Long id)
   {
      return getEntity(TeachingStaff.class, id);
   }

   @Override
   public void create(TeachingStaff teachingStuff)
   {
      final Account account = teachingStuff.getAccount();
      account.setType(AccountType.TEACHER);
      teachingStuff.setAccount(accountService.createAccount(account));
      createEntity(teachingStuff);
   }

   @Override
   public void remove(Long id)
   {
      removeEntity(TeachingStaff.class, id);
   }

   @Override
   public void update(TeachingStaff teachingStuff)
   {
      updateEntity(teachingStuff);
   }

   @Required
   public void setAccountService(AccountService accountService)
   {
      this.accountService = accountService;
   }

}
