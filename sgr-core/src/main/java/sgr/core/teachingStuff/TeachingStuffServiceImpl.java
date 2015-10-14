package sgr.core.teachingStuff;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sgr.api.teachingStuff.TeachingStuff;
import sgr.api.teachingStuff.TeachingStuffService;
import sgr.commons.DaoSupport;

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
      sessionFactory.getCurrentSession().save(teachingStuff);
   }

   @Override
   public void remove(Long id)
   {
      TeachingStuff teacher = get(id);
      sessionFactory.getCurrentSession().delete(teacher);

   }

   @Override
   public void update(TeachingStuff teachingStuff)
   {
      sessionFactory.getCurrentSession().saveOrUpdate(teachingStuff);
   }

}
