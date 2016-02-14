package sgr.app.api.student;

import java.util.List;

/**
 * @author leonzio
 */
public interface StudentService
{

   List<Student> search(StudentQuery query);

   Student get(Long id);

   void create(Student student);

   void remove(Long id);

   void update(Student student);

}
