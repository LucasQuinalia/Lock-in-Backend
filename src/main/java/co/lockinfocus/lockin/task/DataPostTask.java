package co.lockinfocus.lockin.task;

import co.lockinfocus.lockin.focus.Focus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DataPostTask(
        @NotBlank
        String title,
        @NotNull
        Boolean completed,
        @NotNull
        LocalDate due_date,
        @NotNull
        Long focus_id
) {
}
