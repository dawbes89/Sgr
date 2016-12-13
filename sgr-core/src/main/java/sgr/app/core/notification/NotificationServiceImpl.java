package sgr.app.core.notification;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;
import sgr.app.api.notification.Notification;
import sgr.app.api.notification.NotificationQuery;
import sgr.app.api.notification.NotificationService;
import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrService;
import sgr.app.api.student.Student;
import sgr.app.core.util.SgrDaoSupport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
class NotificationServiceImpl extends SgrDaoSupport implements NotificationService
{

	private SemestrService semestrService;

	@Override
	public List<Notification> search(NotificationQuery query)
	{
		final Criteria criteria = createCriteria(query);
		criteria.addOrder(Order.desc(Notification.PROPERTY_RECEIVED));
		return search(criteria);
	}

	private Criteria createCriteria(NotificationQuery query)
	{
		final Criteria criteria = createCriteria(Notification.class);
		criteria.add(Restrictions.eq(nest(Notification.PROPERTY_STUDENT, Student.PROPERTY_ID), query.getStudentId()));
		final Optional<Semestr> foundCurrentSemestr = semestrService.findCurrentSemestr();
		if (foundCurrentSemestr.isPresent())
		{
			final Semestr current = foundCurrentSemestr.get();
			criteria.add(Restrictions.between(Notification.PROPERTY_RECEIVED, current.getFrom(), current.getTo()));
		}
		return criteria;
	}

	@Override
	public void create(Notification notification)
	{
		notification.setReceived(new Date());
		create(notification);
	}

	@Required
	public void setSemestrService(SemestrService semestrService)
	{
		this.semestrService = semestrService;
	}

}
