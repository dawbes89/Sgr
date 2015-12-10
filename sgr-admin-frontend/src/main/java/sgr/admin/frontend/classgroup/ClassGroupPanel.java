package sgr.admin.frontend.classgroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.commons.frontend.AbstractPanel;
import sgr.commons.frontend.EditablePanel;

/**
 * @author leonzio
 */
@Controller
public class ClassGroupPanel extends AbstractPanel<ClassGroup> implements EditablePanel<ClassGroup>
{

   private static final long serialVersionUID = 1665393811406612606L;

   @Autowired
   private ClassGroupService classGroupService;

   @Override
   public void init()
   {
      entity = new ClassGroup();
      entities = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void onLoad()
   {
      init();
   }

   @Override
   public void create()
   {
      classGroupService.create(entity);
      init();
   }

   @Override
   public void update(ClassGroup object)
   {
      // nothing
   }

   @Override
   public void remove(Long id)
   {
      classGroupService.remove(id);
      init();
   }

   public List<String> getYears()
   {
      return classGroupService.getYears();
   }
}
