package sgr.app.frontend.converters;

import javax.faces.component.UIComponent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dawbes89
 */
public class BooleanTranslationConverter extends AbstractTranslationConverter<Boolean>
{

	private final Map<Boolean, String> booleanMap = new HashMap<>();

	public BooleanTranslationConverter()
	{
		booleanMap.put(true, "common_yes");
		booleanMap.put(false, "common_no");
	}

	@Override
	protected Boolean convertToObject(String value)
	{
		return Boolean.valueOf(value);
	}

	@Override
	protected String convertToString(Boolean object, UIComponent component)
	{
		return translationService.translate(booleanMap.get(object));
	}

}
