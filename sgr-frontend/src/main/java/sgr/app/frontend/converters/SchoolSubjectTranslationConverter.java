package sgr.app.frontend.converters;

import sgr.app.api.teachingstaff.SchoolSubject;

import javax.faces.component.UIComponent;


/**
 * Converts {@link SchoolSubject} enum to translated value.
 *
 * @author dawbes89
 */
public class SchoolSubjectTranslationConverter extends AbstractTranslationConverter<SchoolSubject>
{

	@Override
	protected SchoolSubject convertToObject(String value)
	{
		return SchoolSubject.valueOf(value);
	}

	@Override
	protected String convertToString(SchoolSubject object, UIComponent component)
	{
		return translationService.translate(object.getLabel());
	}
}
