package sgr.app.api.assessment;

import java.util.List;

/**
 * @author dawbes
 */
public interface AssessmentService
{
   void create(Assessment assessment);

   List<Assessment> search(AssessmentQuery query);

   double getAverageAssesment(AssessmentQuery query);
}
