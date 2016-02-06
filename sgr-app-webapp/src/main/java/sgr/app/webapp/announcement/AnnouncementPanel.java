package sgr.app.webapp.announcement;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes
 */
@Controller
public class AnnouncementPanel extends AbstractPanel<Announcement> implements Serializable
{

   private static final long serialVersionUID = 3526253311725754381L;

   @Autowired
   private AnnouncementService announcementService;

   @Override
   public void init()
   {
      entity = new Announcement();
      entities = announcementService.search();
   }

   @Override
   public void onLoad()
   {
      init();
   }

}
