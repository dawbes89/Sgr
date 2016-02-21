package sgr.app.core.presence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.lesson.Lesson;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceQuery;
import sgr.app.api.presence.PresenceService;
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
         criteria.createAlias(Presence.PROPERTY_LESSON, "l");
         criteria.add(Restrictions.eq("l.schoolSubject", query.getSchoolSubject()));
      }
      return criteria;
   }


}
