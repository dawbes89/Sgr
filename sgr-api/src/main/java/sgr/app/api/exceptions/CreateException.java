package sgr.app.api.exceptions;

import javax.faces.application.FacesMessage.Severity;

/**
 * Exception throws when actions carried out on create action in serwice don't
 * go well.
 *
 * @author dawbes89
 * @author leonzio
 */
public class CreateException extends CustomException
{

   private static final long serialVersionUID = -7621250000228483730L;

   public CreateException(String messageKey, Severity severity)
   {
      super(messageKey, severity);
   }
}
