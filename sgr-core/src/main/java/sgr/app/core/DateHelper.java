package sgr.app.core;

import java.util.Calendar;
import java.util.Date;

/**
 * Date helper.
 *
 * @author leonzio
 *
 */
public final class DateHelper
{

   public static Date getDateWithoutTime(Date date)
   {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      return cal.getTime();
   }

   public static Date getDateWithTime(Date date, int hour, int minute, int second, int milis)
   {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.HOUR_OF_DAY, hour);
      cal.set(Calendar.MINUTE, minute);
      cal.set(Calendar.SECOND, second);
      cal.set(Calendar.MILLISECOND, milis);
      return cal.getTime();
   }
}
