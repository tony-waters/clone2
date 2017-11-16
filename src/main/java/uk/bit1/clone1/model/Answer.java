package uk.bit1.clone1.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( schema = "G00", name = "CUSTOMERSATISFACTIONSURVEYANSWERS")
public class Answer {
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SURVEY_ID")
	private String surveyId;
	
	@Column(name = "CLIENT_ID")
	private String clientId;
	
	@Column(name = "ANSWER_ID", nullable = true)
	private String answerId;
	
	@Column(name = "TIMESTAMP")
	private LocalDateTime timestamp;

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", surveyId=" + surveyId + ", clientId=" + clientId + ", answerId=" + answerId
				+ "]";
	}

}
