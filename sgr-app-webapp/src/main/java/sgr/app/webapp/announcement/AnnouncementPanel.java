package sgr.app.webapp.announcement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * Panel for displaying annoucements.
 *
 * @author dawbes89
 */
@Controller
public class AnnouncementPanel extends AbstractPanel<Announcement>
{

   private static final long serialVersionUID = 3526253311725754381L;

   @Autowired
   private AnnouncementService announcementService;

   @Override
   public void init()
   {
      entity = new Announcement();
   }

   @Override
   public void onLoad()
   {
      entities = announcementService.search();
   }

}
