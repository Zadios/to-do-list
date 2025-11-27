package com.viscovich.to_do_list.service;

import com.viscovich.to_do_list.exception.InvalidDeadlineException;
import com.viscovich.to_do_list.exception.TaskAlreadyCompletedException;
import com.viscovich.to_do_list.exception.TaskIncompleteDataException;
import com.viscovich.to_do_list.exception.TaskNotFoundException;
import com.viscovich.to_do_list.model.Priority;
import com.viscovich.to_do_list.model.Task;
import com.viscovich.to_do_list.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()){
            Task task = existingTask.get();
            if (task.isCompleted()) {
                throw new TaskAlreadyCompletedException(task.getTitle());
            } else {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setDeadline(updatedTask.getDeadline());
                return taskRepository.save(task);
            }
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    public Task updateDeadline(Long id, LocalDate newDeadline) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (newDeadline != null && newDeadline.isBefore(LocalDate.now())) {
            throw new InvalidDeadlineException(newDeadline);
        }

        task.setDeadline(newDeadline);
        return taskRepository.save(task);
    }

    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (task.isCompleted()) {
            throw new TaskAlreadyCompletedException(task.getTitle());
        }

        if (task.getDescription() == null || task.getDescription().isBlank()) {
            throw new TaskIncompleteDataException(task.getTitle());
        }

        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public Task updatePriority(Long id, Priority priority) {
        Task task = getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setPriority(priority);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
