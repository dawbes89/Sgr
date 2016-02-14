package sgr.app.api.teachingStuff;

import java.util.List;

/**
 * @author dawbes
 */
public interface TeachingStuffService
{

   List<TeachingStuff> search();

   TeachingStuff get(Long id);

   void create(TeachingStuff teachingStuff);

   void remove(Long id);

   void update(TeachingStuff teachingStuff);

}
