package sgr.admin.webapp.semestr;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrService;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * @author leonzio
 */
public class SemestrPanel extends AbstractPanel<Semestr>implements EditablePanel<Semestr>
{

   private static final long serialVersionUID = -2275101170715576396L;

   @Autowired
   private SemestrService semestrService;

   @Override
   public void init()
   {
      entity = new Semestr();
   }

   @Override
   public void onLoad()
   {
      entities = semestrService.search();
   }

   @Override
   public void create()
   {
      semestrService.create(entity);
      entity = new Semestr();
      onLoad();
   }

   @Override
   public void update(Semestr object)
   {

   }

   @Override
   public void remove(Long id)
   {

   }

}
