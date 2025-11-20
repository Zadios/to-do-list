package com.viscovich.to_do_list.controller;

import com.viscovich.to_do_list.exception.TaskNotFoundException;
import com.viscovich.to_do_list.model.Task;
import com.viscovich.to_do_list.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }
}
