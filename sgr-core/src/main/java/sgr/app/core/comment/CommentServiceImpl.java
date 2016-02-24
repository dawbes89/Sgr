package sgr.app.core.comment;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.comment.Comment;
import sgr.app.api.comment.CommentService;
import sgr.app.api.notification.Notification;
import sgr.app.api.notification.NotificationService;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes89
 */
class CommentServiceImpl extends DaoSupport implements CommentService
{

   private NotificationService notificationService;

   @Override
   public void create(Comment comment)
   {
      notificationService
            .create(Notification.create("Uwagi", "Otrzyma³eœ uwagê", comment.getStudentId()));
      comment.setDate(new Date());
      createEntity(comment);
   }

   @Override
   public List<Comment> findByStudentId(Long studentId)
   {
      Criteria criteria = createCriteria(Comment.class);
      criteria.add(Restrictions.eq(Comment.PROPERTY_STUDENT_ID, studentId));
      criteria.addOrder(Order.desc(Comment.PROPERTY_DATE));
      return search(criteria);
   }

   @Required
   public void setNotificationService(NotificationService notificationService)
   {
      this.notificationService = notificationService;
   }

}
