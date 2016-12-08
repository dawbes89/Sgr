package sgr.app.core.translation;

import sgr.app.api.translation.TranslationService;

import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author leonzio
 */
class TranslationServiceImpl implements TranslationService
{

	private static final String DEFAULT_TRANSLATION_FILE = "sgr_translation";

	@Override
	public String translate(String key)
	{
		return translate(DEFAULT_TRANSLATION_FILE, key);
	}

	@Override
	public String translate(String resourceFileName, String key)
	{
		return translate(resourceFileName, key, new Object[0]);
	}

	@Override
	public String translate(String key, Object params[])
	{
		return translate(DEFAULT_TRANSLATION_FILE, key, params);
	}

	private String translate(String resourceFileName, String key, Object params[])
	{
		ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(
				FacesContext.getCurrentInstance(), resourceFileName);

		String text;
		try
		{
			text = resourceBundle.getString(key);
		}
		catch (MissingResourceException e)
		{
			return null;
		}
		if (params == null)
		{
			return text;
		}

		MessageFormat mf = new MessageFormat(text);
		text = mf.format(params, new StringBuffer(), null).toString();

		return text;
	}

}
