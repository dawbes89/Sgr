package sgr.app.frontend.validators;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import sgr.app.api.translation.TranslationService;

/**
 * Abstract base for validators.
 *
 * @author leonzio
 *
 * @param <T>
 *           type of validated value
 */
public abstract class AbstractValidator<T> implements Validator
{

   @Autowired
   protected TranslationService translationService;

   private final FacesMessage validationMessage;

   private boolean nullAllowed = false;

   protected AbstractValidator(Severity severity, String key)
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
      String translated = translationService.translate(key);
      validationMessage = new FacesMessage(severity, translated, translated);
   }

   @SuppressWarnings("unchecked")
   @Override
   final public void validate(FacesContext context, UIComponent component, Object value)
         throws ValidatorException
   {
      if (value == null && !nullAllowed)
      {
         return;
      }
      if (!isValidValue((T) value))
      {
         throw new ValidatorException(validationMessage);
      }
   }

   /**
    * Validates value.
    *
    * @param value
    *           to validate
    * @return true if value is valid, otherwise false
    */
   protected abstract boolean isValidValue(T value);

   /**
    * Allows to pass null to {@link #isValidValue(Object)}.<br>
    * Default is false.
    *
    * @param nullAllowed
    *           if true nulls is passed to {@link #isValidValue(Object)},
    *           otherwise not
    */
   protected void setNullAllowed(boolean nullAllowed)
   {
      this.nullAllowed = nullAllowed;
   }

}
