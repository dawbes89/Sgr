package sgr.app.api.semestr;

import sgr.app.api.exceptions.CreateException;

import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
public interface SemestrService
{

	List<Semestr> search(SemestrQuery query);

	void create(Semestr semestr) throws CreateException;

	/**
	 * Finds currently being semestr.
	 *
	 * @return semestr
	 */
	Optional<Semestr> findCurrentSemestr();

}
