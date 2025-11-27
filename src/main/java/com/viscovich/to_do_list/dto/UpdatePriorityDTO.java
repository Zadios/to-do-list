package com.viscovich.to_do_list.dto;

import com.viscovich.to_do_list.model.Priority;

public class UpdatePriorityDTO {
    private Priority priority;

    public Priority getPriority(){
        return priority;
    }

    public void setPriority(Priority priority){
        this.priority = priority;
    }
}
