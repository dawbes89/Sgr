package sgr.app.api.student;

import java.util.List;

import sgr.app.api.exceptions.RemoveException;

/**
 * @author leonzio
 */
public interface StudentService
{

   List<Student> search(StudentQuery query);

   Student get(Long id);

   void create(Student student);

   void remove(Long id) throws RemoveException;

   void update(Student student);

}
