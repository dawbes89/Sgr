package sgr.app.frontend.converters;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.comment.CommentType;
import sgr.app.api.translation.TranslationService;

/**
 * Converts {@link Enum} to translated value.
 *
 * @author dawbes89
 */
public class EnumTranslationConverter extends AbstractConverter<Enum<?>>
{

   // REVIEW to powinno raczej trafiæ do AbstractConverter jako protected
   // bo mo¿e trzeba bêdzie czasem w innym konwerterze coœ przet³umaczyæ, tak
   // jest zrobione w walidatorze i o to mi chodzi³o
   @Autowired
   private TranslationService translationService;

   @Override
   protected CommentType convertToObject(String value)
   {
      return CommentType.valueOf(value);
   }

   @Override
   protected String convertToString(Object object)
   {
      final CommentType commentType = (CommentType) object;
      String translate = translationService.translate(commentType.getLabel());
      return translate;
   }

}
