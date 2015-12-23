package sgr.admin.frontend.teachingStuff;

import java.util.List;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.account.Account;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.commons.core.RandomPasswordGenerator;
import sgr.commons.frontend.AbstractPanel;
import sgr.commons.frontend.Bean;
import sgr.commons.frontend.EditablePanel;

/**
 * @author dawbes
 */
@Controller
public class TeachingStuffPanel extends AbstractPanel<TeachingStuff> implements
      EditablePanel<TeachingStuff>
{

   private static final long serialVersionUID = 2553933126154263063L;

   @Autowired
   private TeachingStuffService teachingStuffService;

   @Autowired
   private ClassGroupService classGroupService;

   private InputText passwordField;

   private Account account;

   private List<ClassGroup> availableClasses;

   @Override
   public void init()
   {
      entity = new TeachingStuff();
      account = new Account();
      entities = teachingStuffService.search();
      availableClasses = classGroupService.search(ClassGroupQuery.setAvailableForTeacher(true));
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

   public void generatePassword(String component)
   {
      passwordField = Bean.get("add", "password");
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

   public List<ClassGroup> getAvailableClasses()
   {
      return availableClasses;
   }
}
