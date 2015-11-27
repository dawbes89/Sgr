package sgr.commons.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author leonzio
 */
@Transactional
public abstract class DaoSupport
{

   public SessionFactory sessionFactory;

   private Session getSession()
   {
      return sessionFactory.getCurrentSession();
   }

   protected <T> Criteria createCriteria(Class<T> persistentClass)
   {
      return getSession().createCriteria(persistentClass);
   }

   protected <T> List<T> search(Criteria criteria)
   {
      Collection<?> result = criteria.list();
      List<T> resultList = new ArrayList<T>(result.size());
      for (Object object : result)
      {
         resultList.add(ObjectsHelper.uncheckedCast(object));
      }
      return resultList;
   }

   protected <T> T createEntity(T entity)
   {
      Object object = getSession().save(entity);
      return ObjectsHelper.uncheckedCast(object);
   }

   protected <T> T updateEntity(T entity)
   {
      getSession().update(entity);
      return ObjectsHelper.uncheckedCast(entity);
   }

   protected <T> void removeEntity(T entity)
   {
      getSession().delete(entity);
   }

   protected <T> T getEntity(Class<T> clazz, Long id)
   {
      Object entity = getSession().get(clazz, id);
      return ObjectsHelper.uncheckedCast(entity);
   }

   protected <T> LockMode getCurrentLockMode(T entity)
   {
      return getSession().getCurrentLockMode(entity);
   }
   
   protected Session openSession()
   {
      return sessionFactory.openSession();
   }
   
   protected Session closeSession()
   {
      return sessionFactory.openSession();
   }

   @Required
   public void setSessionFactory(SessionFactory sessionFactory)
   {
      this.sessionFactory = sessionFactory;
   }
}