package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.mvc.model.RestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.BULK;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
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
public abstract class BaseController<M extends RestModel> {

    protected abstract Long add(M model);

    protected abstract Set<Long> add(Set<M> models);

    protected abstract void modify(M model);

    protected abstract void remove(Long id);

    protected abstract M find(Long id);

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public void handleUnsupported() {
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> post(@RequestBody M model) {

        return created(
                fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(add(model)).toUri())
                .build();
    }

    @RequestMapping(method = POST, headers = BULK)
    public ResponseEntity<?> post(@RequestBody M[] models) {

        Set<M> modelsSet = stream(models).collect(toSet());
        Set<Long> ids = add(modelsSet);
        final StringBuilder idsString = new StringBuilder("{{");

        ids.forEach(id -> {
                    idsString.append(id);
                    idsString.append(",");
                }
        );

        idsString.deleteCharAt(idsString.length() - 1);
        idsString.append("}}");

        return created(
                fromCurrentRequest()
                        .path("/{ids}")
                        .buildAndExpand(idsString).toUri())
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
