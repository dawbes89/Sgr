package sgr.core.announcement;

import java.util.List;

import org.hibernate.Criteria;

import sgr.api.announcement.Announcement;
import sgr.api.announcement.AnnouncementService;
import sgr.commons.DaoSupport;

/**
 * @author dawbes
 */
public class AnnouncementServiceImpl extends DaoSupport implements AnnouncementService
{

   private static final long serialVersionUID = -6005137911616592291L;

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
