package br.com.service;

import br.com.modelo.ContatoEntity;

import java.util.List;

public interface ContatoService {

    List<ContatoEntity> findAll();
    ContatoEntity create(ContatoEntity contato);
    ContatoEntity update(ContatoEntity contato);
    public void delete(Long id);
}
