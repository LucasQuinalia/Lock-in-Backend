package co.lockinfocus.lockin.controller;

import co.lockinfocus.lockin.focus.DataGetFocus;
import co.lockinfocus.lockin.focus.DataPostFocus;
import co.lockinfocus.lockin.focus.Focus;
import co.lockinfocus.lockin.focus.FocusRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/focuses")
public class FocusController {
    @Autowired
    private FocusRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity post(@RequestBody @Valid DataPostFocus data, UriComponentsBuilder uriBuilder) {
        var focus = new Focus(data);
        repository.save(focus);

        var uri = uriBuilder.path("/focuses/{id}").buildAndExpand(focus.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataGetFocus(focus));
    }

    @GetMapping
    public ResponseEntity<Page<DataGetFocus>> get(@PageableDefault(size = 15, sort = {"title"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(DataGetFocus::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataGetFocus> getById(@PathVariable Long id) {
        var focus = repository.findById(id);

        return ResponseEntity.ok(new DataGetFocus(focus));
    }
}