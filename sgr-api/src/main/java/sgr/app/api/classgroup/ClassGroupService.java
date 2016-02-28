package sgr.app.api.classgroup;

import java.util.List;
import java.util.Optional;

import sgr.app.api.exceptions.ClassGroupException;
import sgr.app.api.exceptions.RemoveException;

/**
 * @author leonzio
 */
public interface ClassGroupService
{

   List<ClassGroup> search(ClassGroupQuery query);

   void create(ClassGroup classGroup) throws ClassGroupException;

   void remove(Long id) throws RemoveException;

   Optional<ClassGroup> find(ClassGroupQuery query);

}
