package sgr.app.api.teachingstaff;

import java.util.List;

/**
 * @author dawbes89
 */
public interface TeachingStaffService
{

   List<TeachingStaff> search();

   TeachingStaff get(Long id);

   void create(TeachingStaff teachingStaff);

   void remove(Long id);

   void update(TeachingStaff teachingStaff);

}
