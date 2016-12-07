package sgr.app.api.presence;

import java.util.List;

/**
 * @author dawbes
 */
public interface PresenceService
{
	void create(Presence presence);

	List<Presence> search(PresenceQuery query);

	void update(Presence presence);
}
