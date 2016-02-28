package sgr.app.api.exceptions;

import javax.faces.application.FacesMessage.Severity;

/**
 * Exception throws when actions carried out on classGroup don't go well.
 *
 * @author dawbes89
 */
public class ClassGroupException extends CustomException
{

   private static final long serialVersionUID = -7621250000228483730L;

   public ClassGroupException(String messageKey, Severity severity)
   {
      super(messageKey, severity);
   }
}
