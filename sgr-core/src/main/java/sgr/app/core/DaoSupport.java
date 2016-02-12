package sgr.app.core;

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

   private SessionFactory sessionFactory;

   protected final Session getSession()
   {
      return sessionFactory.getCurrentSession();
   }

   protected <T> Criteria createCriteria(Class<T> persistentClass)
   {
      return getSession().createCriteria(persistentClass);
   }

   @SuppressWarnings("unchecked")
   protected <T> List<T> search(Criteria criteria)
   {
      Collection<?> result = criteria.list();
      List<T> resultList = new ArrayList<T>(result.size());
      for (Object object : result)
      {
         resultList.add((T) object);
      }
      return resultList;
   }

   @SuppressWarnings("unchecked")
   protected <T> T createEntity(T entity)
   {
      T object = (T) getSession().save(entity);
      return object;
   }

   protected <T> T updateEntity(T entity)
   {
      getSession().update(entity);
      return entity;
   }

   protected <T> void removeEntity(T entity)
   {
      getSession().delete(entity);
   }

   @SuppressWarnings("unchecked")
   protected <T> T getEntity(Class<T> clazz, Long id)
   {
      Object entity = getSession().get(clazz, id);
      return (T) entity;
   }

   protected <T> LockMode getCurrentLockMode(T entity)
   {
      return getSession().getCurrentLockMode(entity);
   }

   protected Session openSession()
   {
      return sessionFactory.openSession();
   }

   protected void closeSession()
   {
      sessionFactory.close();
   }

   public boolean isSession()
   {
      return sessionFactory.isClosed();
   }

   @Required
   public final void setSessionFactory(SessionFactory sessionFactory)
   {
      this.sessionFactory = sessionFactory;
   }
}