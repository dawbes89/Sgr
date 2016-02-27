package sgr.app.frontend.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

public class DateValidator extends AbstractValidator<Date>
{

   private static DateValidationMode defaultMode = DateValidationMode.BEFORE;

   public DateValidator()
   {
      super(FacesMessage.SEVERITY_ERROR, defaultMode.getLabel());
   }

   @Override
   protected boolean isValidValue(Date value, final UIComponent component)
   {
      final String findMode = (String) component.getAttributes().get("validationMode");
      DateValidationMode mode = defaultMode;
      if (findMode != null && DateValidationMode.valueOf(findMode) != null)
      {
         mode = DateValidationMode.valueOf(findMode);
      }
      setErrorMessage(mode.getLabel(), new Object[] { component.getAttributes().get("label") });
      return mode.isValid(new Date(), value);
   }

}
