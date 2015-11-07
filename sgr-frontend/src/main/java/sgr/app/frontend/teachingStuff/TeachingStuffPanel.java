package sgr.app.frontend.teachingStuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.commons.frontend.AbstractPanel;

/**
 * @author dawbes
 */
@Controller
public class TeachingStuffPanel extends AbstractPanel<TeachingStuff>
{

   private static final long serialVersionUID = 5475535216504328321L;

   @Autowired
   private TeachingStuffService teachingStuffService;

   @Override
   public void init()
   {
      entity = new TeachingStuff();
   }

   @Override
   public void onLoad()
   {
      entities = teachingStuffService.search();
   }

}
