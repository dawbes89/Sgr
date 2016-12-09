package sgr.app.frontend.converters;

import org.springframework.beans.factory.annotation.Autowired;
import sgr.app.api.assessment.Assessment;
import sgr.app.api.assessment.AssessmentQuery;
import sgr.app.api.assessment.AssessmentService;
import sgr.app.api.teachingstaff.SchoolSubject;
import sgr.app.api.translation.TranslationService;

import javax.faces.component.UIComponent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dawbes89
 */
// TODO extends TranslationConverter
public class AssessmentListConverter extends AbstractConverter<Long>
{
	private static final String SCHOOL_SUBJECT_ATTRIBUTE = "schoolSubject";

	@Autowired
	protected TranslationService translationService;

	@Autowired
	private AssessmentService assessmentService;

	@Override
	protected Class<Long> getConvertedValueClass()
	{
		return Long.class;
	}

	@Override
	protected Long convertToObject(String value)
	{
		return Long.valueOf(value);
	}

	@Override
	protected String convertToString(Long object, UIComponent component)
	{
		final SchoolSubject findSchoolSubject = (SchoolSubject) component.getAttributes().get(SCHOOL_SUBJECT_ATTRIBUTE);
		AssessmentQuery query = AssessmentQuery.all().withStudentId(object).build();
		if (findSchoolSubject != null)
		{
			query.setSchoolSubject(findSchoolSubject);
		}
		List<Assessment> assessment = assessmentService.search(query);
		return assessment.stream().map(this::convertAssessment).collect(Collectors.joining(", "));
	}

	private String convertAssessment(Assessment assessment)
	{
		AssessmentConverter converter = new AssessmentConverter();
		String convertToString = converter.convertToString(assessment.getAssessment(), null);
		final String translate = translationService.translate(assessment.getAssessmentType().getLabel());

		return String.format("%s %s", convertToString, String.valueOf(translate).charAt(0));
	}

}
