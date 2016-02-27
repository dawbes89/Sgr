package sgr.app.frontend.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;

public class DateValidator extends AbstractValidator<Date>
{
   protected DateValidationMode mode;

   public DateValidator(DateValidationMode mode)
   {
      super(FacesMessage.SEVERITY_ERROR, mode.getLabel());
      this.mode = mode;
   }

   @Override
   protected boolean isValidValue(Date value)
   {
      if (value == null)
      {
         return true;
      }
      return mode.isValid(new Date(), value);
   }

}
