package sgr.app.frontend.converters;

import sgr.app.api.teachingStuff.SchoolSubject;

/**
 * Converts {@link SchoolSubject} enum to translated value.
 *
 * @author dawbes89
 */
public class SchoolSubjectTranslationConverter extends AbstractConverter<SchoolSubject>
{

   @Override
   protected SchoolSubject convertToObject(String value)
   {
      return SchoolSubject.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {
      final SchoolSubject schoolSubject = (SchoolSubject) object;
      String translate = translationService.translate(schoolSubject.getLabel());
      return translate;
   }
}
