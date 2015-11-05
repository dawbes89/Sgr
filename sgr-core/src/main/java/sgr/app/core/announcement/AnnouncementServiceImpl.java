package sgr.app.core.announcement;

import java.util.List;

import org.hibernate.Criteria;

import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;
import sgr.commons.core.DaoSupport;

/**
 * @author dawbes
 */
public class AnnouncementServiceImpl extends DaoSupport implements AnnouncementService
{

   @Override
   public List<Announcement> search()
   {
      Criteria crit = createCriteria(Announcement.class);
      return search(crit);
   }

   @Override
   public void create(Announcement announcement)
   {
      createEntity(announcement);
   }

   @Override
   public void delete(Announcement announcement)
   {
      removeEntity(announcement);
   }

   @Override
   public void update(Announcement announcement)
   {
      updateEntity(announcement);
   }

}
