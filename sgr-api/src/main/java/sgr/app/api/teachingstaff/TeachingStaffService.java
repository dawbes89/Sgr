package sgr.app.api.teachingstaff;

import java.util.List;

/**
 * @author dawbes
 */
public interface TeachingStaffService
{

   List<TeachingStaff> search();

   TeachingStaff get(Long id);

   void create(TeachingStaff teachingStuff);

   void remove(Long id);

   void update(TeachingStaff teachingStuff);

}
