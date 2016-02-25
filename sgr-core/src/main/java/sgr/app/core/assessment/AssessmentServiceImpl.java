package sgr.app.core.assessment;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.assessment.Assessment;
import sgr.app.api.assessment.AssessmentQuery;
import sgr.app.api.assessment.AssessmentService;
import sgr.app.api.notification.Notification;
import sgr.app.api.notification.NotificationService;
import sgr.app.api.student.Student;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes89
 */
class AssessmentServiceImpl extends DaoSupport implements AssessmentService
{

   private NotificationService notificationService;

   @Override
   public void create(Assessment assessment)
   {
      notificationService
            .create(Notification.create("Oceny", "Otrzyma³eœ ocenê", assessment.getStudent()));
      assessment.setDate(new Date());
      createEntity(assessment);
   }

   @Override
   public List<Assessment> search(AssessmentQuery query)
   {
      final Criteria criteria = createAssessmentCriteria(query);
      criteria.addOrder(Order.desc(Assessment.PROPERTY_DATE));
      return search(criteria);
   }

   private Criteria createAssessmentCriteria(AssessmentQuery query)
   {
      final Criteria criteria = createCriteria(Assessment.class);
      if (query.hasSchoolSubject())
      {
         criteria
               .add(Restrictions.eq(Assessment.PROPERTY_SCHOOL_SUBJECT, query.getSchoolSubject()));
      }
      if (query.hasStudentId())
      {
         criteria.add(Restrictions.eq(nest(Assessment.PROPERTY_STUDENT, Student.PROPERTY_ID),
               query.getStudentId()));
      }
      return criteria;
   }

   @Override
   public double getAverageAssesment(AssessmentQuery query)
   {
      final ProjectionList projList = Projections.projectionList();
      projList.add(Projections.avg("assessment"));

      final Criteria criteria = createAssessmentCriteria(query);
      criteria.setProjection(projList);
      final List<Object> search = search(criteria);
      if (search == null || search.isEmpty())
      {
         return 0.0;
      }
      final Object result = search.get(0);
      return result == null ? 0.0 : (double) result;
   }

   @Required
   public void setNotificationService(NotificationService notificationService)
   {
      this.notificationService = notificationService;
   }

}
