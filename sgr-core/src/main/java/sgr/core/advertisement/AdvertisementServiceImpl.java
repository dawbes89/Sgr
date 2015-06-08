package sgr.core.advertisement;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

import sgr.api.advertisement.Advertisement;
import sgr.api.advertisement.AdvertisementService;

@Transactional
public class AdvertisementServiceImpl implements AdvertisementService
{

   private SessionFactory sessionFactory;

   @Override
   public List<Advertisement> search()
   {
      Session currentSession = sessionFactory.getCurrentSession();
      Criteria createCriteria = currentSession.createCriteria(Advertisement.class);

      return createCriteria.list();
   }

   @Override
   public void add(Advertisement advertisement)
   {
      sessionFactory.getCurrentSession().save(advertisement);

   }

   @Override
   public void delete(Advertisement advertisement)
   {
      sessionFactory.getCurrentSession().delete(advertisement);

   }

   @Override
   public void update(Advertisement advertisement)
   {
      sessionFactory.getCurrentSession().update(advertisement);

   }

   public SessionFactory getSessionFactory()
   {
      return sessionFactory;
   }

   @Required
   public void setSessionFactory(SessionFactory sessionFactory)
   {
      this.sessionFactory = sessionFactory;
   }

}
