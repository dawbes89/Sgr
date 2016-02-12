package sgr.app.frontend.panels;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.translation.TranslationService;

/**
 * Abstract base for login panels.
 *
 * @author leonzio
 */
public abstract class AbstractLoginPanel implements Serializable
{

   private static final long serialVersionUID = 8771925513486552329L;

   private static final String MAIN_PAGE = String.format("/app/%s.xhtml",
         AuthenticationService.MAIN_PAGE);

   @Autowired
   protected TranslationService translationService;

   @Autowired
   protected AuthenticationService authenticationService;

   private LoginBean loginBean;

   public AbstractLoginPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   @PostConstruct
   public void init()
   {
      loginBean = new LoginBean();
   }

   public void checkLogin() throws IOException
   {
      final boolean isAuthenticated = authenticationService
            .authenticateUser(loginBean.getUserName(), loginBean.getPassword(), false);
      if (isAuthenticated)
      {
         final ExternalContext externalContext = FacesContext.getCurrentInstance()
               .getExternalContext();
         externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
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

   public final LoginBean getLoginBean()
   {
      return loginBean;
   }

   public final void setLoginBean(LoginBean loginBean)
   {
      this.loginBean = loginBean;
   }
}
