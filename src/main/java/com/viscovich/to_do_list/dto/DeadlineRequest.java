package com.viscovich.to_do_list.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class DeadlineRequest {
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate deadline;
}

