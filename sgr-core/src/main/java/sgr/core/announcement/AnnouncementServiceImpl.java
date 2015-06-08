package sgr.core.announcement;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

import sgr.api.announcement.Announcement;
import sgr.api.announcement.AnnouncementService;

@Transactional
public class AnnouncementServiceImpl implements AnnouncementService
{

   private SessionFactory sessionFactory;

   @Override
   public List<Announcement> search()
   {
      Session currentSession = sessionFactory.getCurrentSession();
      Criteria createCriteria = currentSession.createCriteria(Announcement.class);

      return createCriteria.list();
   }

   @Override
   public void add(Announcement announcement)
   {
      sessionFactory.getCurrentSession().save(announcement);

   }

   @Override
   public void delete(Announcement announcement)
   {
      sessionFactory.getCurrentSession().delete(announcement);

   }

   @Override
   public void update(Announcement announcement)
   {
      sessionFactory.getCurrentSession().update(announcement);

   }

   @Required
   public void setSessionFactory(SessionFactory sessionFactory)
   {
      this.sessionFactory = sessionFactory;
   }

   public SessionFactory getSessionFactory()
   {
      return sessionFactory;
   }

}
