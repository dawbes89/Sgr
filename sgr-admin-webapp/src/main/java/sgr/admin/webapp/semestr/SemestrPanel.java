package sgr.admin.webapp.semestr;

import org.springframework.beans.factory.annotation.Autowired;
import sgr.app.api.exceptions.CreateException;
import sgr.app.api.semestr.Semestr;
import sgr.app.api.semestr.SemestrQuery;
import sgr.app.api.semestr.SemestrService;
import sgr.app.frontend.panels.AbstractPanel;
import sgr.app.frontend.panels.EditablePanel;

/**
 * @author leonzio
 */
public class SemestrPanel extends AbstractPanel<Semestr> implements EditablePanel<Semestr>
{

	private static final long serialVersionUID = -2275101170715576396L;

	@Autowired
	private SemestrService semestrService;

	@Override
	public void init()
	{
		entity = new Semestr();
	}

	@Override
	public void onLoad()
	{
		entities = semestrService.search(SemestrQuery.all().build());
	}

	@Override
	public void create()
	{
		try
		{
			semestrService.create(entity);
			requestContextExecute(HIDE_ADD_DIALOG_ACTION);
			onLoad();
		} catch (CreateException e)
		{
			showValidationMessage(PROPERTY_ADD_FORM, e.getMessage(), e.getSeverity());
		} finally
		{
			entity = new Semestr();
		}
	}

	@Override
	public void update(Semestr object)
	{
		// nothing
	}

	@Override
	public void remove(Long id)
	{
		// nothing
	}

}
