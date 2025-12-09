package com.viscovich.to_do_list.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DeadlineRequest {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deadline;

    @NotNull(message = "La fecha límite es obligatoria")
    public LocalDate getDeadline() {
        return deadline;
    }
}

