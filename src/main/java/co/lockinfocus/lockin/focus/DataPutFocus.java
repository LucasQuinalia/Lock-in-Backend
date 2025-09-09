package co.lockinfocus.lockin.focus;

import jakarta.validation.constraints.NotNull;

public record DataPutFocus(
        @NotNull
        Long id,
        String title,
        Integer timer,
        Integer short_break,
        Integer long_break
) {
}
