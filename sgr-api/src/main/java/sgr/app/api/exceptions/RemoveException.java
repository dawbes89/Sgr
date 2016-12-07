package sgr.app.api.exceptions;

import javax.faces.application.FacesMessage.Severity;

/**
 * Exception throws when the removal action don't go well.
 *
 * @author dawbes89
 */
public class RemoveException extends CustomException
{

	private static final long serialVersionUID = 3387304826657788351L;

	public RemoveException(String messageKey, Severity severity)
	{
		super(messageKey, severity);
	}

}
