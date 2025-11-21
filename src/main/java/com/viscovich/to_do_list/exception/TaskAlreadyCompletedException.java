package com.viscovich.to_do_list.exception;

public class TaskAlreadyCompletedException extends RuntimeException {
    public TaskAlreadyCompletedException(String title) {
        super("La tarea '" + title + "' ya está completada y no puede modificarse");
    }
}
