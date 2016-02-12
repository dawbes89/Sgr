package sgr.app.core.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.account.AccountType;
import sgr.app.api.admin.Admin;
import sgr.app.api.admin.AdminService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes
 */
public class AdminServiceImpl extends DaoSupport implements AdminService
{
   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

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
      String password = admin.getAccount().getPassword();
      admin.getAccount().setPassword(PASSWORD_ENCODER.encode(password));
      admin.getAccount().setType(AccountType.TEACHER);
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
}
