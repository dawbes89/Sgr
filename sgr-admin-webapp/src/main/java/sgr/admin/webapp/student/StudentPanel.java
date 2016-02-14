package sgr.admin.webapp.student;

import java.util.List;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.account.Account;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.person.Person;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
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

   private Account account;

   private Person person;

   private List<ClassGroup> availableClasses;

   @Override
   public void init()
   {
      entity = new Student();
      account = new Account();
      person = new Person();
      entities = studentService.search(StudentQuery.EMPTY);
      availableClasses = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void onLoad()
   {
      init();
   }

   @Override
   public void create()
   {
      entity.setAccount(account);
      entity.setPerson(person);
      studentService.create(entity);
      init();
   }

   @Override
   public void update(Student object)
   {
      studentService.update(object);
      init();
   }

   @Override
   public void remove(Long id)
   {
      studentService.remove(id);
      init();
   }

   public void generatePassword(String formId, String componentId)
   {
      InputText passwordField = BeanHelper.getComponent(formId, componentId);
      String password = RandomPasswordGenerator.generate();
      passwordField.setSubmittedValue(password);
   }

   public Account getAccount()
   {
      return account;
   }

   public void setAccount(Account account)
   {
      this.account = account;
   }

   public Person getPerson()
   {
      return person;
   }

   public void setPerson(Person person)
   {
      this.person = person;
   }

   public List<ClassGroup> getAvailableClasses()
   {
      return availableClasses;
   }
}
