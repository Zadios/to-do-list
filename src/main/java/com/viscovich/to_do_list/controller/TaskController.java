package com.viscovich.to_do_list.controller;

import com.viscovich.to_do_list.dto.DeadlineRequest;
import com.viscovich.to_do_list.dto.UpdatePriorityDTO;
import com.viscovich.to_do_list.exception.TaskNotFoundException;
import com.viscovich.to_do_list.model.Priority;
import com.viscovich.to_do_list.model.Task;
import com.viscovich.to_do_list.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<Task> getAllTasks(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Boolean completed) {

        return taskService.getAllTasks(sortBy, order, priority, completed);
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

    @PatchMapping("/{id}/complete")
    public ResponseEntity<String> completeTask(@PathVariable Long id){
        Task task = taskService.completeTask(id);
        return ResponseEntity.ok("La tarea fue marcada como completada");
    }

    @PatchMapping("/{id}/priority")
    public ResponseEntity<Task> updatePriority(@PathVariable Long id,@RequestBody UpdatePriorityDTO dto) {
        Task updatedTask = taskService.updatePriority(id, dto.getPriority());
        return ResponseEntity.ok(updatedTask);
    }


    @PatchMapping("/{id}/deadline")
    public ResponseEntity<String> updateDeadline(@PathVariable Long id, @RequestBody DeadlineRequest request){
        Task updated = taskService.updateDeadline(id, request.deadline);
        return ResponseEntity.ok("La fecha límite para la tarea '" + updated.getTitle() + "' fue actualizada correctamente.");
    }

}
