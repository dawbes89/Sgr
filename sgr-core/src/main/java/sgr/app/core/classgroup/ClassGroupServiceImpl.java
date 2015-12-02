package sgr.app.core.classgroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.commons.core.DaoSupport;

/**
 * @author leonzio
 */
public class ClassGroupServiceImpl extends DaoSupport implements ClassGroupService
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
   public List<ClassGroup> search()
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      criteria.addOrder(Order.asc("groupNumber"));
      criteria.addOrder(Order.asc("groupName"));
      criteria.addOrder(Order.asc("year"));
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
   public ClassGroup getClass(Integer id, String code)
   {
      Criteria criteria = createCriteria(ClassGroup.class);
      criteria.add(Restrictions.eq("groupNumber", id));
      criteria.add(Restrictions.eq("groupName", code));
      List<ClassGroup> result = search(criteria);
      if (result.size() > 0)
         return result.get(0);
      return null;
   }
}
