package sgr.core.teachingStuff;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sgr.api.teachingStuff.TeachingStuff;
import sgr.api.teachingStuff.TeachingStuffService;
import sgr.commons.DaoSupport;

/**
 * @author dawbes
 */
public class TeachingStuffServiceImpl extends DaoSupport implements TeachingStuffService
{
   private static final long serialVersionUID = 893855768880646645L;

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
      org.springframework.security.crypto.password.PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
      bCrypt.encode(password);
      teachingStuff.getAccount().setPassword((passwordEncoder.encode(password)));

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
