package sgr.app.core.lesson;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;
import sgr.app.api.DateHelper;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.lesson.Lesson;
import sgr.app.api.lesson.LessonQuery;
import sgr.app.api.lesson.LessonService;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceService;
import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrService;
import sgr.app.core.util.SgrDaoSupport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author dawbes89
 */
class LessonServiceImpl extends SgrDaoSupport implements LessonService
{
	private static final String PROPERTY_CLASS_GROUP_ID = Lesson.PROPERTY_CLASS_GROUP + "." + ClassGroup.PROPERTY_ID;

	private PresenceService presenceService;

	private SemestrService semestrService;

	@Override
	public Lesson create(Lesson lesson, List<Presence> presences)
	{
		lesson.setDate(new Date());
		final Lesson created = create(lesson);
		presences.stream().peek(presence -> presence.setLesson(created)).forEach(
				presence -> presenceService.create(presence));
		return created;
	}

	@Override
	public List<Lesson> search(LessonQuery query)
	{
		final Criteria criteria = createLessonCriteria(query);
		criteria.addOrder(Order.desc("id"));
		return search(criteria);
	}

	@Override
	public Optional<Lesson> find(LessonQuery query)
	{
		final Criteria criteria = createLessonCriteria(query);
		return find(criteria);
	}

	private Criteria createLessonCriteria(LessonQuery query)
	{
		final Criteria criteria = createCriteria(Lesson.class);
		if (query.hasSchoolSubject())
		{
			criteria.add(Restrictions.eq(Lesson.PROPERTY_SCHOOL_SUBJECT, query.getSchoolSubject()));
		}
		if (query.hasStudentId())
		{
			criteria.add(Restrictions.eq(Lesson.PROPERTY_STUDENT, query.getStudentId()));
		}
		if (query.hasClassGroupId())
		{
			criteria.add(Restrictions.eq(PROPERTY_CLASS_GROUP_ID, query.getClassGroupId()));
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
			criteria.add(Restrictions.between(Lesson.PROPERTY_DATE, current.getFrom(), current.getTo()));
		}
		if (query.hasLessonNumber())
		{
			criteria.add(Restrictions.eq(Lesson.PROPERTY_LESSON_NUMBER, query.getLessonNumber()));
		}

		return criteria;
	}

	@Override
	public Presence createPresence(Presence presence)
	{
		return create(presence);
	}

	@Required
	public void setPresenceService(PresenceService presenceService)
	{
		this.presenceService = presenceService;
	}

	@Required
	public void setSemestrService(SemestrService semestrService)
	{
		this.semestrService = semestrService;
	}

}
