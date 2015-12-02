package sgr.admin.frontend.classgroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converter for year. Converts {@link String} value of year to {@link Date}.
 *
 * @author leonzio
 */
@FacesConverter("yearConverter")
public class YearConverter implements Converter
{

   private final static SimpleDateFormat YEAR_FORMATTER = new SimpleDateFormat("yyyy");

   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value)
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
   public String getAsString(FacesContext context, UIComponent component, Object value)
   {
      return value.toString();
   }

}
