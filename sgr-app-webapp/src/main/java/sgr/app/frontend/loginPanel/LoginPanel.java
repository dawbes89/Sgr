package sgr.app.frontend.loginPanel;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.login.LoginService;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel implements Serializable
{
   private static final long serialVersionUID = -7242960918445825945L;

   @Autowired
   public LoginService loginService;

   public <T> void checkLogin() throws IOException
   {
      InputText loginField = (InputText) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("loginForm:loginInput");

      Password passwordField = (Password) FacesContext.getCurrentInstance().getViewRoot()
            .findComponent("loginForm:passwordInput");
      Optional<T> existUser = loginService.checkLogin(loginField.getValue().toString(),
            passwordField.getValue().toString());
      ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
      if (existUser.isPresent())
      {
         ec.redirect(ec.getRequestContextPath() + "/app/index.jsf");
      }
      else
      {
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d",
               "Nie poprawny login lub has³o");
         RequestContext.getCurrentInstance().showMessageInDialog(message);
      }
   }
}
