package sgr.app.frontend.converters;

import java.util.HashMap;
import java.util.Map;
/**
 * @author dawbes
 */
public class BooleanTranslationConverter extends AbstractConverter<Boolean>
{

   private Map<Boolean, String> booleanMap = new HashMap<>();

   public BooleanTranslationConverter()
   {
      booleanMap.put(true, "form_yes");
      booleanMap.put(false, "form_no");
   }

   @Override
   protected Boolean convertToObject(String value)
   {
      return Boolean.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {
      String translate = translationService.translate(booleanMap.get(object));
      return translate;
   }

}
