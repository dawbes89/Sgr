package sgr.admin.webapp.admin;

import org.primefaces.component.inputtext.InputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sgr.app.api.admin.Admin;
import sgr.app.api.admin.AdminQuery;
import sgr.app.api.admin.AdminService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.frontend.RandomPasswordGenerator;
import sgr.app.frontend.helpers.BeanHelper;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * Panel for handling admin users.
 *
 * @author dawbes89
 */
@Controller
public class AdminPanel extends AbstractPanel<Admin> implements EditablePanel<Admin>
{

	private static final long serialVersionUID = -2599849233906847054L;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private AdminService adminService;

	@Override
	public void init()
	{
		entity = new Admin();
	}

	@Override
	public void onLoad()
	{
		init();
		final Admin currentUser = authenticationService.getCurrentUser();
		final AdminQuery query = AdminQuery.all().withExcludedId(currentUser.getId()).build();
		entities = adminService.search(query);
	}

	@Override
	public void create()
	{
		adminService.create(entity);
		entity = new Admin();
		onLoad();
	}

	@Override
	public void update(Admin object)
	{
		adminService.update(object);
		onLoad();
	}

	@Override
	public void remove(Long id)
	{
		adminService.remove(id);
		requestContextExecute(HIDE_REMOVE_DIALOG_ACTION);
		onLoad();
	}

	public void generatePassword()
	{
		final InputText passwordField = BeanHelper.getComponent(PROPERTY_ADD_FORM, "password");
		final String password = RandomPasswordGenerator.generate();
		passwordField.setSubmittedValue(password);
	}

}
