package sgr.app.frontend.converters;

import sgr.app.api.comment.CommentType;

import javax.faces.component.UIComponent;

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
	protected String convertToString(CommentType object, UIComponent component)
	{
		return translationService.translate(object.getLabel());
	}

}
