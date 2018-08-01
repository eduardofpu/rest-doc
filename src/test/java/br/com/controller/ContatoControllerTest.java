package br.com.controller;

import br.com.AbstractTest;
import br.com.modelo.ContatoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//JAVAOITo8
public class ContatoControllerTest extends AbstractTest{

    private final String PATH = "/";

    @Test
    public void findAll() throws Exception {
       saveV2();
       this.mockMvc.perform(MockMvcRequestBuilders.get(PATH+"contatos"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void create() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("payload/contatos.json"));

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH+"contato")
                .contentType(APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void updateV2() throws Exception {
        ContatoEntity edit = saveV1();
        int id = Integer.parseInt(valueOf(edit.getId()));

        Map<String, String> data = new HashMap<>();
        data.put("id", valueOf(id));
        data.put("name","Ludimila Martins");
        data.put("telefone", "(34)996342575");
        data.put("email", "ludimila@gmail.com");

         this.mockMvc.perform(
                put(PATH + "edit")

                        .contentType(APPLICATION_JSON)
                        .content(JSONObject.toJSONString(data)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("id", Matchers.is(id)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }


    @Test
    public void delete() throws Exception {

        ContatoEntity created = saveV1();
        Assertions.assertThat(created.getId()).isNotNull();

        String id = valueOf(created.getId());

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(PATH + id)
                        .param("id",id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assertions.assertThat(id).isNotNull();
    }

    private ContatoEntity saveV1(){
        ContatoEntity contato = new ContatoEntity("Luis", "(34)32345710", "luis@gmail.com");
        return repository.save(contato);
    }

    private void saveV2(){

        ContatoEntity contato1 = new ContatoEntity("Lucas", "(34)32225510", "lucas@gmail.com");
        repository.save(contato1);

        ContatoEntity contato2 = new ContatoEntity("Mara", "(34)32223412", "mara@gmail.com");
        repository.save(contato2);

        ContatoEntity contato3 = new ContatoEntity("Adriana", "(34)32233011", "adria@gmail.com");
        repository.save(contato3);

        ContatoEntity contato4 = new ContatoEntity("Matheus", "(34)32244015", "matheus@gmail.com");
        repository.save(contato4);

        ContatoEntity contato5 = new ContatoEntity("Marcos", "(34)322785017", "carcos@gmail.com");
        repository.save(contato5);
    }

}