package com.stepsoft.study.mvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

/**
 * @author Eugene Stepanenkov
 */
public abstract class BaseController<M> {

    protected abstract Long add(M model);

    protected abstract void modify(M model);

    protected abstract void remove(Long id);

    protected abstract M find(Long id);

    @RequestMapping(method = POST)
    public ResponseEntity<?> post(@RequestBody M model) {

        return created(
                fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(add(model)).toUri())
                .build();
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody M model) {

        modify(model);
        return ok().build();
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {

        remove(id);
        return ok().build();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<M> get(@PathVariable Long id) {

        M model = find(id);
        return ok().body(model);
    }
}
