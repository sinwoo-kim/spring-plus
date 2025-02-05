package org.example.expert.domain.todo.service;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

	@InjectMocks // 클래스를 기반으로 객체를 생성
	private TodoService todoService;

	@Mock
	private TodoRepository todoRepository;  // Repository는 Mock

	@Test
	void todo_단건_조회_시_todo가_존재하지_않아_예외가_발생한다() throws Exception {
		// given
		long todoId = 3L;

		//상황 설정
		when(todoRepository.findByIdWithUser(todoId)).thenReturn(Optional.empty());

		// when & then
		Assertions.assertThrows(InvalidRequestException.class, () -> {
			todoService.getTodo(todoId);
		});
	}

	@Test
	public void getTodos_success() {
		// given

		// when
		// then

	}
}
