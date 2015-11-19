package sgr.app.api.classgroup;

import java.util.List;

/**
 * @author leonzio
 */
public interface ClassGroupService
{

   List<ClassGroup> search();

   public void create(ClassGroup classGroup);

   public void remove(Long id);

}
