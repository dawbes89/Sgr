package sgr.app.frontend.loginPanel;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.account.AccountService;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel implements Serializable
{
   private static final long serialVersionUID = -7242960918445825945L;

   @Autowired
   public AccountService accountService;

   //REVIEW nazwa metody? checKLogin jak już eventualnie loginUser - zaloguj uzytkownika
   public String chectLogin() throws IOException
   {
      //REVIEW jak mówiłem trzeba zmienić te metody na taką która zwraca użytkownika przy udanym logowaniu
      // lub wyrzuca jakiś exception nawet jakiś nasz napisać przy nieudanym      
      InputText loginField = (InputText) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("loginForm:loginInput");

      Password passwordField = (Password) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("loginForm:passwordInput");
      boolean existUser = accountService.checkLogin(loginField.getValue().toString(), passwordField
            .getValue().toString());
      if (existUser)
         return "index";
      else
         return "";
   }

}
