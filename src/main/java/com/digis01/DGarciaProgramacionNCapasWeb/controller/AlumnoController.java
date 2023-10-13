/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaProgramacionNCapasWeb.controller;

import com.digis01.DGarciaProgramacionNCapasWeb.Model.Alum;
import com.digis01.DGarciaProgramacionNCapasWeb.Model.AlumnoDireccion;
import com.digis01.DGarciaProgramacionNCapasWeb.Model.Direccion;
import com.digis01.DGarciaProgramacionNCapasWeb.Model.Estado;
import com.digis01.DGarciaProgramacionNCapasWeb.Model.Pais;
import com.digis01.DGarciaProgramacionNCapasWeb.Model.Semestre;

import java.io.IOException;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ALIEN 34
 */
//localhost:8080/alumno/
@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    //localhost:8080/alumno/listado
    @GetMapping("/listado")
    private String listadoAlumnos(Model model) {

        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "http://localhost:8080/alumnoapi/GetAll";

        ResponseEntity<List<Alum>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Alum>>() {
        }
        );

        List<Alum> alumnos = response.getBody();

        model.addAttribute("alumnos", alumnos); //envío de datos para HTML
        model.addAttribute("alumnoBusqueda", new Alum());

        return "listadoAlumnos";
    }

    @PostMapping("/listado")
    private String listadoAlumnos(@ModelAttribute("alumnoBusqueda") Alum alumnoBusqueda, Model model) {

        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "http://localhost:8080/alumnoapi/GetAll";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Alum> requestEntity = new HttpEntity<>(alumnoBusqueda, headers);
        
        ResponseEntity<List<Alum>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Alum>>() {
        }
        );

        List<Alum> alumnos = response.getBody();
        model.addAttribute("alumnos", alumnos); //envío de datos para HTML
        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
//List<Direccion> direcciones = direccionDAOImplementation.GetAll();
        return "listadoAlumnos";
    }
//
//    @PostMapping("/listado")
//    private String listadoAlumnos(@ModelAttribute("alumnoBusqueda") Alum alumnoBusqueda, Model model) {
//
//        List<Alum> alumnos = alumnoDAOImplementation.GetAll(alumnoBusqueda);
//        model.addAttribute("alumnos", alumnos); //envío de datos para HTML
//        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
////List<Direccion> direcciones = direccionDAOImplementation.GetAll();
//        return "listadoAlumnos";
//    }
//
//    //localhost:8080/alumno/editarAlumno/85

    @GetMapping("/form/{idalumno}")
    public String Form(@PathVariable int idalumno, Model model) {
//        List<Semestre> semestres = semestreDAOImplementation.GetAll();
//        model.addAttribute("semestres", semestres);
        RestTemplate restTemplate = new RestTemplate();
 // List
        ResponseEntity<Semestre[]> responseEntitySemestre = restTemplate.getForEntity("http://localhost:8080/semestreapi/GetAll", Semestre[].class);
        ResponseEntity<Pais[]> responseEntityPais = restTemplate.getForEntity("http://localhost:8080/paisapi/GetAll", Pais[].class);

        model.addAttribute("semestres", responseEntitySemestre.getBody());
        model.addAttribute("paises", responseEntityPais.getBody());

        if (idalumno == 0) { //ADD
            model.addAttribute("alumnodireccion", new AlumnoDireccion());
            return "formAlumnoEditable";
        } else { //UPDATE
//            Alum alumno = alumnoDAOImplementation.GetById(idalumno);
//            model.addAttribute("alumno", alumno);
//            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
//            alumnoDireccion.setAlumno(alumnoDAOImplementation.GetById(idalumno));
//            alumnoDireccion.setDireccion(direccionDAOImplementation.GetByIdUsuario(idalumno));
//            
            //try catch 
//            if (alumnoDireccion.getDireccion().getEstado() != null) {
//                model.addAttribute("Estados", estadoDAOImplementation.GetByIdPais(alumnoDireccion.getDireccion().getEstado().getPais().getIdpais()));
//            }

            //mode.addAttribute("Municipios", municiDAOImpl.getByIDEstado(alumnodirecciom.getDireccion().GetColonia().GetMuni().GetEstado.GetIDEstado) )
//            model.addAttribute("alumnodireccion", alumnoDireccion);
        }
        return "formAlumnoEditable";
    }

//
//    // VALIDACIÓN CON INFORMACIÓN DEL CLIENTE
////    @PostMapping("/form")
////    public String Update(@ModelAttribute AlumnoDireccion alumnodireccion) {
////        // actualización
////        Semestre semestre = new Semestre();
////        semestre.setIdsemestre(2);
////        alumnodireccion.getAlumno().setSemestre(semestre);
////
////        if (alumnodireccion.getAlumno().getIdalumno() > 0) { //UPDATE
////            alumnoDAOImplementation.Update(alumnodireccion.getAlumno());
////        } else {
//            int idAlumno = alumnoDAOImplementation.Add(alumnodireccion.getAlumno()); //Regresar el IDINSERTADO
////            //alumnoDireccion.direccion.IdAlumno = 0; //Al idinsertado
////            //NOTAAAAAAA: esto es por que no recuperamos información de dirección
////            Direccion direccion = new Direccion("Reforma", "09", "199", new Estado(1), new Alum(idAlumno));
////            alumnodireccion.setDireccion(direccion); 
////            direccionDAOImplementation.Add(alumnodireccion.getDireccion());
////        }
////
////        return "redirect:/alumno/listado";
////    }
//    // VALIDACIÓN CON CLIENTE Y SERVIDOR
//    @PostMapping("/form")
//    public String Update(@Valid @ModelAttribute("alumnodireccion") AlumnoDireccion alumnodireccion,
//            BindingResult bindingResult,
//            @RequestParam("imagenFile") MultipartFile imagenFile,
//            Model model) {
//        // actualización
//
//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("alumnodireccion", alumnodireccion);
//            return "formAlumnoEditable";
//        }
////        
////        Semestre semestre = new Semestre();
////        semestre.setIdsemestre(2);
////        alumnodireccion.getAlumno().setSemestre(semestre);
////
//
//        try {
//            if (!imagenFile.isEmpty()) {
//                byte[] bytes = imagenFile.getBytes();
//                String imagenBase64 = Base64.encodeBase64String(bytes);
//                alumnodireccion.getAlumno().setImagen(imagenBase64);
//            } else {
//                alumnodireccion.getAlumno().setImagen(null);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (alumnodireccion.getAlumno().getIdalumno() > 0) { //UPDATE
//            //alumnoDAOImplementation.Update(alumnodireccion.getAlumno());
//
//            alumnoDAOImplementation.Update(alumnodireccion.getAlumno());
//
//        } else {
//            int idAlumno = alumnoDAOImplementation.Add(alumnodireccion.getAlumno()); //Regresar el IDINSERTADO
//            //alumnoDireccion.direccion.IdAlumno = 0; //Al idinsertado
//            //NOTAAAAAAA: esto es por que no recuperamos información de dirección
//            Direccion direccion = new Direccion("Reforma", "09", "199", new Estado(1), new Alum(idAlumno));
//            alumnodireccion.setDireccion(direccion);
//            direccionDAOImplementation.Add(alumnodireccion.getDireccion());
//        }
//
//        return "redirect:/alumno/listado";
//    }
//
//    @GetMapping("/GetEstadoByIdPais")
//    @ResponseBody
//    public List<Estado> GetEstadoByIdPais(@RequestParam("IdPais") int IdPais) {
//        List<Estado> estados = estadoDAOImplementation.GetByIdPais(IdPais); // Obtiene los datos del servicio
//        return estados;
//    }
//
//    @GetMapping("/ChangeStatus")
//    @ResponseBody
//    public void ChangeStatus(@RequestParam("idAlumno") int idAlumno, @RequestParam("status") boolean status) {
//        alumnoDAOImplementation.ChangeStatus(idAlumno, status);
//    }
//
//    //    @GetMapping("/add")
////    public String Add(Model model) {
////
////        // Alum alumno = new Alum();
////        model.addAttribute("alumno", new Alum());
////        return "formAlumno";
////    }
////        @PostMapping("addalumno")
////    public String Add(@ModelAttribute Alum alumno) {
////
////        //la logica necesaria para enviar dicha información a DAO
////        alumnoDAOImplementation.Add(alumno);
////
////        return "redirect:/alumno/listado";
////
////    }
}
