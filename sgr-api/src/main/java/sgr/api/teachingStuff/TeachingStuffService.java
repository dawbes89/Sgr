package sgr.api.teachingStuff;

import java.util.List;

public interface TeachingStuffService
{

   public List<TeachingStuff> search();

   public TeachingStuff get(Long id);

}
