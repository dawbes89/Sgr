package sgr.app.core.notification;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.notification.Notification;
import sgr.app.api.notification.NotificationQuery;
import sgr.app.api.notification.NotificationService;
import sgr.app.api.student.Student;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class NotificationServiceImpl extends DaoSupport implements NotificationService
{

   @Override
   public List<Notification> search(NotificationQuery query)
   {
      Criteria criteria = createCriteria(query);
      criteria.addOrder(Order.desc(Notification.PROPERTY_RECEIVED));
      return search(criteria);
   }

   private Criteria createCriteria(NotificationQuery query)
   {
      Criteria criteria = createCriteria(Notification.class);
      criteria.add(Restrictions.eq(nest(Notification.PROPERTY_STUDENT, Student.PROPERTY_ID),
            query.getStudentId()));
      return criteria;
   }

   @Override
   public void create(Notification notification)
   {
      notification.setReceived(new Date());
      createEntity(notification);
   }

}
