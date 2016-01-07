package sgr.app.core.account;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.core.DaoSupport;
import sgr.app.core.ObjectsHelper;

/**
 * @author dawbes
 */
class AccountServiceImpl extends DaoSupport implements AccountService
{

   @Override
   public Optional<Account> findAccountByLogin(String login)
   {
      Criteria criteria = createCriteria(Account.class);
      criteria.add(Restrictions.eq(Account.PROPERTY_USER_NAME, login));
      Account result = (Account) criteria.uniqueResult();
      return Optional.ofNullable(result);
   }

   @Override
   public <T> Optional<T> findUserByAccount(Account account)
   {
      if (account.getType().equals(AccountType.TEACHER))
      {
         Criteria teachingStuffCriteria = createCriteria(TeachingStuff.class);
         teachingStuffCriteria.add(Restrictions.eq("account", account));
         T object = ObjectsHelper.uncheckedCast(teachingStuffCriteria.uniqueResult());
         return Optional.ofNullable(object);
      }
      // TODO Student criteria
      return null;
   }

}
