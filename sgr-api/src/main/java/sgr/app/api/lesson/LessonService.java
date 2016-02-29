package sgr.app.api.lesson;

import java.util.List;
import java.util.Optional;

import sgr.app.api.presence.Presence;

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
