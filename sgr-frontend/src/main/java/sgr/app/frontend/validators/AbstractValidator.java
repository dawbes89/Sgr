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
import java.util.Objects;

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

	private final Severity severity;
	private String translatedValidationMessage;
	private boolean nullAllowed;

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

	@Override
	final public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		if (Objects.isNull(value) && !nullAllowed)
		{
			return;
		}
		if (!isValidValue(getValueClass().cast(value), component))
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
	 * Gets class of validated <code>value</code>.
	 *
	 * @return value class
	 */
	protected abstract Class<T> getValueClass();

	/**
	 * Allows to pass null to {@link #isValidValue(Object, UIComponent)}}. Default is false.
	 *
	 * @param nullAllowed
	 * 		if true nulls is passed to {@link #isValidValue(Object, UIComponent)}, otherwise not
	 */
	protected void setNullAllowed(boolean nullAllowed)
	{
		this.nullAllowed = nullAllowed;
	}

}
