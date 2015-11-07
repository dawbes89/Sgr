package sgr.admin.frontend.teachingStuff;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.account.Account;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.commons.core.RandomPasswordGenerator;
import sgr.commons.frontend.AbstractPanel;

/**
 * @author dawbes
 */
@Controller
@ManagedBean(name = "teachingStuffPanel")
public class TeachingStuffPanel extends AbstractPanel<TeachingStuff>
{

   private static final long serialVersionUID = 2553933126154263063L;

   private Account account = new Account();

   @Autowired
   private TeachingStuffService teachingStuffService;

   public TeachingStuffPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

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

   public void addTeacher()
   {
      entity.setAccount(account);
      teachingStuffService.create(entity);
      entities = teachingStuffService.search();
      entity = new TeachingStuff();
      account = new Account();
   }

   public void deleteTeacher(Long id)
   {
      teachingStuffService.remove(id);
      entities = teachingStuffService.search();
   }

   public void updateTeacher(TeachingStuff teachingStuff)
   {
      teachingStuffService.update(teachingStuff);
      entities = teachingStuffService.search();
      teachingStuff = new TeachingStuff();
   }

   public void generatePassword(String component)
   {
      InputText passwordField = (InputText) FacesContext.getCurrentInstance().getViewRoot()
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
