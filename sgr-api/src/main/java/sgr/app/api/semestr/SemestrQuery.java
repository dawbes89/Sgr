package sgr.app.api.semestr;

import sgr.app.api.QueryBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * @author leonzio
 */
public class SemestrQuery implements Serializable
{

	private static final long serialVersionUID = -410409303192253879L;

	private boolean currentSchoolYear = false;

	private Optional<Integer> semestrNumber = Optional.empty();

	private Optional<Date> fromDate = Optional.empty();

	private Optional<Date> toDate = Optional.empty();

	public static Builder all()
	{
		return new Builder();
	}

	public boolean isCurrentSchoolYear()
	{
		return currentSchoolYear;
	}

	public void setCurrentSchoolYear(boolean currentSchoolYear)
	{
		this.currentSchoolYear = currentSchoolYear;
	}

	public boolean hasSemestrNumber()
	{
		return semestrNumber.isPresent();
	}

	public Integer getSemestrNumber()
	{
		return semestrNumber.get();
	}

	public void setSemestrNumber(Integer semestrNumber)
	{
		this.semestrNumber = Optional.of(semestrNumber);
	}

	public boolean hasFromDate()
	{
		return fromDate.isPresent();
	}

	public Date getFromDate()
	{
		return fromDate.get();
	}

	public void setFromDate(Date fromDate)
	{
		this.fromDate = Optional.of(fromDate);
	}

	public boolean hasToDate()
	{
		return toDate.isPresent();
	}

	public Date getToDate()
	{
		return toDate.get();
	}

	public void setToDate(Date toDate)
	{
		this.toDate = Optional.of(toDate);
	}

	public static final class Builder extends QueryBuilder<SemestrQuery>
	{

		protected Builder()
		{
			super(new SemestrQuery());
		}

		public Builder withCurrentSchoolYear(boolean currentSchoolYear)
		{
			query.setCurrentSchoolYear(currentSchoolYear);
			return this;
		}

		public Builder withSemestrNumber(Integer number)
		{
			query.setSemestrNumber(number);
			return this;
		}

		public Builder withFromDate(Date date)
		{
			query.setFromDate(date);
			return this;
		}

		public Builder withToDate(Date date)
		{
			query.setToDate(date);
			return this;
		}

	}

}
