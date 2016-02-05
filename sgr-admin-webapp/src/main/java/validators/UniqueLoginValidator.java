package validators;

import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.validator.FacesValidator;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.frontend.AbstractValidator;

/**
 * @author leonzio
 */
@FacesValidator(value = "uniqueLoginValidator")
public class UniqueLoginValidator extends AbstractValidator<String>
{

   @Autowired
   private AccountService accountService;

   public UniqueLoginValidator()
   {
      super(FacesMessage.SEVERITY_ERROR, "validation.loginIsUsed");
   }

   @Override
   protected boolean isValidValue(String value)
   {
      final Optional<Account> findAccountByLogin = accountService.findAccountByLogin(value);
      return !findAccountByLogin.isPresent();
   }

}
