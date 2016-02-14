package sgr.app.api.assessment;

import java.util.List;

/**
 * @author dawbes
 */
public interface AssessmentService
{
   public void create(Assessment assessment);

   public List<Assessment> search(AssessmentQuery query);
}
