package sgr.app.frontend.converters;

import sgr.app.api.assessment.AssessmentType;

/**
 * Converts {@link AssessmentType} enum to translated value.
 *
 * @author dawbes89
 */
public class AssessmentTypeTranslationConverter extends AbstractTranslationConverter<AssessmentType>
{
   @Override
   protected AssessmentType convertToObject(String value)
   {
      return AssessmentType.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {
      final AssessmentType assessmentType = (AssessmentType) object;
      final String translate = translationService.translate(assessmentType.getLabel());
      return translate;
   }
}
