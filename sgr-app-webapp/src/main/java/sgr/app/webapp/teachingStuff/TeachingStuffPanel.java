package sgr.app.webapp.teachingStuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.teachingStuff.TeachingStaff;
import sgr.app.api.teachingStuff.TeachingStaffService;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * Panel for displaying teaching stuff personel.
 *
 * @author dawbes89
 */
@Controller
public class TeachingStuffPanel extends AbstractPanel<TeachingStaff>
{

   private static final long serialVersionUID = 5475535216504328321L;

   @Autowired
   private TeachingStaffService teachingStuffService;

   @Override
   public void init()
   {
      entity = new TeachingStaff();
   }

   @Override
   public void onLoad()
   {
      entities = teachingStuffService.search();
   }

}
