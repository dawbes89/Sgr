package sgr.app.frontend.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter for year. Converts {@link String} value of year to {@link Date}.
 *
 * @author leonzio
 */
public class YearConverter extends AbstractConverter<Date>
{

   private final static SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
   private final static SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat("yyyy-mm-dd");

   @Override
   protected Date convertToObject(String value)
   {
      Date date = null;
      try
      {
         date = YEAR_FORMAT.parse(value);
      }
      catch (ParseException e)
      {
         e.printStackTrace();
      }
      return date;
   }

   @Override
   protected String convertToString(Object object)
   {
      try
      {
         Timestamp ts = null;
         try
         {
            ts = (Timestamp) object;
         }
         catch (ClassCastException e)
         {
            return object.toString();
         }
         final String stringDate = ts.toString();
         final Date date = YEAR_MONTH_DAY_FORMAT.parse(stringDate);
         return YEAR_MONTH_DAY_FORMAT.format(date);
      }
      catch (ParseException e)
      {
         e.printStackTrace();
      }
      return object.toString();
   }

}
