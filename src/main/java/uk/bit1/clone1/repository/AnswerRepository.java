package uk.bit1.clone1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.bit1.clone1.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, String>{
	
	Answer findBySurveyIdAndClientId(String surveyId, String clientId);

}
