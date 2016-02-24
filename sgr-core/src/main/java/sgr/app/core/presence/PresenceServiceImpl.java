package sgr.app.core.presence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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
      final Criteria criteria = createCriteria(query);
      criteria.addOrder(Order.desc("id"));
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
         final Criteria personCriteria = criteria.createCriteria(PROPERTY_PERSON);
         final MatchMode matchMode = MatchMode.ANYWHERE;
         final String matchString = matchMode.toMatchString(query.getStudentFullName());
         final String rawQuery = "concat(first_name, ' ', last_name) ilike ?";
         personCriteria
               .add(Restrictions.sqlRestriction(rawQuery, matchString, StandardBasicTypes.STRING));
      }
      if (query.hasDate())
      {
         criteria.add(Restrictions.eq("l.date", query.getDate()));
      }
      if (query.hasStatus())
      {
         criteria.add(Restrictions.eq(Presence.PROPERTY_STATUS, query.getStatus()));
      }
      return criteria;
   }

}
