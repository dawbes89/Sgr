package sgr.app.api.assessment;
/**
 * @author dawbes
 */
public enum AssessmentType
{
   TEST("form_assessment_type_test"),
   EXAM("form_assessment_type_exam"),
   ORAL_ANSWER("form_assessment_type_oral_answer"),
   ACTIVITY("form_assessment_type_activity");

   private String label;

   private AssessmentType(String label)
   {
      this.label = label;
   }

   public String getLabel()
   {
      return label;
   }
}
