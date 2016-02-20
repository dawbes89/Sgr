package sgr.app.core.lesson;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.lesson.Lesson;
import sgr.app.api.lesson.LessonQuery;
import sgr.app.api.lesson.LessonService;
import sgr.app.api.presence.Presence;
import sgr.app.core.DaoSupport;
/**
 * @author dawbes
 */
public class LessonServiceImpl extends DaoSupport implements LessonService
{
   private static final String PROPERTY_CLASS_GROUP_ID = Lesson.PROPERTY_CLASS_GROUP + "." + ClassGroup.PROPERTY_ID;
  @Override
   public Lesson create(Lesson lesson, List<Presence> presences)
   {
      Lesson created = createEntity(lesson);
      presences.stream().peek(presence -> presence.setLesson(created)).forEach(presence -> createPresence(presence));
      return created;
   }

   @Override
   public List<Lesson> search(LessonQuery query)
   {
      Criteria criteria = createAssessmentCriteria(query);
      return search(criteria);
   }

   private Criteria createAssessmentCriteria(LessonQuery query)
   {
      Criteria criteria = createCriteria(Lesson.class);
      if(query.hasSchoolSubject())
      {
         criteria.add(Restrictions.eq(Lesson.PROPERTY_SCHOOL_SUBJECT, query.getSchoolSubject()));
      }
      if(query.hasStudentId())
      {
         criteria.add(Restrictions.eq(Lesson.PROPERTY_STUDENT_ID, query.getStudentId()));
      }
      if(query.hasClassGroupId())
      {
         criteria.add(Restrictions.eq(PROPERTY_CLASS_GROUP_ID, query.getClassGroupId()));
      }
      return criteria;
   }

   @Override
   public Presence createPresence(Presence presence)
   {
      return createEntity(presence);
   }
}
