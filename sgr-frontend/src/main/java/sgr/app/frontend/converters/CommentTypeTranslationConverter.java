package sgr.app.frontend.converters;

import javax.faces.component.UIComponent;

import sgr.app.api.comment.CommentType;

/**
 * Converts {@link CommentType} enum to translated value.
 *
 * @author dawbes89
 */
public class CommentTypeTranslationConverter extends AbstractTranslationConverter<CommentType>
{

   @Override
   protected CommentType convertToObject(String value)
   {
      return CommentType.valueOf(value);
   }

   @Override
   protected String convertToString(Object object, UIComponent component)
   {
      final CommentType commentType = (CommentType) object;
      final String translate = translationService.translate(commentType.getLabel());
      return translate;
   }

}
