package sgr.api.teachingStuff;

import java.util.List;

public interface TeachingStuffService
{

   public List<TeachingStuff> getTeachingStuffList();

   public void addTeacher(TeachingStuff teachingStuff);

   public void deleteTeacher(TeachingStuff teachingStuff);

   public void updateTeacher(TeachingStuff teachingStuff);
}
