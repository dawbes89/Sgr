package sgr.app.core.student;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;
import sgr.app.core.account.Account;
import sgr.app.core.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.exceptions.RemoveException;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.core.util.SgrDaoSupport;

import javax.faces.application.FacesMessage;
import java.util.List;

/**
 * @author leonzio
 */
class StudentServiceImpl extends SgrDaoSupport implements StudentService
{
	private final static String PROPERTY_CLASS_GROUP_ID = "classGroup.id";

	private AccountService accountService;

	@Override
	public List<Student> search(StudentQuery query)
	{
		final Criteria criteria = createCriteriaFromQuery(query);
		criteria.addOrder(Order.asc(Student.PROPERTY_CLASS_GROUP));
		return search(criteria);
	}

	@Override
	public Student get(Long id)
	{
		return get(Student.class, id);
	}

	@Override
	public void create(Student student)
	{
		final Account account = student.getAccount();
		account.setType(AccountType.STUDENT);
		student.setAccount(accountService.create(account));
		create(student);
	}

	@Override
	public void remove(Long id) throws RemoveException
	{
		final Criteria criteria = findIndelibleStudents(id);
		final List<Object> indelibleStudents = search(criteria);
		if (!indelibleStudents.isEmpty())
		{
			throw new RemoveException("exception_remove_canNotDelete", FacesMessage.SEVERITY_ERROR);
		}
		remove(Student.class, id);
	}

	@Override
	public void update(Student student)
	{
		update(student);
	}

	private Criteria findIndelibleStudents(Long studentId)
	{

		Criteria criteria = createCriteria(Student.class);
		criteria.add(Restrictions.eq(Student.PROPERTY_ID, studentId));
		criteria.add(Restrictions.sqlRestriction(
				"this_.id IN (SELECT student_id FROM assessment WHERE student_id = " + studentId + ") OR this_.id IN " + "(SELECT student_id FROM comment WHERE student_id = " + studentId + ")" + " OR this_.id IN " + "(select student_id FROM notification WHERE student_id = " + studentId + ")" + " OR this_.id" + " " + "IN (select student_id FROM presence WHERE student_id = " + studentId + ")"));

		return criteria;
	}

	private Criteria createCriteriaFromQuery(StudentQuery query)
	{
		Criteria criteria = createCriteria(Student.class);

		if (query.hasClassGroupId())
		{
			criteria.add(Restrictions.eq(PROPERTY_CLASS_GROUP_ID, query.getClassGroupId()));
		}
		return criteria;
	}

	@Required
	public void setAccountService(AccountService accountService)
	{
		this.accountService = accountService;
	}
}
