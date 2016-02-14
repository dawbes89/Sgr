package sgr.app.api.exceptions;

import javax.faces.application.FacesMessage.Severity;

/**
 * Base for exception used in application.
 * 
 * @author leonzio
 */
public abstract class CustomException extends Exception
{

   private static final long serialVersionUID = 1862306239081386331L;

   private Severity severity;

   protected CustomException(String messageKey, Severity severity)
   {
      super(messageKey);
      this.severity = severity;
   }

   public void setSeverity(Severity severity)
   {
      this.severity = severity;
   }

   public Severity getSeverity()
   {
      return severity;
   }

}
