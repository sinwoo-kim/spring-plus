package org.example.expert.domain.todo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user WHERE " +
		"(:startDate IS NULL OR t.modifiedAt >= :startDate) AND " +
		"(:endDate IS NULL OR t.modifiedAt <= :endDate)")
	Page<Todo> findAllByOrderByModifiedAtDesc(
		@Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate,
		Pageable pageable
	);

	@Query("SELECT t FROM Todo t " +
		"LEFT JOIN t.user " +
		"WHERE t.id = :todoId")
	Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
