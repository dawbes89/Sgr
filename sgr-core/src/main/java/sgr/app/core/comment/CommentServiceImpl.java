package sgr.app.core.comment;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;
import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
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
class CommentServiceImpl extends SgrDaoSupport implements CommentService
{

	private NotificationService notificationService;

	private SemestrService semestrService;

	@Override
	public void create(Comment comment)
	{
		notificationService.create(Notification.create("Uwagi", "Otrzymałeś uwagę", comment.getStudent()));
		comment.setDate(new Date());
		createEntity(comment);
	}

	@Override
	public List<Comment> findByStudentId(Long studentId)
	{
		final Criteria criteria = createCriteria(Comment.class);
		criteria.add(Restrictions.eq(nest(Comment.PROPERTY_STUDENT, Student.PROPERTY_ID), studentId));
		criteria.addOrder(Order.desc(Comment.PROPERTY_DATE));
		final Optional<Semestr> foundCurrentSemestr = semestrService.findCurrentSemestr();
		if (foundCurrentSemestr.isPresent())
		{
			final Semestr current = foundCurrentSemestr.get();
			criteria.add(Restrictions.between(Comment.PROPERTY_DATE, current.getFrom(), current.getTo()));
		}
		return search(criteria);
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
