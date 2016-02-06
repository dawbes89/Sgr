package sgr.app.core.translation;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import sgr.app.api.translation.TranslationService;

/**
 * @author leonzio
 */
public class TranslationServiceImpl implements TranslationService
{

   @Override
   public String translate(String key)
   {
      return translate("sgr_translation", key);
   }

   @Override
   public String translate(String resourceFileName, String key)
   {
      ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication()
            .getResourceBundle(FacesContext.getCurrentInstance(), resourceFileName);
      return resourceBundle.getString(key);
   }

}
