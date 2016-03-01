package sgr.app.core.teachingStuff;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes89
 */
class TeachingStuffServiceImpl extends DaoSupport implements TeachingStuffService
{

   private AccountService accountService;

   @Override
   public List<TeachingStuff> search()
   {
      Criteria criteria = createCriteria(TeachingStuff.class);
      criteria.addOrder(Order.desc(TeachingStuff.PROPERTY_ID));
      return search(criteria);
   }

   @Override
   public TeachingStuff get(Long id)
   {
      return getEntity(TeachingStuff.class, id);
   }

   @Override
   public void create(TeachingStuff teachingStuff)
   {
      final Account account = teachingStuff.getAccount();
      account.setType(AccountType.TEACHER);
      teachingStuff.setAccount(accountService.createAccount(account));
      createEntity(teachingStuff);
   }

   @Override
   public void remove(Long id)
   {
      removeEntity(TeachingStuff.class, id);
   }

   @Override
   public void update(TeachingStuff teachingStuff)
   {
      updateEntity(teachingStuff);
   }

   @Required
   public void setAccountService(AccountService accountService)
   {
      this.accountService = accountService;
   }

}
