package sgr.app.core.semestr;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrService;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class SemestrServiceImpl extends DaoSupport implements SemestrService
{

   @Override
   public List<Semestr> search()
   {
      final Criteria criteria = createCriteria(Semestr.class);
      criteria.addOrder(Order.desc(Semestr.PROPERTY_SCHOOL_YEAR));
      criteria.addOrder(Order.desc(Semestr.PROPERTY_SEMESTR_NUMBER));
      return search(criteria);
   }

   @Override
   public void create(Semestr semestr)
   {
      semestr.setSchoolYear(currentSchoolYear());
      createEntity(semestr);
   }

   @Override
   public Optional<Semestr> find()
   {
      return null;
   }

   private String currentSchoolYear()
   {
      final Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());
      final int currentYear = cal.get(Calendar.YEAR);
      final String schoolYear = String.format("%4d/%4d", currentYear, currentYear + 1);
      return schoolYear;
   }

}
