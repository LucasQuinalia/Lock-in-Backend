package co.lockinfocus.lockin.controller;

import co.lockinfocus.lockin.focus.DataGetFocus;
import co.lockinfocus.lockin.focus.FocusRepository;
import co.lockinfocus.lockin.task.DataGetTask;
import co.lockinfocus.lockin.task.DataPostTask;
import co.lockinfocus.lockin.task.Task;
import co.lockinfocus.lockin.task.TaskRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/focuses/{focusId}/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FocusRepository focusRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DataPostTask data, @PathVariable Long focusId, UriComponentsBuilder uriBuilder) {
        var optionalFocus = focusRepository.findById(data.focus_id());

        if (optionalFocus.isPresent()) {
            var focus = optionalFocus.get();

            var task = new Task(data, focus);
            taskRepository.save(task);

            var uri = uriBuilder.path("/focuses/" + focusId + "/tasks/{id}").buildAndExpand(task.getId()).toUri();

            return ResponseEntity.created(uri).body(new DataGetTask(task));
        }

        return ResponseEntity.notFound().build();
    }
}
