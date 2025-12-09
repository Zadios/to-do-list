package com.viscovich.to_do_list.specification;

import com.viscovich.to_do_list.model.Priority;
import com.viscovich.to_do_list.model.Task;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskSpecification {
    public static Specification<Task> hasCompleted(Boolean completed) {
        if (completed == null){
            return null;
        }
        return (root, query, builder)
                -> builder.equal(root.get("completed"), completed);
    }

    public static Specification<Task> hasPriority(Priority priority) {
        if (priority == null){
            return null;
        }
        return (root, query, builder)
                -> builder.equal(root.get("priority"), priority);
    }

    public static Specification<Task> titleContains(String text) {
        if (text == null || text.isBlank()){
            return null;
        }

        return (root, query, builder)
            -> builder.like(builder.lower(root.get("title")), "%" + text.toLowerCase() + "%" );
    }

    public static Specification<Task> deadlineBetweenNowAnd(String deadlineToText) {
        if (deadlineToText == null || deadlineToText.isBlank()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(deadlineToText, formatter);

        return (root, query, builder)
                -> builder.between(root.get("deadline"), LocalDate.now(), endDate);
    }

}
