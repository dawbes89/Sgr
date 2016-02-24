package sgr.admin.webapp.teachingStuff;

import java.util.List;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.account.Account;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.person.Person;
import sgr.app.api.teachingStuff.SchoolSubject;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
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

   private Account account;

   private Person person;

   private List<ClassGroup> availableClasses;

   @Override
   public void init()
   {
      entity = new TeachingStuff();
      account = new Account();
      person = new Person();
      entities = teachingStuffService.search();
      availableClasses = classGroupService.search(ClassGroupQuery.setAvailableForTeachers(true));
   }

   @Override
   public void onLoad()
   {
      init();
   }

   @Override
   public void create()
   {
      entity.setPerson(person);
      entity.setAccount(account);
      teachingStuffService.create(entity);
      init();
   }

   @Override
   public void update(TeachingStuff object)
   {
      teachingStuffService.update(object);
      init();
   }

   @Override
   public void remove(Long id)
   {
      teachingStuffService.remove(id);
      init();
   }

   @Override
   public void setEntity(TeachingStuff entity)
   {
      super.setEntity(entity);
      ClassGroupQuery query = ClassGroupQuery.setAvailableForTeachers(true);
      final ClassGroup preceptorClass = entity.getPreceptorClass();
      if (preceptorClass != null)
      {
         query = ClassGroupQuery.setAvailableForCurrentTeacher(preceptorClass.getId());
      }
      availableClasses = classGroupService.search(query);
   }

   public void generatePassword(String formId, String componentId)
   {
      InputText passwordField = BeanHelper.getComponent(formId, componentId);
      String password = RandomPasswordGenerator.generate();
      passwordField.setSubmittedValue(password);
   }

   public SchoolSubject[] getSchoolSubjects()
   {
      return SchoolSubject.values();
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
