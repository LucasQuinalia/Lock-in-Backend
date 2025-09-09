package co.lockinfocus.lockin.controller;

import co.lockinfocus.lockin.focus.*;
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
    public ResponseEntity create(@RequestBody @Valid DataPostFocus data, UriComponentsBuilder uriBuilder) {
        var focus = new Focus(data);
        repository.save(focus);

        var uri = uriBuilder.path("/focuses/{id}").buildAndExpand(focus.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataGetFocus(focus));
    }

    @GetMapping
    public ResponseEntity<Page<DataGetFocus>> read(@PageableDefault(size = 15, sort = {"title"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(DataGetFocus::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataGetFocus> readById(@PathVariable Long id) {
        var optionalFocus = repository.findById(id);

        if (optionalFocus.isPresent()) {
            var focus = optionalFocus.get();
            return ResponseEntity.ok(new DataGetFocus(focus));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataPutFocus data) {
        var optionalFocus = repository.findById(data.id());

        if (optionalFocus.isPresent()) {
            var focus = optionalFocus.get();
            focus.updateFocus(data);

            return ResponseEntity.ok(new DataGetFocus(focus));
        }

        return ResponseEntity.notFound().build();
    }
}