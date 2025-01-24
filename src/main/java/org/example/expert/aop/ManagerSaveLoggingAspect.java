package org.example.expert.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.expert.domain.Log.Log;
import org.example.expert.domain.Log.LogRepository;
import org.example.expert.domain.Log.LogService;
import org.example.expert.domain.Log.ManagerLog;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class ManagerSaveLoggingAspect {
	private final LogService logService;

	@Around("@annotation(managerLog)")
	@Transactional(propagation  = Propagation.REQUIRES_NEW)
	public Object logManagerOperation(ProceedingJoinPoint jointPoint, ManagerLog managerLog) throws Throwable {
		String methodName = jointPoint.getSignature().getName();
		String action = managerLog.action();
		String description = managerLog.description();

		try {
			Object result = jointPoint.proceed();
			logService.saveLog(Log.LogStatus.SUCCESS, action, description + " 성공");
			return result;
		} catch (Exception e) {
			logService.saveLog(Log.LogStatus.FAIL, action, description + " 실패: " + e.getMessage());
			throw e;
		}
	}
}
