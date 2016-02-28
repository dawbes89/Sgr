package sgr.admin.webapp.teachingStuff;

import java.util.List;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * Panel used for handling teachers.
 *
 * @author dawbes89
 */
@Controller
public class TeachingStuffPanel extends AbstractPanel<TeachingStuff>
      implements EditablePanel<TeachingStuff>
{

   private static final long serialVersionUID = 2553933126154263063L;

   @Autowired
   private TeachingStuffService teachingStuffService;

   @Autowired
   private ClassGroupService classGroupService;

   private List<ClassGroup> availableClasses;

   @Override
   public void init()
   {
      entity = new TeachingStuff();
      entities = teachingStuffService.search();
      availableClasses = classGroupService.search(ClassGroupQuery.all().withAvailableForTeachers(true).build());
   }

   @Override
   public void onLoad()
   {
      init();
   }

   @Override
   public void create()
   {
      teachingStuffService.create(entity);
      entity = new TeachingStuff();
      onLoad();
   }

   @Override
   public void update(TeachingStuff object)
   {
      teachingStuffService.update(object);
      onLoad();
   }

   @Override
   public void remove(Long id)
   {
      teachingStuffService.remove(id);
      onLoad();
   }

   @Override
   public void setEntity(TeachingStuff entity)
   {
      super.setEntity(entity);

      ClassGroupQuery query = ClassGroupQuery.all().withAvailableForTeachers(true).build();
      final ClassGroup preceptorClass = entity.getPreceptorClass();
      if (preceptorClass != null)
      {
         query.setClassId(preceptorClass.getId());
      }
      availableClasses = classGroupService.search(query);
   }

   public void generatePassword()
   {
      final InputText passwordField = BeanHelper.getComponent("add", "password");
      final String password = RandomPasswordGenerator.generate();
      passwordField.setSubmittedValue(password);
   }

   public List<ClassGroup> getAvailableClasses()
   {
      return availableClasses;
   }

}
