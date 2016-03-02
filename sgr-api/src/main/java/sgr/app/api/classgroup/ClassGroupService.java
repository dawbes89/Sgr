package sgr.app.api.classgroup;

import java.util.List;
import java.util.Optional;

import sgr.app.api.exceptions.CreateException;
import sgr.app.api.exceptions.RemoveException;

/**
 * @author leonzio
 */
public interface ClassGroupService
{

   List<ClassGroup> search(ClassGroupQuery query);

   void create(ClassGroup classGroup) throws CreateException;

   void remove(Long id) throws RemoveException;

   Optional<ClassGroup> find(ClassGroupQuery query);

}
