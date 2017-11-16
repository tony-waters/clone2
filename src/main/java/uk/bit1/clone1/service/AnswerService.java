package uk.bit1.clone1.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.bit1.clone1.model.Answer;
import uk.bit1.clone1.model.ResponseType;
import uk.bit1.clone1.repository.AnswerRepository;

@Service
@Transactional
public class AnswerService {
	
	private static Logger log = LoggerFactory.getLogger(AnswerService.class);
	
	private final AnswerRepository answerRepository;
	
	@Autowired
	public AnswerService(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	public ResponseType processAnswer(String surveyId, String clientId, String answerId) {
		log.info("Processing answer - surveyId[{}] clientId[{}] answerId[{}]", surveyId, clientId, answerId);
		
		try {
			Answer answer = answerRepository.findBySurveyIdAndClientId(surveyId, clientId);
			if(answer == null) {
				log.debug("No answer in database - surveyId[{}] clientId[{}] answerId[{}]", surveyId, clientId, answerId);
				return ResponseType.PROBLEM;
			}
			if(answer.getAnswerId() != null) {
				log.debug("Duplicate answer - surveyId[{}] clientId[{}] answerId[{}]", surveyId, clientId, answerId);
				return ResponseType.DUPLICATE;
			}
			answer.setAnswerId(answerId);
			answerRepository.save(answer);
			
		} catch(RuntimeException e) {
			log.error("Problem processing answer - surveyId[{}] clientId[{}] answerId[{}]", surveyId, clientId, answerId);
			return ResponseType.PROBLEM;
		}
		log.debug("Sucessfully processed answer - surveyId[{}] clientId[{}] answerId[{}]", surveyId, clientId, answerId);
		return ResponseType.SUCCESS;
	}
}
