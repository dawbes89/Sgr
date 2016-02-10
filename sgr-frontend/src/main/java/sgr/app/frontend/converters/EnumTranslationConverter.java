package sgr.app.frontend.converters;

import sgr.app.api.comment.CommentType;

/**
 * Converts {@link Enum} to translated value.
 *
 * @author dawbes89
 */
public class EnumTranslationConverter extends AbstractConverter<Enum<?>>
{

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
