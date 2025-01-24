package org.example.expert.domain.Log;

import org.example.expert.domain.common.entity.Timestamped;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name = "Logs")
@RequiredArgsConstructor
public class Log extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long LogId;

	@Enumerated(EnumType.STRING)
	private LogStatus status;

	private String action;
	private String description;

	private Log(LogStatus status, String action, String description) {
		this.status = status;
		this.action = action;
		this.description = description;
	}

	public static Log create(LogStatus status, String action, String description) {
		return new Log(status, action, description);
	}

	public enum LogStatus {
		SUCCESS, FAIL
	}
}

