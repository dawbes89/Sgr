package sgr.app.frontend.converters;

import sgr.app.frontend.helpers.StandardFormat;

import javax.faces.component.UIComponent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * Converter for year. Converts {@link String} value of year to {@link Date}.
 *
 * @author leonzio
 */
// TODO improve converter
public class YearConverter extends AbstractConverter<Date>
{
	@Override
	protected Class<Date> getConvertedValueClass()
	{
		return Date.class;
	}

	@Override
	protected Date convertToObject(String value)
	{
		try
		{
			return StandardFormat.YEAR_FORMAT.parse(value);
		}
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String convertToString(Date object, UIComponent component)
	{
		try
		{
			Timestamp ts;
			try
			{
				ts = (Timestamp) object;
			}
			catch (ClassCastException e)
			{
				return object.toString();
			}
			String stringDate = ts.toString();
			Date date = StandardFormat.DAY_FORMAT.parse(stringDate);
			return StandardFormat.DAY_FORMAT.format(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return object.toString();
		}
	}

}
