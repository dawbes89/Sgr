package sgr.app.webapp.student;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.notification.Notification;
import sgr.app.api.notification.NotificationQuery;
import sgr.app.api.notification.NotificationService;
import sgr.app.api.student.Student;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * Panel used for displaing notifications for student.
 *
 * @author leonzio
 */
public class StudentNotificationPanel extends AbstractPanel<Notification>
{

   private static final long serialVersionUID = 4409968623088196549L;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private NotificationService notificationService;

   @Override
   public void init()
   {
      entity = new Notification();
      final NotificationQuery query = new NotificationQuery();
      final Student student = authenticationService.getCurrentUser();
      query.setStudentId(student.getId());
      entities = notificationService.search(query);
   }

   @Override
   public void onLoad()
   {
      init();
   }

}
