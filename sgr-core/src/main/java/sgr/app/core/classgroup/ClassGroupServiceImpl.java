package sgr.app.core.classgroup;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.exceptions.CreateException;
import sgr.app.api.exceptions.RemoveException;
import sgr.app.core.util.SgrDaoSupport;

import javax.faces.application.FacesMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
class ClassGroupServiceImpl extends SgrDaoSupport implements ClassGroupService
{

	@Override
	public List<ClassGroup> search(ClassGroupQuery query)
	{
		final Criteria criteria = createCriteria(query);
		criteria.addOrder(Order.asc(ClassGroup.PROPERTY_GROUP_NUMBER)).addOrder(
				Order.asc(ClassGroup.PROPERTY_GROUP_NAME)).addOrder(Order.asc(ClassGroup.PROPERTY_YEARBOOK));
		return search(criteria);
	}

	@Override
	public void create(ClassGroup classGroup) throws CreateException
	{
		final Optional<ClassGroup> optionalClass = find(ClassGroupQuery.all().withGroupName(
				classGroup.getGroupName()).withGroupNumber(classGroup.getGroupNumber()).build());
		if (optionalClass.isPresent())
		{
			throw new CreateException("exception_classGroup_exists", FacesMessage.SEVERITY_ERROR);
		}
		classGroup.setYearbook(currentSchoolYear());
		create(classGroup);
	}

	@Override
	public void remove(Long id) throws RemoveException
	{
		final Criteria criteria = findIndelibleClasses(id);
		final List<Object> indelibleClasses = search(criteria);
		if (!indelibleClasses.isEmpty())
		{
			throw new RemoveException("exception_remove_canNotDelete", FacesMessage.SEVERITY_ERROR);
		}
		remove(ClassGroup.class, id);
	}

	@Override
	public Optional<ClassGroup> find(ClassGroupQuery query)
	{
		final Criteria criteria = createCriteria(query);
		return find(criteria);
	}

	private Criteria findIndelibleClasses(Long classGroupId)
	{
		final Criteria criteria = createCriteria(ClassGroup.class);
		criteria.add(Restrictions.eq(ClassGroup.PROPERTY_ID, classGroupId));
		criteria.add(Restrictions.sqlRestriction(
				"this_.id IN (SELECT class_group_id FROM lesson WHERE class_group_id = " + classGroupId + ") OR this_" + ".id IN " + "(SELECT class_group_id FROM student WHERE class_group_id = " + classGroupId + ")" + " OR this_.id IN (select preceptor_class_id FROM teaching_staff WHERE preceptor_class_id" + " = " + classGroupId + ")"));
		return criteria;
	}

	private Criteria createCriteria(ClassGroupQuery query)
	{
		final Criteria criteria = createCriteria(ClassGroup.class);
		if (query.hasClassId())
		{
			criteria.add(Restrictions.sqlRestriction(
					"this_.id NOT IN (SELECT preceptor_class_id FROM teaching_staff WHERE preceptor_class_id IS NOT " + "NULL AND preceptor_class_id != " + query.getClassId() + ")"));
		}
		if (query.isAvailableForTeachers())
		{
			criteria.add(Restrictions.sqlRestriction(
					"this_.id NOT IN (SELECT preceptor_class_id FROM teaching_staff WHERE preceptor_class_id IS NOT " + "NULL)"));
		}
		if (query.hasGroupNumber())
		{
			criteria.add(Restrictions.eq(ClassGroup.PROPERTY_GROUP_NUMBER, query.getGroupNumber()));
		}
		if (query.hasGroupName())
		{
			criteria.add(Restrictions.eq(ClassGroup.PROPERTY_GROUP_NAME, query.getGroupName()));
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
