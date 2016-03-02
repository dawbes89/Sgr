package sgr.app.frontend.converters;

import sgr.app.api.teachingstaff.SchoolSubject;

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
   protected String convertToString(Object object)
   {
      final SchoolSubject schoolSubject = (SchoolSubject) object;
      final String translate = translationService.translate(schoolSubject.getLabel());
      return translate;
   }
}
