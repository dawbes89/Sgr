package sgr.app.api.classgroup;

import sgr.app.api.exceptions.CreateException;
import sgr.app.api.exceptions.RemoveException;

import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
public interface ClassGroupService
{

	List<ClassGroup> search(ClassGroupQuery query);

	void create(ClassGroup classGroup) throws CreateException;

	void remove(Long id) throws RemoveException;

	Optional<ClassGroup> find(ClassGroupQuery query);

}
