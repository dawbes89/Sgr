package sgr.api.teachingStuff;

import java.io.Serializable;
import java.util.List;

public interface TeachingStuffService extends Serializable
{

   public List<TeachingStuff> search();

   public TeachingStuff get(Long id);

   public void create(TeachingStuff teachingStuff);

   public void remove(Long id);

   public void update(TeachingStuff teachingStuff);

}
