package sgr.app.frontend.validators;

import sgr.app.api.DateHelper;
import sgr.app.frontend.helpers.StandardFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import java.util.Date;

/**
 * Date validator with {@link DateValidationMode} used when value is validated. Default is {@link
 * DateValidationMode#BEFORE}, for custom validation mode use<code>f:attribute name="validationMode" value="{@link
 * DateValidationMode}"</code>.
 *
 * @author dawbes89
 * @author leonzio
 */
public class DateValidator extends AbstractValidator<Date>
{

	private static final DateValidationMode DEFAULT_MODE = DateValidationMode.BEFORE;

	public DateValidator()
	{
		super(FacesMessage.SEVERITY_ERROR, DEFAULT_MODE.getTranslationKey());
	}

	@Override
	protected Class<Date> getValueClass()
	{
		return Date.class;
	}

	@Override
	protected boolean isValidValue(Date value, final UIComponent component)
	{
		final String findMode = (String) component.getAttributes().get("validationMode");
		DateValidationMode mode = findMode == null ? DEFAULT_MODE : DateValidationMode.valueOf(findMode);

		final Date currentDate = DateHelper.getDateWithoutTime(new Date());
		Object[] messageParameters = {component.getAttributes().get("label"), StandardFormat.DAY_FORMAT.format(
				currentDate)};
		setErrorMessage(mode.getTranslationKey(), messageParameters);
		return mode.isValid(currentDate, value);
	}

}
