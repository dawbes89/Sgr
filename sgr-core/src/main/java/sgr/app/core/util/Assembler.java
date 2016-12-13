package sgr.app.core.util;

/**
 * Created by leonzio on 2016-12-09.
 */
@FunctionalInterface
public interface Assembler<S, T>
{
	/**
	 * Assembles <code>source</code> object to <code>target</code> object.
	 *
	 * @param source
	 * 		to assemble
	 * @return assembled
	 */
	T assemble(S source);
}
