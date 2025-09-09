package co.lockinfocus.lockin.task;

import co.lockinfocus.lockin.controller.FocusController;
import co.lockinfocus.lockin.focus.Focus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "tasks")
@Entity(name = "Task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean completed;
    private LocalDate due_date;
    @ManyToOne
    @JoinColumn(name = "focus_id")
    private Focus focus;

    public Task(DataPostTask data, Focus focus) {
        this.title = data.title();
        this.completed = data.completed();
        this.due_date = data.due_date();
        this.focus = focus;
    }
}
