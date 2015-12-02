package sgr.app.api.classgroup;

import java.util.List;

/**
 * @author leonzio
 */
public interface ClassGroupService
{

   List<ClassGroup> search();

   void create(ClassGroup classGroup);

   void remove(Long id);

   List<String> getYears();

   ClassGroup getClass(Integer id, String code);
}
