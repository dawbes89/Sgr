package sgr.admin.webapp.admin;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.account.Account;
import sgr.app.api.admin.Admin;
import sgr.app.api.admin.AdminService;
import sgr.app.api.person.Person;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * @author dawbes
 */
@Controller
public class AdminPanel extends AbstractPanel<Admin>
implements EditablePanel<Admin>
{

   private static final long serialVersionUID = -2599849233906847054L;

   @Autowired
   private AdminService adminService;

   private Account account;

   private Person person;

   @Override
   public void init()
   {
      entity = new Admin();
      account = new Account();
      person = new Person();
      entities = adminService.search();
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
      adminService.create(entity);
      init();
   }

   @Override
   public void update(Admin object)
   {
      adminService.update(object);
      init();
   }

   @Override
   public void remove(Long id)
   {
      adminService.remove(id);
      init();
   }

   public void generatePassword(String formId, String componentId)
   {
      final InputText passwordField = BeanHelper.getComponent(formId, componentId);
      final String password = RandomPasswordGenerator.generate();
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
}
