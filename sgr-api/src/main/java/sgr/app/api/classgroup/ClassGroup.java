package sgr.app.api.classgroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author leonzio
 */
@Entity
@Table(name = "class_group")
public class ClassGroup implements Serializable
{
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_GROUP_NUMBER = "groupNumber";
	public static final String PROPERTY_GROUP_NAME = "groupName";
	public static final String PROPERTY_YEARBOOK = "yearbook";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "group_number", precision = 1)
	private Integer groupNumber;

	@Column(name = "group_name", length = 1, nullable = false, updatable = false)
	private String groupName;

	@Column(name = "yearbook", nullable = false, updatable = false, length = 9)
	private String yearbook;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Integer getGroupNumber()
	{
		return groupNumber;
	}

	public void setGroupNumber(Integer groupNumber)
	{
		this.groupNumber = groupNumber;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public String getYearbook()
	{
		return yearbook;
	}

	public void setYearbook(String yearbook)
	{
		this.yearbook = yearbook;
	}

	public String getClassName()
	{
		return String.format("%d%s", groupNumber, groupName);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !Objects.equals(this.getClass(), obj.getClass()))
		{
			return false;
		}
		final ClassGroup object = (ClassGroup) obj;
		return Objects.equals(object.getId(), id);
	}

}
