package sgr.app.core.util;

/**
 * Created by leonzio on 2016-12-13.
 */
public abstract class AbstractAssemblerFactory<S, T> implements Assembler<S, T>
{
	private final Assembler<S, T> instance = createInstance();

	protected abstract Assembler<S, T> createInstance();

	@Override
	public T assemble(S source)
	{
		return instance.assemble(source);
	}

	public static class MapperBuilder<S, T>
	{
		private final Class<S> sourceClass;
		private final Class<T> targetClass;

		private MapperBuilder(Class<S> sourceClass, Class<T> targetClass)
		{
			this.sourceClass = sourceClass;
			this.targetClass = targetClass;
		}

		public static <S, T> MapperBuilder<S, T> builder(Class<S> sourceClass, Class<T> targetClass)
		{
			return new MapperBuilder(sourceClass, targetClass);
		}

		public <Y> MapperBuilder from(Getter<S, Y> getter)
		{
			return this;
		}

		public <Y> MapperBuilder to(Setter<T, Y> setter)
		{

			return this;
		}

		public Assembler<S, T> build()
		{
			return null;
		}
	}

	@FunctionalInterface
	public interface Getter<S, Y>
	{
		Y get(S source);
	}

	@FunctionalInterface
	public interface Setter<T, Y>
	{
		void set(T target, Y value);
	}
}
