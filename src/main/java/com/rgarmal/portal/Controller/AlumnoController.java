package com.rgarmal.portal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rgarmal.portal.model.Alumno;
import com.rgarmal.portal.services.AlumnoService;


@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
    
    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(Model model) {

        List<Alumno> alumnos = alumnoService.findAll();

        ModelAndView modelAndView = new ModelAndView("alumnos/list");
        modelAndView.addObject("alumnos", alumnos);
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) int codigo) {

        Alumno alumno = alumnoService.find(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alumno", alumno);
        modelAndView.setViewName("alumnos/edit");
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Alumno alumno) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alumno", new Alumno());
        modelAndView.setViewName("alumnos/new");
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Alumno alumno) {

        Alumno save = alumnoService.save(alumno);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + save.getCodigo());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Alumno alumno) {

        alumnoService.update(alumno);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + alumno.getCodigo());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int id) {

        alumnoService.delete(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../list");
        return modelAndView;
    }
}
