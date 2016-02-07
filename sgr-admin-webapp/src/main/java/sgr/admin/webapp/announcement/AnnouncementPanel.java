package sgr.admin.webapp.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 *
 * @author dawbes
 *
 */
@Controller
public class AnnouncementPanel extends AbstractPanel<Announcement>
      implements EditablePanel<Announcement>
{

   private static final long serialVersionUID = 6851863797450712604L;

   @Autowired
   private AnnouncementService announcementService;

   @Override
   public void init()
   {
      entity = new Announcement();
      entities = announcementService.search();
   }

   @Override
   public void create()
   {
      entity.setDate(new Date());
      announcementService.create(entity);
      init();
   }

   @Override
   public void update(Announcement object)
   {
      announcementService.update(entity);
      init();
   }

   @Override
   public void remove(Long id)
   {
      announcementService.delete(id);
      init();
   }

   @Override
   public void onLoad()
   {
      init();
   }

}
