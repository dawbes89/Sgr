package sgr.app.frontend.converters;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.translation.TranslationService;

/**
 * Abstract base for translated converters.
 *
 * @author leonzio
 *
 * @param <T>
 *           type of converted value
 */
public abstract class AbstractTranslationConverter<T> extends AbstractConverter<T>
{

   @Autowired
   protected TranslationService translationService;

   public AbstractTranslationConverter()
   {
      super();
   }

}
