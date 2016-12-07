package sgr.app.api.admin;

import java.util.List;

/**
 * @author dawbes89
 */
public interface AdminService
{

	List<Admin> search(AdminQuery query);

	Admin get(Long id);

	void create(Admin admin);

	void remove(Long id);

	void update(Admin admin);

}