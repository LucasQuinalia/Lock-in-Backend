package co.lockinfocus.lockin.focus;

import co.lockinfocus.lockin.task.Task;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public record DataGetFocus(
        Long id,
        String title,
        Integer timer,
        Integer short_break,
        Integer long_break,
        List<Task> tasks
) {

    public DataGetFocus(Focus focus) {
        this(focus.getId(), focus.getTitle(), focus.getTimer(), focus.getShort_break(),  focus.getLong_break(), focus.getTasks());
    }

    public DataGetFocus(Optional<Focus> focus) {
        this(focus.get().getId(), focus.get().getTitle(), focus.get().getTimer(), focus.get().getShort_break(), focus.get().getLong_break(), focus.get().getTasks());
    }
}