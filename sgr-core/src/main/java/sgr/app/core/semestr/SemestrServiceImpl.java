package sgr.app.core.semestr;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import sgr.app.api.exceptions.CreateException;
import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrQuery;
import sgr.app.api.semestr.SemestrService;
import sgr.app.core.DaoSupport;

import javax.faces.application.FacesMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
class SemestrServiceImpl extends DaoSupport implements SemestrService
{

	@Override
	public List<Semestr> search(SemestrQuery query)
	{
		final Criteria criteria = createCriteria(query);
		criteria.addOrder(Order.desc(Semestr.PROPERTY_SCHOOL_YEAR));
		criteria.addOrder(Order.desc(Semestr.PROPERTY_SEMESTR_NUMBER));
		return search(criteria);
	}

	@Override
	public void create(Semestr semestr) throws CreateException
	{
		Criteria criteria = createCriteria(Semestr.class);
		criteria.add(Restrictions.eq(Semestr.PROPERTY_SCHOOL_YEAR, currentSchoolYear()));
		criteria.add(Restrictions.ge(Semestr.PROPERTY_TO, semestr.getFrom()));
		List<Semestr> search = search(criteria);
		if (!search.isEmpty())
		{
			throw new CreateException("exception_create_semestr_dateFromBeforeDateTo", FacesMessage.SEVERITY_ERROR);
		}
		criteria = createCriteria(Semestr.class);
		criteria.add(Restrictions.eq(Semestr.PROPERTY_SCHOOL_YEAR, currentSchoolYear()));
		criteria.add(Restrictions.eq(Semestr.PROPERTY_SEMESTR_NUMBER, semestr.getSemestrNumber()));
		search = search(criteria);
		if (!search.isEmpty())
		{
			throw new CreateException("exception_create_semestr_withGivenNumberExsists", FacesMessage.SEVERITY_ERROR);
		}
		semestr.setSchoolYear(currentSchoolYear());
		createEntity(semestr);
	}

	@Override
	public Optional<Semestr> findCurrentSemestr()
	{
		final Date currentDay = new Date();

		final Criteria criteria = createCriteria(Semestr.class);
		criteria.add(Restrictions.eq(Semestr.PROPERTY_SCHOOL_YEAR, currentSchoolYear()));
		criteria.add(Restrictions.ge(Semestr.PROPERTY_FROM, currentDay));
		criteria.add(Restrictions.le(Semestr.PROPERTY_TO, currentDay));
		return find(criteria);
	}

	private Criteria createCriteria(SemestrQuery query)
	{
		final Criteria criteria = createCriteria(Semestr.class);
		if (query.hasSemestrNumber())
		{
			criteria.add(Restrictions.eq(Semestr.PROPERTY_SEMESTR_NUMBER, query.getSemestrNumber()));
		}
		if (query.isCurrentSchoolYear())
		{
			criteria.add(Restrictions.eq(Semestr.PROPERTY_SCHOOL_YEAR, currentSchoolYear()));
			return criteria;
		}
		if (query.hasFromDate())
		{
			criteria.add(Restrictions.ge(Semestr.PROPERTY_FROM, query.getFromDate()));
		}
		if (query.hasToDate())
		{
			criteria.add(Restrictions.le(Semestr.PROPERTY_TO, query.getToDate()));
		}
		return criteria;
	}

	private String currentSchoolYear()
	{
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		final int currentYear = cal.get(Calendar.YEAR);
		return String.format("%4d/%4d", currentYear, currentYear + 1);
	}

}
