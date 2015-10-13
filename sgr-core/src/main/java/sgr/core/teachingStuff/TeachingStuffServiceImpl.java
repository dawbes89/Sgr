package sgr.core.teachingStuff;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sgr.api.teachingStuff.TeachingStuff;
import sgr.api.teachingStuff.TeachingStuffService;

@Transactional
public class TeachingStuffServiceImpl implements TeachingStuffService
{
   private SessionFactory sessionFactory;

   @Override
   public List<TeachingStuff> search()
   {
      Session currentSession = sessionFactory.getCurrentSession();
      Criteria createCriteria = currentSession.createCriteria(TeachingStuff.class);

      return createCriteria.list();
   }

   public void setSessionFactory(SessionFactory sessionFactory)
   {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public TeachingStuff get(Long id)
   {
      return (TeachingStuff) sessionFactory.getCurrentSession().get(TeachingStuff.class, id);
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
