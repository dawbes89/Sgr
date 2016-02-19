package sgr.app.frontend.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import sgr.app.frontend.StandardFormat;

/**
 * Converter for year. Converts {@link String} value of year to {@link Date}.
 *
 * @author leonzio
 */
public class YearConverter extends AbstractConverter<Date>
{

   @Override
   protected Date convertToObject(String value)
   {
      Date date = null;
      try
      {
         date = StandardFormat.YEAR_FORMAT.parse(value);
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
         final Date date = StandardFormat.DAY_FORMAT.parse(stringDate);
         return StandardFormat.DAY_FORMAT.format(date);
      }
      catch (ParseException e)
      {
         e.printStackTrace();
         return object.toString();
      }
   }

}
