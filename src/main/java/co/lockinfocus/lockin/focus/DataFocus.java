package co.lockinfocus.lockin.focus;

import co.lockinfocus.lockin.task.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DataFocus(
        @NotBlank
        String title,
        @NotBlank
        @Pattern(regexp = "\\d{60,86400}")
        int timer,
        @NotBlank
        @Pattern(regexp = "\\d{60,86400}")
        int short_break,
        @NotBlank
        @Pattern(regexp = "\\d{60,86400}")
        int long_break,
        List<Task> tasks
) {

}