package sgr.app.core.student;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.account.AccountType;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentService;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class StudentServiceImpl extends DaoSupport implements StudentService
{

   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

   @Override
   public List<Student> search()
   {
      Criteria criteria = createCriteria(Student.class);
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
      String password = student.getAccount().getPassword();
      student.getAccount().setPassword(PASSWORD_ENCODER.encode(password));
      student.getAccount().setType(AccountType.STUDENT);
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

}
