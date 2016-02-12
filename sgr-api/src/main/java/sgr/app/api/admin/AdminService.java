package sgr.app.api.admin;

import java.util.List;
/**
 * @author dawbes
 */
public interface AdminService
{
   public List<Admin> search();

   public Admin get(Long id);

   public void create(Admin admin);

   public void remove(Long id);

   public void update(Admin admin);

}
