package sgr.app.core.presence;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Required;
import sgr.app.api.DateHelper;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.lesson.Lesson;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceQuery;
import sgr.app.api.presence.PresenceService;
import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrService;
import sgr.app.api.student.Student;
import sgr.app.core.DaoSupport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author dawbes89
 */
class PresenceServiceImpl extends DaoSupport implements PresenceService
{

	private static final String PROPERTY_PERSON = nest(Presence.PROPERTY_STUDENT, Student.PROPERTY_PERSON);

	private SemestrService semestrService;

	@Override
	public void create(Presence presence)
	{
		presence.setDate(new Date());
		createEntity(presence);
	}

	@Override
	public List<Presence> search(PresenceQuery query)
	{
		final Criteria criteria = createCriteria(query);
		criteria.addOrder(Order.desc(Presence.PROPERTY_ID));
		return search(criteria);
	}

	@Override
	public void update(Presence presence)
	{
		updateEntity(presence);
	}

	private Criteria createCriteria(PresenceQuery query)
	{
		final Criteria criteria = createCriteria(Presence.class);
		if (query.hasStudentFullName())
		{
			final Criteria personCriteria = criteria.createCriteria(PROPERTY_PERSON);
			final MatchMode matchMode = MatchMode.ANYWHERE;
			final String matchString = matchMode.toMatchString(query.getStudentFullName());
			final String rawQuery = "concat(first_name, ' ', last_name) ilike ?";
			personCriteria.add(Restrictions.sqlRestriction(rawQuery, matchString, StandardBasicTypes.STRING));
		}
		if (query.hasStatus())
		{
			criteria.add(Restrictions.eq(Presence.PROPERTY_STATUS, query.getStatus()));
		}

		final Optional<Semestr> foundCurrentSemestr = semestrService.findCurrentSemestr();
		if (query.hasDate())
		{
			final Date date = query.getDate();
			final Date from = DateHelper.getDateWithTime(date, 0, 0, 0, 1);
			final Date to = DateHelper.getDateWithTime(date, 23, 59, 59, 999);
			criteria.add(Restrictions.between(Presence.PROPERTY_DATE, from, to));
		} else if (foundCurrentSemestr.isPresent())
		{
			final Semestr current = foundCurrentSemestr.get();
			criteria.add(Restrictions.between(Presence.PROPERTY_DATE, current.getFrom(), current.getTo()));
		}
		if (query.hasStudentId())
		{
			criteria.add(Restrictions.eq(nest(Presence.PROPERTY_STUDENT, Student.PROPERTY_ID), query.getStudentId()));
		}

		final Criteria lessonCriteria = criteria.createCriteria(Presence.PROPERTY_LESSON);
		if (query.hasLessonId())
		{
			lessonCriteria.add(Restrictions.eq(Lesson.PROPERTY_ID, query.getLessonId()));
		}
		if (query.hasClassGroupId())
		{
			lessonCriteria.add(Restrictions.eq(nest(Lesson.PROPERTY_CLASS_GROUP, ClassGroup.PROPERTY_ID),
			                                   query.getClassGroupId()));
		}
		if (query.hasSchoolSubject())
		{
			lessonCriteria.add(Restrictions.eq(Lesson.PROPERTY_SCHOOL_SUBJECT, query.getSchoolSubject()));
		}
		return criteria;
	}

	@Required
	public void setSemestrService(SemestrService semestrService)
	{
		this.semestrService = semestrService;
	}

}
