package com.viscovich.to_do_list.repository;

import com.viscovich.to_do_list.model.Priority;
import com.viscovich.to_do_list.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(Boolean completed);
    List<Task> findByPriority(Priority priority);
    List<Task> findByCompletedAndPriority(Boolean Completed, Priority priority);
}
