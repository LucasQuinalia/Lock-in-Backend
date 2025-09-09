package co.lockinfocus.lockin.controller;

import co.lockinfocus.lockin.focus.DataGetFocus;
import co.lockinfocus.lockin.focus.FocusRepository;
import co.lockinfocus.lockin.task.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<DataGetTask>> read(@PageableDefault(size = 15, sort = {"title"}) Pageable pageable) {
        var page = taskRepository.findAll(pageable).map(DataGetTask::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<DataGetTask> readById(@PathVariable Long taskId) {
        var optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            var task = optionalTask.get();
            return ResponseEntity.ok(new DataGetTask(task));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataPutTask data) {
        var optionalTask = taskRepository.findById(data.id());

        if (optionalTask.isPresent()) {
            var task = optionalTask.get();
            task.updateTask(data);

            return ResponseEntity.ok(new DataGetTask(task));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{taskId}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long taskId) {
        var optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            var task = optionalTask.get();
            task.getFocus().getTasks().remove(task);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
