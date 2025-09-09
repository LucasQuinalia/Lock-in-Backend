package co.lockinfocus.lockin.task;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DataPutTask(
        @NotNull
        Long id,
        String title,
        Boolean completed,
        LocalDate due_date
) {
}
