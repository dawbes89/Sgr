package sgr.app.frontend.converters;

import sgr.app.api.presence.PresenceStatus;

import javax.faces.component.UIComponent;

/**
 * Converts {@link PresenceStatus} enum to translated value.
 *
 * @author dawbes89
 */
public class PresenceStatusTranslationConverter extends AbstractTranslationConverter<PresenceStatus>
{
	@Override
	protected Class<PresenceStatus> getConvertedValueClass()
	{
		return PresenceStatus.class;
	}

	@Override
	protected PresenceStatus convertToObject(String value)
	{
		return PresenceStatus.valueOf(value);
	}

	@Override
	protected String convertToString(PresenceStatus object, UIComponent component)
	{
		return translationService.translate(object.getLabel());
	}
}
