package sgr.app.api.classgroup;

import java.util.List;

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

   ClassGroup getClass(Integer groupNumber, String groupName);

}
