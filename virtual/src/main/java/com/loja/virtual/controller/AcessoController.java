package com.loja.virtual.controller;

import com.loja.virtual.model.Acesso;
import com.loja.virtual.repository.AcessoRepository;
import com.loja.virtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class AcessoController {

    @Autowired
    private AcessoService acessoservice;
    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody
    @PostMapping("salvarAcesso")
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {

        Acesso acessoSalvo = acessoservice.save(acesso);

        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }
    @ResponseBody
    @PostMapping(value = "deleteAcesso")
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso){

        acessoRepository.deleteById(acesso.getId());
        return new ResponseEntity<>("Acesso Removido", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/deleteAcessoPorId/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable ("id") Long id){

        acessoRepository.deleteById(id);
        return new ResponseEntity<>("Acesso Removido", HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "obterAcesso/{id}")
    public  ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id){

        Acesso acesso = acessoRepository.findById(id).get();

        return new  ResponseEntity<Acesso>(acesso,HttpStatus.OK);

    }
    @ResponseBody
    @GetMapping(value = "/obterPorDesc/{desc}")
    public  ResponseEntity<List<Acesso>> obterPorDesc(@PathVariable("desc") String desc){

        List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc);

        return new ResponseEntity<List<Acesso>>(acesso,HttpStatus.OK);



    }

}
