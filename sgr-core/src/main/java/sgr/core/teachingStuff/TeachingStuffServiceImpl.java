package sgr.core.teachingStuff;

import java.util.List;

import org.hibernate.Criteria;

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
