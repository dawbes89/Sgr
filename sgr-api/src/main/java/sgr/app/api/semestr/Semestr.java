package sgr.app.api.semestr;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author leonzio
 */
@Entity
@Table(name = "semestr", uniqueConstraints = {@UniqueConstraint(columnNames = {"semestr_number", "school_year"},
		name = "semestr_number_in_school_year_uk")})
public class Semestr implements Serializable
{

	public static final String PROPERTY_SCHOOL_YEAR = "schoolYear";

	public static final String PROPERTY_SEMESTR_NUMBER = "semestrNumber";

	public static final String PROPERTY_FROM = "from";

	public static final String PROPERTY_TO = "to";

	private static final long serialVersionUID = -988347663214142873L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "semestr_number", nullable = false)
	private Integer semestrNumber;

	@Column(name = "school_year", nullable = false, updatable = false, length = 9)
	private String schoolYear;

	@Column(name = "date_from", nullable = false)
	@Type(type = "date")
	private Date from;

	@Column(name = "date_to", nullable = false)
	@Type(type = "date")
	private Date to;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Integer getSemestrNumber()
	{
		return semestrNumber;
	}

	public void setSemestrNumber(Integer semestrNumber)
	{
		this.semestrNumber = semestrNumber;
	}

	public String getSchoolYear()
	{
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear)
	{
		this.schoolYear = schoolYear;
	}

	public Date getFrom()
	{
		return from;
	}

	public void setFrom(Date from)
	{
		this.from = from;
	}

	public Date getTo()
	{
		return to;
	}

	public void setTo(Date to)
	{
		this.to = to;
	}

}
