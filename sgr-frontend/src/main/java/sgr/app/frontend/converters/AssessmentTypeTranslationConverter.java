package sgr.app.frontend.converters;

import sgr.app.api.assessment.AssessmentType;

import javax.faces.component.UIComponent;

/**
 * Converts {@link AssessmentType} enum to translated value.
 *
 * @author dawbes89
 */
public class AssessmentTypeTranslationConverter extends AbstractTranslationConverter<AssessmentType>
{
	@Override
	protected Class<AssessmentType> getConvertedValueClass()
	{
		return AssessmentType.class;
	}

	@Override
	protected AssessmentType convertToObject(String value)
	{
		return AssessmentType.valueOf(value);
	}

	@Override
	protected String convertToString(AssessmentType object, UIComponent component)
	{
		return translationService.translate(object.getLabel());
	}
}
