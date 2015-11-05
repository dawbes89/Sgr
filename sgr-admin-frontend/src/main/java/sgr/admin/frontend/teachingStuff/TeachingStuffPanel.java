package sgr.admin.frontend.teachingStuff;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.account.Account;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.teachingStuff.TeachingStuffService;
import sgr.commons.core.RandomPasswordGenerator;

/**
 * @author dawbes
 */
@Controller
@ManagedBean(name = "teachingStuffPanel")
@ViewScoped
public class TeachingStuffPanel implements Serializable
{

   private static final long serialVersionUID = 2553933126154263063L;

   private TeachingStuff teachingStuff = new TeachingStuff();
   // REVIEW a ten account jest potrzebny?
   private Account account = new Account();

   private List<TeachingStuff> teachingStuffs;

   @Autowired
   private TeachingStuffService teachingStuffService;

   public TeachingStuffPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   @PostConstruct
   public void init()
   {
      teachingStuffs = teachingStuffService.search();
   }

   public void addTeacher()
   {
      teachingStuff.setAccount(account);
      teachingStuffService.create(teachingStuff);
      teachingStuffs = teachingStuffService.search();
      teachingStuff = new TeachingStuff();
      account = new Account();
   }

   public void deleteTeacher(Long id)
   {
      teachingStuffService.remove(id);
      teachingStuffs = teachingStuffService.search();
   }

   public void updateTeacher(TeachingStuff teachingStuff)
   {
      teachingStuffService.update(teachingStuff);
      teachingStuffs = teachingStuffService.search();
      teachingStuff = new TeachingStuff();
   }

   public void generatePassword()
   {
      InputText passwordField = (InputText) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("add:password");
      String password = RandomPasswordGenerator.generate();
      passwordField.setSubmittedValue(password);
   }

   public TeachingStuff getTeachingStuff()
   {
      return teachingStuff;
   }

   public void setTeachingStuff(TeachingStuff teachingStuff)
   {
      this.teachingStuff = teachingStuff;
   }

   public List<TeachingStuff> getTeachingStuffs()
   {
      return teachingStuffs;
   }

   public void setTeachingStuffs(List<TeachingStuff> teachingStuffs)
   {
      this.teachingStuffs = teachingStuffs;
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
