package sgr.app.webapp.loginPanel;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.login.LoginService;
import sgr.app.api.translation.TranslationService;
import sgr.app.frontend.Bean;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel implements Serializable
{
   private static final long serialVersionUID = -7242960918445825945L;

   @Autowired
   private LoginService loginService;

   @Autowired
   private TranslationService translationService;

   @Autowired
   private AuthenticationService authenticationService;

   public LoginPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   public <T> void checkLogin() throws IOException
   {
      final InputText loginField = Bean.getComponent("loginForm", "loginInput");
      final Password passwordField = Bean.getComponent("loginForm", "passwordInput");

      final Optional<T> existUser = loginService.checkLogin(loginField.getValue().toString(),
            passwordField.getValue().toString());

      if (existUser.isPresent())
      {
         authenticationService.loginUser(existUser.get());
         final ExternalContext externalContext = FacesContext.getCurrentInstance()
               .getExternalContext();
         externalContext.redirect(externalContext.getRequestContextPath() + "/app/index.xhtml");
      }
      else
      {
         final String validationMessage = translationService.translate("validation_loginUserError");
         final FacesMessage message = new FacesMessage(validationMessage);
         message.setSeverity(FacesMessage.SEVERITY_ERROR);
         FacesContext.getCurrentInstance().addMessage("loginForm", message);
      }
   }

   public void logout() throws IOException
   {
      authenticationService.logoutUser();
      final ExternalContext externalContext = FacesContext.getCurrentInstance()
            .getExternalContext();
      externalContext.redirect(externalContext.getRequestContextPath());
   }
}
