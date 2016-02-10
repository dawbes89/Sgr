package sgr.app.core.classgroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class ClassGroupServiceImpl extends DaoSupport implements ClassGroupService
{

   private final static Long START_YEAR = 2000L;
   private final static List<String> YEARS = new ArrayList<String>();

   static
   {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      for (int i = START_YEAR.intValue(); i <= currentYear; i++)
      {
         YEARS.add(String.valueOf(i));
      }
   }

   @Override
   public List<ClassGroup> search(ClassGroupQuery query)
   {
      Criteria criteria = createCriteriaFromQuery(query);
      return search(criteria);
   }

   @Override
   public void create(ClassGroup classGroup)
   {
      createEntity(classGroup);
   }

   @Override
   public void remove(Long id)
   {
      removeEntity(getEntity(ClassGroup.class, id));
   }

   @Override
   public List<String> getYears()
   {
      return YEARS;
   }

   @Override
   public ClassGroup getClass(Integer groupNumber, String groupName)
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      criteria.add(Restrictions.eq("groupNumber", groupNumber));
      criteria.add(Restrictions.eq("groupName", groupName));
      List<ClassGroup> result = search(criteria);
      if (result.size() > 0)
      {
         return result.get(0);
      }
      return null;
   }

   private Criteria createCriteriaFromQuery(ClassGroupQuery query)
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      criteria.addOrder(Order.asc("groupNumber")).addOrder(Order.asc("groupName"))
            .addOrder(Order.asc("year"));
      if (query.hasClassId())
      {
         criteria
               .add(Restrictions
                     .sqlRestriction("this_.id NOT IN (SELECT preceptor_class_id FROM teaching_stuff WHERE preceptor_class_id IS NOT NULL AND preceptor_class_id != "
                           + query.getClassId() + ")"));
      }
      if (query.isAvailableForTeachers())
      {
         criteria
               .add(Restrictions
                     .sqlRestriction("this_.id NOT IN (SELECT preceptor_class_id FROM teaching_stuff WHERE preceptor_class_id IS NOT NULL)"));
      }
      return criteria;
   }
}
