package sgr.app.core.student;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.core.DaoSupport;

/**
 * @author leonzio
 */
class StudentServiceImpl extends DaoSupport implements StudentService
{

   private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

   public static String PROPERTY_CLASS_GROUP_ID = "classGroup.id";

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
      String password = student.getAccount().getPassword();
      student.getAccount().setPassword(PASSWORD_ENCODER.encode(password));
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

      if(query.hasClassGroupId())
      {
         criteria.add(Restrictions.eq(PROPERTY_CLASS_GROUP_ID, query.getClassGroupId()));
      }
         return criteria;
   }

}
