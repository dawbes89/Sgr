package sgr.app.frontend.announcement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;

/**
 * @author dawbes
 */
@Controller
public class AnnouncementPanel implements Serializable
{

   private static final long serialVersionUID = 3526253311725754381L;

   @Autowired
   private AnnouncementService announcementService;

   public AnnouncementService getAnnouncementService()
   {
      return announcementService;
   }

   public void setAnnouncementService(AnnouncementService announcementService)
   {
      this.announcementService = announcementService;
   }

   private Announcement announcement = new Announcement();

   private List<Announcement> announcementsList;

   @PostConstruct
   public void init()
   {
      announcementsList = announcementService.search();
   }

   public Announcement getAnnouncement()
   {
      return announcement;
   }

   public void setAnnouncement(Announcement announcement)
   {
      this.announcement = announcement;
   }

   public List<Announcement> getAnnouncementsList()
   {
      return announcementsList;
   }

   public void setAnnouncementsList(List<Announcement> announcementsList)
   {
      this.announcementsList = announcementsList;
   }

}
