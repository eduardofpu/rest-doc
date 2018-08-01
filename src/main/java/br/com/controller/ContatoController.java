package br.com.controller;

import br.com.modelo.ContatoEntity;
import br.com.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping(path = "contatos")
    @ResponseStatus(HttpStatus.OK)
    List<ContatoEntity> findAll() {

        return contatoService.findAll();
    }

    @PostMapping(path = "contato")
    @ResponseStatus(HttpStatus.CREATED)
    ContatoEntity create(@RequestBody ContatoEntity contatdo) {
        return contatoService.create(contatdo);
    }

    @PutMapping(path = "edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ContatoEntity update(@RequestBody ContatoEntity contato){
        return contatoService.update(contato);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        contatoService.delete(id);
    }


}
