package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Livro;
import application.model.LivroRepository;

@Controller
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;

    @RequestMapping("/livros")
    public String list(Model model) {
        model.addAttribute("livros", livroRepo.findAll());
        return "WEB-INF/list.jsp";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "WEB-INF/insert.jsp";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("titulo") String titulo){
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livroRepo.save(livro);
        return "redirect:/livros";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Livro> livro = livroRepo.findById(id);
        if(!livro.isPresent()) {
            return "redirect:/livros";
        }

        model.addAttribute("livro", livro.get());
        return "WEB-INF/update.jsp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") int id, @RequestParam("titulo") String titulo) {
        Optional<Livro> livro = livroRepo.findById(id);
        if(livro.isPresent()) {
            livro.get().setTitulo(titulo);
            livroRepo.save(livro.get());
        }

        return "redirect:/livros";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Livro> livro = livroRepo.findById(id);
        if(!livro.isPresent()) {
            return "redirect:/livros";
        }

        model.addAttribute("livro", livro.get());
        return "WEB-INF/delete.jsp";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        livroRepo.deleteById(id);
        return "redirect:/livros";
    }
}
