package co.lockinfocus.lockin.focus;

import co.lockinfocus.lockin.task.Task;
import jakarta.validation.constraints.*;

import java.util.List;

public record DataPostFocus(
        @NotBlank
        String title,
        @NotNull
        @Min(60)
        @Max(86400)
        Integer timer,
        @NotNull
        @Min(60)
        @Max(86400)
        Integer short_break,
        @NotNull
        @Min(60)
        @Max(86400)
        Integer long_break,
        List<Task> tasks
) {

}