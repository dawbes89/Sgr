package sgr.app.api.admin;

import java.util.List;

/**
 * @author dawbes
 */
public interface AdminService
{
   List<Admin> search();

   Admin get(Long id);

   void create(Admin admin);

   void remove(Long id);

   void update(Admin admin);

}