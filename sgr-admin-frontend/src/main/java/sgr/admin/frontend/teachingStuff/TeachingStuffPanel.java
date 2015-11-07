package sgr.admin.frontend.teachingStuff;

import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.account.Account;
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

   private Account account;

   @Autowired
   private TeachingStuffService teachingStuffService;

   @Bean(form = "add", name = "password")
   private InputText passwordField;

   @Override
   public void init()
   {
      entity = new TeachingStuff();
      account = new Account();
   }

   @Override
   public void onLoad()
   {
      InputText passwordField1 = (InputText) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("add:password");
      entities = teachingStuffService.search();
   }

   @Override
   public void create()
   {
      entity.setAccount(account);
      teachingStuffService.create(entity);
      entities = teachingStuffService.search();
      entity = new TeachingStuff();
      account = new Account();
   }

   @Override
   public void update(TeachingStuff object)
   {
      teachingStuffService.update(object);
      entities = teachingStuffService.search();
      entity = new TeachingStuff();
   }

   @Override
   public void remove(Long id)
   {
      teachingStuffService.remove(id);
      entities = teachingStuffService.search();
   }

   public void generatePassword(String component)
   {
      InputText passwordField1 = (InputText) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent(component);
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

}
