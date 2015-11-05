package sgr.admin.teachingStuff;

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

import sgr.api.account.Account;
import sgr.api.teachingStuff.TeachingStuff;
import sgr.api.teachingStuff.TeachingStuffService;
import sgr.commons.RandomPasswordGenerator;

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

   public void deleteTeacher(TeachingStuff teachingStuff)
   {
      teachingStuffService.remove(teachingStuff.getId());
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
      InputText inputCriteriaStr = (InputText) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("addForm:password");
      String password = RandomPasswordGenerator.generate();
      inputCriteriaStr.setSubmittedValue(password);
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
