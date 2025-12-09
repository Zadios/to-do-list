package com.viscovich.to_do_list.dto;

import com.viscovich.to_do_list.model.Priority;
import jakarta.validation.constraints.NotNull;

public class UpdatePriorityDTO {

    @NotNull(message = "La prioridad debe ser especificada (NONE, LOW, MEDIUM, HIGH)")
    private Priority priority;

    public Priority getPriority(){
        return priority;
    }

    public void setPriority(Priority priority){
        this.priority = priority;
    }
}
