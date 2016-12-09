package sgr.app.frontend.converters;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.Objects;

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
		return Objects.nonNull(value) ? convertToObject(value) : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		return Objects.nonNull(value) ? convertToString(getConvertedValueClass().cast(value), component) : null;
	}

	protected abstract Class<T> getConvertedValueClass();

	protected abstract T convertToObject(String value);

	protected abstract String convertToString(T object, UIComponent component);

}
