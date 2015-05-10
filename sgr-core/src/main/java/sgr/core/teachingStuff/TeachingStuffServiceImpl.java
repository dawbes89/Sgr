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
   public List<TeachingStuff> getTeachingStuffList()
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
   public void addTeacher(TeachingStuff teachingStuff)
   {
      sessionFactory.getCurrentSession().save(teachingStuff);

   }

   @Override
   public void deleteTeacher(TeachingStuff teachingStuff)
   {
      sessionFactory.getCurrentSession().delete(teachingStuff);

   }

   @Override
   public void updateTeacher(TeachingStuff teachingStuff)
   {
      sessionFactory.getCurrentSession().update(teachingStuff);
   }

}
