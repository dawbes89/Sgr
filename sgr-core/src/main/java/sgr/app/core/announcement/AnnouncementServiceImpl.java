package sgr.app.core.announcement;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes
 */
class AnnouncementServiceImpl extends DaoSupport implements AnnouncementService
{

   @Override
   public List<Announcement> search()
   {
      Criteria crit = createCriteria(Announcement.class);
      crit.addOrder(Order.desc(Announcement.PROPERTY_DATE));
      return search(crit);
   }

   @Override
   public void create(Announcement announcement)
   {
      announcement.setDate(new Date());
      createEntity(announcement);
   }

   @Override
   public void delete(Long id)
   {
      removeEntity(getEntity(Announcement.class, id));
   }

   @Override
   public void update(Announcement announcement)
   {
      updateEntity(announcement);
   }

}
