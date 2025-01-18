package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.todo.entity.QTodo.*;

import java.util.Optional;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class TodoRepositoryQueryImpl implements TodoRepositoryQuery {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Todo> findByIdWithUser(Long todoId) {
		return Optional.ofNullable(
			jpaQueryFactory
				.select(todo)
				.from(todo)
				.join(todo.user).fetchJoin()
				.where(todo.id.eq(todoId))
				.fetchOne()
		);
	}
}
