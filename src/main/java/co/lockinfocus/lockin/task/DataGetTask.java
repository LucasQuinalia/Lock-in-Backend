package co.lockinfocus.lockin.task;

import java.time.LocalDate;

public record DataGetTask(
        Long id,
        String title,
        Boolean completed,
        LocalDate due_date,
        Long focus_id
) {

    public DataGetTask(Task task) {
        this(task.getId(), task.getTitle(), task.getCompleted(), task.getDue_date(), task.getFocus().getId());
    }
}
