package sgr.admin.webapp.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * @author leonzio
 */
@Controller
public class StudentPanel extends AbstractPanel<Student>implements EditablePanel<Student>
{

   private static final long serialVersionUID = 2553933126154263063L;

   @Autowired
   private StudentService studentService;

   @Autowired
   private ClassGroupService classGroupService;

   private List<ClassGroup> availableClasses;

   @Override
   public void init()
   {
      entity = new Student();
   }

   @Override
   public void onLoad()
   {
      entities = studentService.search(StudentQuery.EMPTY);
      availableClasses = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void create()
   {
      studentService.create(entity);
      entity = new Student();
      onLoad();
   }

   @Override
   public void update(Student object)
   {
      studentService.update(object);
      onLoad();
   }

   @Override
   public void remove(Long id)
   {
      studentService.remove(id);
      onLoad();
   }

   public void generatePassword()
   {
      entity.getAccount().setPassword(RandomPasswordGenerator.generate());
   }

   public List<ClassGroup> getAvailableClasses()
   {
      return availableClasses;
   }
}
