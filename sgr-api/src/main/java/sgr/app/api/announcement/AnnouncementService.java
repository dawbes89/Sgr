package sgr.app.api.announcement;

import java.util.List;

/**
 * @author dawbes
 */
public interface AnnouncementService
{

	List<Announcement> search();

	void create(Announcement announcement);

	void update(Announcement announcement);

	void remove(Long id);

}