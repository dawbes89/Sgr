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
	protected PresenceStatus convertToObject(String value)
	{
		return PresenceStatus.valueOf(value);
	}

	@Override
	protected String convertToString(Object object, UIComponent component)
	{
		final PresenceStatus presenceStatus = (PresenceStatus) object;
		final String translate = translationService.translate(presenceStatus.getLabel());
		return translate;
	}
}
