package sgr.app.api.lesson;

import sgr.app.api.presence.Presence;

import java.util.List;
import java.util.Optional;

/**
 * @author dawbes
 */
public interface LessonService
{
	Lesson create(Lesson lesson, List<Presence> presences);

	Presence createPresence(Presence presence);

	List<Lesson> search(LessonQuery query);

	Optional<Lesson> find(LessonQuery query);
}
