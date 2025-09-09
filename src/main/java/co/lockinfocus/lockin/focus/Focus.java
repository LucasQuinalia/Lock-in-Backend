package co.lockinfocus.lockin.focus;

import co.lockinfocus.lockin.task.Task;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Table(name = "focuses")
@Entity(name = "Focus")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Focus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer timer;
    private Integer short_break;
    private Integer long_break;
    @OneToMany(mappedBy = "focus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Focus(DataPostFocus data) {
        this.title = data.title();
        this.timer = data.timer();
        this.short_break = data.short_break();
        this.long_break = data.long_break();
    }

    public void updateFocus(@Valid DataPutFocus data) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.timer() != null) {
            this.timer = data.timer();
        }
        if (data.short_break() != null) {
            this.short_break = data.short_break();
        }
        if (data.long_break() != null) {
            this.long_break = data.long_break();
        }
    }
}
