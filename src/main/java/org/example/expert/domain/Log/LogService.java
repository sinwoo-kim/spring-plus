package org.example.expert.domain.Log;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {

	private final LogRepository logRepository;

	@Transactional(propagation  = Propagation.REQUIRES_NEW)
	public void saveLog(Log.LogStatus status, String action, String description) {
		Log newLog = Log.create(status, action, description);
		logRepository.save(newLog);
	}
}
