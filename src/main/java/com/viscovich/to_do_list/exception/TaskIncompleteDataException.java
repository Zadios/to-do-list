package com.viscovich.to_do_list.exception;

public class TaskIncompleteDataException extends RuntimeException {
    public TaskIncompleteDataException(String title) {
        super("La descripción de la tarea '" + title + "' está vacía y no puede completarse. Añade una descripción.");
    }
}
