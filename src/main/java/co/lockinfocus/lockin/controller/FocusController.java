package co.lockinfocus.lockin.controller;

import co.lockinfocus.lockin.focus.DataGetFocus;
import co.lockinfocus.lockin.focus.DataPostFocus;
import co.lockinfocus.lockin.focus.Focus;
import co.lockinfocus.lockin.focus.FocusRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}