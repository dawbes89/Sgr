package sgr.app.core.teachingStuff;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.commons.core.DaoSupport;

/**
 * @author dawbes
 */
public class TeachingStuffServiceImpl extends DaoSupport implements TeachingStuffService
{

   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

   @Override
   public List<TeachingStuff> search()
   {
      Criteria criteria = createCriteria(TeachingStuff.class);
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
      String password = teachingStuff.getAccount().getPassword();
      teachingStuff.getAccount().setPassword(PASSWORD_ENCODER.encode(password));
      createEntity(teachingStuff);
   }

   @Override
   public void remove(Long id)
   {
      TeachingStuff teacher = get(id);
      removeEntity(teacher);
   }

   @Override
   public void update(TeachingStuff teachingStuff)
   {
      updateEntity(teachingStuff);
   }

}
