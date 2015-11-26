package sgr.app.core.account;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountQuery;
import sgr.app.api.account.AccountService;
import sgr.commons.core.DaoSupport;

/**
 * @author dawbes
 */
public class AccountServiceImpl extends DaoSupport implements AccountService
{
   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

   @Override
   public boolean checkLogin(String userName, String password)
   {
      openSession();
      boolean userFound = false;
      AccountQuery query = AccountQuery.withLogin(userName);
      List<Account> accounts = search(query);
      for (Account account : accounts)
      {
         if (PASSWORD_ENCODER.matches(password, account.getPassword()))
         {
            if ((accounts != null) && (accounts.size() > 0))
            {
               userFound = true;
            }
         }
      }
      closeSession();
      return userFound;
   }

   @Override
   public List<Account> search(AccountQuery query)
   {
      Criteria crit = createCriteria(query);
      return crit.list();
   }

   private Criteria createCriteria(AccountQuery query)
   {

      Criteria crit = createCriteria(Account.class);
      String login = query.getLogin();
      String password = query.getPassword();
      if (login != null)
      {
         crit.add(Restrictions.eq(Account.PROPERTY_USER_NAME, query.getLogin()));
      }
      if (password != null)
      {
         crit.add(Restrictions.eq(Account.PROPERTY_PASSWORD, query.getPassword()));
      }
      return crit;

   }

}
