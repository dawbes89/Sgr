package sgr.app.core.assessment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.assessment.Assessment;
import sgr.app.api.assessment.AssessmentQuery;
import sgr.app.api.assessment.AssessmentService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes89
 */
public class AssessmentServiceImpl extends DaoSupport implements AssessmentService
{

   @Override
   public void create(Assessment assessment)
   {
      createEntity(assessment);
   }

   @Override
   public List<Assessment> search(AssessmentQuery query)
   {
      Criteria criteria = createAssessmentCriteria(query);
      return search(criteria);
   }

   private Criteria createAssessmentCriteria(AssessmentQuery query)
   {
      Criteria criteria = createCriteria(Assessment.class);
      if(query.hasSchoolSubject())
      {
         criteria.add(Restrictions.eq(Assessment.PROPERTY_SCHOOLSUBJECT, query.getSchoolSubject()));
      }
      if(query.hasStudentId())
      {
         criteria.add(Restrictions.eq(Assessment.PROPERTY_STUDENT_ID, query.getStudentId()));
      }
      return criteria;
   }

}
