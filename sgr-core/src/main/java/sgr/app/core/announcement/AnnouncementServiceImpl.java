package sgr.app.core.announcement;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import sgr.app.api.announcement.Announcement;
import sgr.app.api.announcement.AnnouncementService;
import sgr.app.core.DaoSupport;

import java.util.Date;
import java.util.List;

/**
 * @author dawbes89
 */
class AnnouncementServiceImpl extends DaoSupport implements AnnouncementService
{

	@Override
	public List<Announcement> search()
	{
		Criteria crit = createCriteria(Announcement.class);
		crit.addOrder(Order.desc(Announcement.PROPERTY_DATE));
		return search(crit);
	}

	@Override
	public void create(Announcement announcement)
	{
		announcement.setDate(new Date());
		createEntity(announcement);
	}

	@Override
	public void remove(Long id)
	{
		removeEntity(Announcement.class, id);
	}

	@Override
	public void update(Announcement announcement)
	{
		updateEntity(announcement);
	}

}
