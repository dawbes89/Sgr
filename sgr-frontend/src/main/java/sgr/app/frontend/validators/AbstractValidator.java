package sgr.app.frontend.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sgr.app.api.translation.TranslationService;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Abstract base for validators.
 *
 * @param <T>
 * 		type of validated value
 * @author leonzio
 */
public abstract class AbstractValidator<T> implements Validator
{

	@Autowired
	protected TranslationService translationService;

	private String translatedValidationMessage;

	private Severity severity;

	private boolean nullAllowed = false;

	protected AbstractValidator(Severity severity, String key)
	{
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.severity = severity;
		setErrorMessage(key, new Object[]{});
	}

	protected void setErrorMessage(String key, Object[] values)
	{
		translatedValidationMessage = translationService.translate(key, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	final public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		if (value == null && !nullAllowed)
		{
			return;
		}
		if (!isValidValue((T) value, component))
		{
			throw new ValidatorException(
					new FacesMessage(severity, translatedValidationMessage, translatedValidationMessage));
		}
	}

	/**
	 * Validates value.
	 *
	 * @param value
	 * 		to validate
	 * @return true if value is valid, otherwise false
	 */
	protected abstract boolean isValidValue(T value, final UIComponent component);

	/**
	 * Allows to pass null to {@link #isValidValue(Object)}.<br>
	 * Default is false.
	 *
	 * @param nullAllowed
	 * 		if true nulls is passed to {@link #isValidValue(Object)}, otherwise not
	 */
	protected void setNullAllowed(boolean nullAllowed)
	{
		this.nullAllowed = nullAllowed;
	}

}
