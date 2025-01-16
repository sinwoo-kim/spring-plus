package org.example.expert.domain.todo.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public record TodoGetRequest(
	String weather,
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate startDate,
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate endDate
) {
	public LocalDateTime getStartDateTime() {
		return (startDate() != null) ? startDate.atStartOfDay() : null;
	}
	public LocalDateTime getEndDateTime() {
		return (endDate != null) ? endDate.atTime(LocalTime.MAX) : null;
	}
}
