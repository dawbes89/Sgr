package sgr.app.api.exceptions;

import javax.faces.application.FacesMessage.Severity;

/**
 * Exception throws when authentication don't go well.
 *
 * @author leonzio
 */
public class AuthenticationException extends CustomException
{

	private static final long serialVersionUID = 71574516963266685L;

	public AuthenticationException(String messageKey, Severity severity)
	{
		super(messageKey, severity);
	}

}
