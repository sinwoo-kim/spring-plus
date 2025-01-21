package org.example.expert.domain.todo.service;

import java.util.List;

import org.example.expert.client.WeatherClient;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.dto.request.TodoGetRequest;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.request.TodoSearchRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

	private final TodoRepository todoRepository;
	private final WeatherClient weatherClient;
	private final UserRepository userRepository;

	@Transactional
	public TodoSaveResponse saveTodo(Long userId, TodoSaveRequest todoSaveRequest) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

		String weather = weatherClient.getTodayWeather();

		Todo newTodo = new Todo(
			todoSaveRequest.getTitle(),
			todoSaveRequest.getContents(),
			weather,
			user
		);
		Todo savedTodo = todoRepository.save(newTodo);

		return new TodoSaveResponse(
			savedTodo.getId(),
			savedTodo.getTitle(),
			savedTodo.getContents(),
			weather,
			new UserResponse(user.getId(), user.getEmail(), user.getNickName())
		);
	}

	public Page<TodoResponse> getTodos(int page, int size, TodoGetRequest todoGetRequest) {
		Pageable pageable = PageRequest.of(page - 1, size);

		Page<Todo> todos = todoRepository.findAllByOrderByModifiedAtDesc(
			todoGetRequest.weather(),
			todoGetRequest.getStartDateTime(),
			todoGetRequest.getEndDateTime(),
			pageable
		);
		log.info(":::: 할 일 목록 조회 완료");
		return todos.map(todo -> new TodoResponse(
			todo.getId(),
			todo.getTitle(),
			todo.getContents(),
			todo.getWeather(),
			new UserResponse(todo.getUser().getId(), todo.getUser().getEmail(), todo.getUser().getNickName()),
			todo.getCreatedAt(),
			todo.getModifiedAt()
		));
	}

	public TodoResponse getTodo(long todoId) {
		Todo todo = todoRepository.findByIdWithUser(todoId)
			.orElseThrow(() -> new InvalidRequestException("Todo not found"));

		User user = todo.getUser();

		return new TodoResponse(
			todo.getId(),
			todo.getTitle(),
			todo.getContents(),
			todo.getWeather(),
			new UserResponse(user.getId(), user.getEmail(), user.getNickName()),
			todo.getCreatedAt(),
			todo.getModifiedAt()
		);
	}


	public Page<TodoSearchResponse> searchTodoList(int page, int size,
		TodoSearchRequest todoSearchRequest) {
		Pageable pageable = PageRequest.of(page - 1, size);

		return todoRepository.findByTodoList(
			todoSearchRequest.title(),
			todoSearchRequest.getStartDateTime(),
			todoSearchRequest.getEndDateTime(),
			todoSearchRequest.nickName(),
			pageable
		);
	}
}
