package sgr.app.frontend.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;

public class DateValidator extends AbstractValidator<Date>
{
   protected DateValidationMode mode;

   public DateValidator(DateValidationMode mode)
   {
      super(FacesMessage.SEVERITY_ERROR, mode.getTranslationKey());
      this.mode = mode;
   }

   @Override
   protected boolean isValidValue(Date value)
   {
      return mode.isValid(new Date(), value);
   }

}
