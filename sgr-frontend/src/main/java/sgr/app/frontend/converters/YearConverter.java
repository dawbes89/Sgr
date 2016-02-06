package sgr.app.frontend.converters;

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

   private final static SimpleDateFormat YEAR_FORMATTER = new SimpleDateFormat("yyyy");

   @Override
   protected Date convertToObject(String value)
   {
      Date date = null;
      try
      {
         date = YEAR_FORMATTER.parse(value);
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
      return object.toString();
   }

}
