package sgr.admin.webapp.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.translation.TranslationService;
import sgr.app.frontend.BeanHelper;

/**
 * @author dawbes
 */
@Controller
public class LoginPanel implements Serializable
{
   private static final long serialVersionUID = -7242960918445825945L;

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
      final InputText loginField = BeanHelper.getComponent("loginForm", "userNameInput");
      final Password passwordField = BeanHelper.getComponent("loginForm", "passwordInput");

      final boolean isAuthenticated = authenticationService.authenticateUser(
            loginField.getValue().toString(), passwordField.getValue().toString(), true);
      if (isAuthenticated)
      {
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
