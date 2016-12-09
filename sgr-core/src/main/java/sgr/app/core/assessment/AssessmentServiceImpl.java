package sgr.app.core.assessment;

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
import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrService;
import sgr.app.api.student.Student;
import sgr.app.core.SgrDaoSupport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author dawbes89
 */
class AssessmentServiceImpl extends SgrDaoSupport implements AssessmentService
{

	private NotificationService notificationService;

	private SemestrService semestrService;

	@Override
	public void create(Assessment assessment)
	{
		notificationService.create(Notification.create("Oceny", "Otrzymałeś ocenę", assessment.getStudent()));
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
			criteria.add(Restrictions.eq(Assessment.PROPERTY_SCHOOL_SUBJECT, query.getSchoolSubject()));
		}
		if (query.hasStudentId())
		{
			criteria.add(Restrictions.eq(nest(Assessment.PROPERTY_STUDENT, Student.PROPERTY_ID), query.getStudentId()));
		}
		final Optional<Semestr> foundCurrentSemestr = semestrService.findCurrentSemestr();
		if (foundCurrentSemestr.isPresent())
		{
			final Semestr current = foundCurrentSemestr.get();
			criteria.add(Restrictions.between(Assessment.PROPERTY_DATE, current.getFrom(), current.getTo()));
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

	@Required
	public void setSemestrService(SemestrService semestrService)
	{
		this.semestrService = semestrService;
	}

}
