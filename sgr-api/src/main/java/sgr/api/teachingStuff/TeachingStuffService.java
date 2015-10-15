package sgr.api.teachingStuff;

import java.util.List;

/**
 * @author dawbes
 */
public interface TeachingStuffService
{

   public List<TeachingStuff> search();

   public TeachingStuff get(Long id);

   public void create(TeachingStuff teachingStuff);

   public void remove(Long id);

   public void update(TeachingStuff teachingStuff);

}
