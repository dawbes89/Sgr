package sgr.app.api.notification;

import java.util.List;

public interface NotificationService
{

   List<Notification> search(NotificationQuery query);

   void create(Notification notification);

}
