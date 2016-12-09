package sgr.app.frontend.converters;

import javax.faces.component.UIComponent;

/**
 * @author dawbes89
 */
public class BooleanTranslationConverter extends AbstractTranslationConverter<Boolean>
{
	@Override
	protected Class<Boolean> getConvertedValueClass()
	{
		return Boolean.class;
	}

	@Override
	protected Boolean convertToObject(String value)
	{
		return Boolean.valueOf(value);
	}

	@Override
	protected String convertToString(Boolean object, UIComponent component)
	{
		final String convertedValue = object ? "common_yes" : "common_no";
		return translationService.translate(convertedValue);
	}

}
