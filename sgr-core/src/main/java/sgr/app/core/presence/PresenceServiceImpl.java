package sgr.app.core.presence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import sgr.app.api.lesson.Lesson;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceQuery;
import sgr.app.api.presence.PresenceService;
import sgr.app.api.student.Student;
import sgr.app.core.DaoSupport;

/**
 * @author dawbes
 */
class PresenceServiceImpl extends DaoSupport implements PresenceService
{

   private static final String PROPERTY_LESSON_ID = Presence.PROPERTY_LESSON + "."
         + Lesson.PROPERTY_ID;
   private static final String PROPERTY_CLASS_GROUP = Presence.PROPERTY_LESSON + "."
         + Lesson.PROPERTY_CLASS_GROUP;
   private static final String PROPERTY_PERSON = Presence.PROPERTY_STUDENT + "."
         + Student.PROPERTY_PERSON;

   @Override
   public void create(Presence presence)
   {
      createEntity(presence);
   }

   @Override
   public List<Presence> search(PresenceQuery query)
   {
      Criteria criteria = createCriteria(query);
      return search(criteria);
   }

   @Override
   public void update(Presence presence)
   {
      updateEntity(presence);
   }

   private Criteria createCriteria(PresenceQuery query)
   {
      Criteria criteria = createCriteria(Presence.class);
      criteria.createAlias(Presence.PROPERTY_LESSON, "l");
      if (query.hasLessonId())
      {
         criteria.add(Restrictions.eq(PROPERTY_LESSON_ID, query.getLessonId()));
      }
      if (query.hasClassGroupId())
      {
         criteria.createAlias(PROPERTY_CLASS_GROUP, "cg");
         criteria.add(Restrictions.eq("cg.id", query.getClassGroupId()));
      }
      if (query.hasSchoolSubject())
      {
         criteria.add(Restrictions.eq("l.schoolSubject", query.getSchoolSubject()));
      }
      if (query.hasStudentFullName())
      {
         Criteria personCriteria = criteria.createCriteria(PROPERTY_PERSON);
         MatchMode matchMode = MatchMode.ANYWHERE;
         String matchString = matchMode.toMatchString(query.getStudentFullName());
         String format = String.format("concat(first_name, ' ', last_name) %s ?", "like");
         personCriteria.add(Restrictions.sqlRestriction(format, matchString,
               StandardBasicTypes.STRING));
      }
      if (query.hasDate())
      {
         criteria.add(Restrictions.eq("l.date", query.getDate()));
      }
      if(query.hasStatus())
      {
         criteria.add(Restrictions.eq(Presence.PROPERTY_STATUS, query.getStatus()));
      }
      return criteria;
   }

}
