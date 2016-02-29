package sgr.app.frontend.validators;

import java.util.Date;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import org.springframework.beans.factory.annotation.Autowired;

import sgr.app.api.lesson.Lesson;
import sgr.app.api.lesson.LessonQuery;
import sgr.app.api.lesson.LessonService;

/**
 * Validator for lessonNumber. Not allows to use twice and more times the same
 * lessonNumber in the same day.
 *
 * @author dawbes89
 */
public class LessonNumberValidator extends AbstractValidator<Integer>
{

   @Autowired
   private LessonService lessonService;

   public LessonNumberValidator()
   {
      super(FacesMessage.SEVERITY_ERROR, "validation_lessonNumberExists");
   }

   @Override
   protected boolean isValidValue(Integer value, final UIComponent component)
   {
      final Long findClassGroupId = (Long) component.getAttributes().get("classGroupId");
      LessonQuery query = LessonQuery.all().withLessonNumber(value)
            .withDate(new Date()).build();
      if(findClassGroupId != null)
      {
          query.setClassGroupId(findClassGroupId);
      }
      final Optional<Lesson> lesson = lessonService.find(query);
      return !lesson.isPresent();
   }

}
