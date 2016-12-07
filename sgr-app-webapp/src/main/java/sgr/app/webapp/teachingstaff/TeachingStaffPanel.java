package sgr.app.webapp.teachingstaff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sgr.app.api.teachingstaff.TeachingStaff;
import sgr.app.api.teachingstaff.TeachingStaffService;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * Panel for displaying teaching staff personel.
 *
 * @author dawbes89
 */
@Controller
public class TeachingStaffPanel extends AbstractPanel<TeachingStaff>
{

	private static final long serialVersionUID = 5475535216504328321L;

	@Autowired
	private TeachingStaffService teachingStaffService;

	@Override
	public void init()
	{
		entity = new TeachingStaff();
	}

	@Override
	public void onLoad()
	{
		entities = teachingStaffService.search();
	}

}
