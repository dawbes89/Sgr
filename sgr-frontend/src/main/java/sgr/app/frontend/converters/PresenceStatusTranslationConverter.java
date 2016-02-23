package sgr.app.frontend.converters;

import sgr.app.api.presence.PresenceStatus;

/**
 * Converts {@link PresenceStatus} enum to translated value.
 *
 * @author dawbes89
 */
public class PresenceStatusTranslationConverter extends AbstractConverter<PresenceStatus>
{
   @Override
   protected PresenceStatus convertToObject(String value)
   {
      return PresenceStatus.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {
      final PresenceStatus presenceStatus = (PresenceStatus) object;
      String translate = translationService.translate(presenceStatus.getLabel());
      return translate;
   }
}
