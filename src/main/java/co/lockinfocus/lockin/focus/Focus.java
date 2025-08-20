package co.lockinfocus.lockin.focus;

import co.lockinfocus.lockin.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private int timer;
    private int short_break;
    private int long_break;
    @OneToMany(mappedBy = "focus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Focus(DataFocus data) {
        this.title = data.title();
        this.timer = data.timer();
        this.short_break = data.short_break();
        this.long_break = data.long_break();
    }
}
