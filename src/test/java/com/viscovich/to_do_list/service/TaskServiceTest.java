package com.viscovich.to_do_list.service;

import com.viscovich.to_do_list.exception.TaskAlreadyCompletedException;
import com.viscovich.to_do_list.model.Task;
import com.viscovich.to_do_list.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void whenCompleteTask_givenTaskIsAlreadyCompleted_thenShouldThrowException() {
        Long id = 1L;

        Task task = new Task("Prueba", "Esto es un test");
        task.setCompleted(true);

        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        assertThrows(TaskAlreadyCompletedException.class, () -> {
            taskService.completeTask(id);
        });
    }
}
