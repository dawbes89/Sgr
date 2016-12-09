package sgr.app.frontend.converters;

import javax.faces.component.UIComponent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dawbes89
 */
public class AssessmentConverter extends AbstractConverter<Float>
{
	// TODO enum
	private final static Float ONE = 1.0f;
	private final static Float ONE_POINT_SEVENTYFIVE = 1.75f;
	private final static Float TWO = 2.0f;
	private final static Float TWO_POINT_FIVE = 2.5f;
	private final static Float TWO_POINT_SEVENTYFIVE = 2.75f;
	private final static Float THREE = 3.0f;
	private final static Float THREE_POINT_FIVE = 3.5f;
	private final static Float THREE_POINT_SEVENTYFIVE = 3.75f;
	private final static Float FOUR = 4.0f;
	private final static Float FOUR_POINT_FIVE = 4.5f;
	private final static Float FOUR_POINT_SEVENTYFIVE = 4.75f;
	private final static Float FIVE = 5.0f;
	private final static Float FIVE_POINT_FIVE = 5.5f;
	private final static Float SIX = 6.0f;

	private final Map<Float, String> assessmentMap = new HashMap<>();

	public AssessmentConverter()
	{
		assessmentMap.put(ONE, "1");
		assessmentMap.put(TWO, "2");
		assessmentMap.put(ONE_POINT_SEVENTYFIVE, "-2");
		assessmentMap.put(TWO_POINT_FIVE, "+2");
		assessmentMap.put(THREE, "3");
		assessmentMap.put(TWO_POINT_SEVENTYFIVE, "-3");
		assessmentMap.put(THREE_POINT_FIVE, "+3");
		assessmentMap.put(THREE_POINT_SEVENTYFIVE, "-4");
		assessmentMap.put(FOUR, "4");
		assessmentMap.put(FOUR_POINT_FIVE, "+4");
		assessmentMap.put(FIVE, "5");
		assessmentMap.put(FOUR_POINT_SEVENTYFIVE, "-5");
		assessmentMap.put(FIVE_POINT_FIVE, "+5");
		assessmentMap.put(SIX, "6");
	}

	@Override
	protected Class<Float> getConvertedValueClass()
	{
		return Float.class;
	}

	@Override
	protected Float convertToObject(String value)
	{
		return Float.valueOf(value);
	}

	@Override
	protected String convertToString(Float object, UIComponent component)
	{
		return assessmentMap.get(object);
	}

}
