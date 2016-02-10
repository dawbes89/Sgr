package sgr.app.core.account;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.student.Student;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes
 */
class AccountServiceImpl extends DaoSupport implements AccountService
{

   private static final String ACCOUNT = "account";

   @Override
   public Optional<Account> findAccountByLogin(String login)
   {
      Criteria criteria = createCriteria(Account.class);
      criteria.add(Restrictions.eq(Account.PROPERTY_USER_NAME, login));
      Account result = (Account) criteria.uniqueResult();
      return Optional.ofNullable(result);
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T> Optional<T> findUserByAccount(Account account)
   {
      Optional<T> user = Optional.empty();

      final SimpleExpression accountRestriction = Restrictions.eq(ACCOUNT, account);

      switch (account.getType())
      {
         case TEACHER:
            Criteria teachingStuffCriteria = createCriteria(TeachingStuff.class);
            teachingStuffCriteria.add(accountRestriction);
            user = Optional.of((T) teachingStuffCriteria.uniqueResult());
            break;
         case STUDENT:
            Criteria studentCriteria = createCriteria(Student.class);
            studentCriteria.add(accountRestriction);
            user = Optional.of((T) studentCriteria.uniqueResult());
            break;
         default:
            break;
      }

      return user;
   }

}
