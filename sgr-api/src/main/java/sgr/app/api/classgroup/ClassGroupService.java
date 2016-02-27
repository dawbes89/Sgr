package sgr.app.api.classgroup;

import java.util.List;
import java.util.Optional;

import sgr.app.api.exceptions.ClassGroupException;

/**
 * @author leonzio
 */
public interface ClassGroupService
{

   List<ClassGroup> search(ClassGroupQuery query);

   void create(ClassGroup classGroup) throws ClassGroupException;

   void remove(Long id) throws ClassGroupException;

   List<String> getYears();

   Optional<ClassGroup> getClass(Integer groupNumber, String groupName);

   Optional<ClassGroup> find(ClassGroupQuery query);

}
