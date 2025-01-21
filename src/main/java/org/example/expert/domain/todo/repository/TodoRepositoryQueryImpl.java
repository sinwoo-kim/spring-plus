package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.comment.entity.QComment.*;
import static org.example.expert.domain.manager.entity.QManager.*;
import static org.example.expert.domain.todo.entity.QTodo.*;
import static org.example.expert.domain.user.entity.QUser.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
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

	@Override
	public Page<TodoSearchResponse> findByTodoList(String title, LocalDateTime startDate, LocalDateTime endDate, String nickName,
		Pageable pageable) {

		List<TodoSearchResponse> todoList = jpaQueryFactory
			.select(Projections.constructor(TodoSearchResponse.class,
				todo.title,
				JPAExpressions
					.select(manager.count())
					.from(manager)
					.where(manager.todo.eq(todo)),
				JPAExpressions
					.select(comment.count())
					.from(comment)
					.where(comment.todo.eq(todo))))
			.from(todo)
			.where(
				titleContains(title),
				nickNameContains(nickName),
				todo.createdAt.between(startDate, endDate)
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(todo.createdAt.desc())
			.fetch();

		long total = jpaQueryFactory
			.select(todo.count())
			.from(todo)
			.fetchOne();

		return new PageImpl<>(todoList, pageable, total);

	}

	// 동적 쿼리를 위한 메서드들
	private BooleanExpression titleContains(String title) {
		return StringUtils.hasText(title) ?
			todo.title.contains(title) : null;
	}

	private BooleanExpression nickNameContains(String nickName) {
		return StringUtils.hasText(nickName) ?
			user.nickName.contains(nickName) : null;
	}
}


