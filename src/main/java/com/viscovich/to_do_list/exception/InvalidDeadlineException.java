package com.viscovich.to_do_list.exception;

import java.time.LocalDate;

public class InvalidDeadlineException extends RuntimeException {
    public InvalidDeadlineException(LocalDate deadline) {
        super("La fecha " + deadline + " ya pasó. Ingrese una fecha actual o posterior.");
    }
}
