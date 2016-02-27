package sgr.app.frontend.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import sgr.app.api.DateHelper;
import sgr.app.frontend.StandardFormat;

/**
 * @author dawbes89
 */
public class DateValidator extends AbstractValidator<Date>
{

   private static DateValidationMode defaultMode = DateValidationMode.BEFORE;

   public DateValidator()
   {
      super(FacesMessage.SEVERITY_ERROR, defaultMode.getTranslationKey());
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
      final Date currentDate = DateHelper.getDateWithoutTime(new Date());
      setErrorMessage(mode.getTranslationKey(),
            new Object[] { component.getAttributes().get("label"),
                  StandardFormat.DAY_FORMAT.format(currentDate) });
      return mode.isValid(currentDate, value);
   }

}
