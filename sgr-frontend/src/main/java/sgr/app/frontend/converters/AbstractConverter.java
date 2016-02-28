package sgr.app.frontend.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Abstract base for converters.
 *
 * @author leonzio
 */
public abstract class AbstractConverter<T> implements Converter
{

   public AbstractConverter()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   }

   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value)
   {
      if (value == null)
      {
         return null;
      }
      return convertToObject(value);
   }

   @Override
   public String getAsString(FacesContext context, UIComponent component, Object value)
   {
      if (value == null)
      {
         return null;
      }
      return convertToString(value);
   }

   protected abstract T convertToObject(String value);

   protected abstract String convertToString(Object object);

}
