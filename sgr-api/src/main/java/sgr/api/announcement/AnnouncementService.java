package sgr.api.announcement;

import java.util.List;

public interface AnnouncementService
{
   public List<Announcement> search();

   public void add(Announcement announcement);

   public void delete(Announcement announcement);

   public void update(Announcement announcement);

}