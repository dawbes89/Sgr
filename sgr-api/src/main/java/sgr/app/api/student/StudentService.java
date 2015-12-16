package sgr.app.api.student;

import java.util.List;

/**
 * @author leonzio
 */
public interface StudentService
{
   public List<Student> search();

   public Student get(Long id);

   public void create(Student student);

   public void remove(Long id);

   public void update(Student student);
}
