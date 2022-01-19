package com.eventoApp.Biblioteca.controller;

import com.eventoApp.Biblioteca.model.Livro;
import com.eventoApp.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    LivroRepository livros;

    @GetMapping
    public ModelAndView listar(){
        ModelAndView modelAndView = new ModelAndView("ListaLivros");
        modelAndView.addObject("livros", livros.findAll());
        modelAndView.addObject(new Livro());
        return modelAndView;
    }

    @PostMapping
    public String cadastrarLivro(Livro livro){
        this.livros.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable("id") Long id, Model model){
        Livro livro = livros.findById(id).orElseThrow();
        livros.delete(livro);
        return "redirect:/livros";
    }



}
