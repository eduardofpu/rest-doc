package br.com.service.ServiceImp;

import br.com.modelo.ContatoEntity;
import br.com.repository.ContatoRepository;
import br.com.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class ServiceImp implements ContatoService {

    private ContatoRepository repository;

    @Autowired
    public ServiceImp(ContatoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<ContatoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ContatoEntity create(ContatoEntity contato) {
        return repository.save(contato);
    }

    @Override
    public ContatoEntity update(ContatoEntity contato) {
        verifyContatoId(contato.getId());
        return repository.save(contato);
    }

    @Override
    public void delete(Long id) {
        verifyContatoId(id);
        repository.delete(id);

    }

    private void verifyContatoId(Long id) {
        Assert.noNullElements(new Object[]{repository.findOne(id), id}, "Id not exist" +id);
    }



}
