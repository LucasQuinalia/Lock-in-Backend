package co.lockinfocus.lockin.controller;

import co.lockinfocus.lockin.focus.DataFocus;
import co.lockinfocus.lockin.focus.Focus;
import co.lockinfocus.lockin.focus.FocusRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/focuses")
public class FocusController {
    @Autowired
    private FocusRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DataFocus data) {
        repository.save(new Focus(data));
    }
}