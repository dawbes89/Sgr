package sgr.app.core.student;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountService;
import sgr.app.api.account.AccountType;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class StudentServiceImpl extends DaoSupport implements StudentService
{

   private static String PROPERTY_CLASS_GROUP_ID = "classGroup.id";

   private AccountService accountService;

   @Override
   public List<Student> search(StudentQuery query)
   {
      Criteria criteria = createCriteriaFromQuery(query);
      return search(criteria);
   }

   @Override
   public Student get(Long id)
   {
      return getEntity(Student.class, id);
   }

   @Override
   public void create(Student student)
   {
      final Account account = student.getAccount();
      account.setType(AccountType.STUDENT);
      student.setAccount(accountService.createAccount(account));
      createEntity(student);
   }

   @Override
   public void remove(Long id)
   {
      Student student = get(id);
      removeEntity(student);
   }

   @Override
   public void update(Student student)
   {
      updateEntity(student);
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
