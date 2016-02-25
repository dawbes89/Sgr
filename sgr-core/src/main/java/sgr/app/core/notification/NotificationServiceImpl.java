package sgr.app.core.notification;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.notification.Notification;
import sgr.app.api.notification.NotificationQuery;
import sgr.app.api.notification.NotificationService;
import sgr.app.core.DaoSupport;

class NotificationServiceImpl extends DaoSupport implements NotificationService
{

   @Override
   public List<Notification> search(NotificationQuery query)
   {
      Criteria criteria = createCriteria(query);
      criteria.addOrder(Order.desc("received"));
      return search(criteria);
   }

   private Criteria createCriteria(NotificationQuery query)
   {
      Criteria criteria = createCriteria(Notification.class);
      criteria.add(Restrictions.eq("student", query.getStudentId()));
      return criteria;
   }

   @Override
   public void create(Notification notification)
   {
      notification.setReceived(new Date());
      createEntity(notification);
   }

}
