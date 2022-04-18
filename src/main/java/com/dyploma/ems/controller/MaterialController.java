package com.dyploma.ems.controller;

import com.dyploma.ems.entity.Material;
import com.dyploma.ems.repos.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialRepo materialRepo;

    @GetMapping("/add")
    private String addMaterial(Model model){
        model.addAttribute("materialForm", new Material());
        return "createMaterial";
    }

    @PostMapping(path = "/add")
    public String addNewMaterial (
            @RequestParam String material,
            @RequestParam String type,
            @RequestParam Integer price,
            @RequestParam String description){

        Material material1 = new Material(material, type, price, description);
        materialRepo.save(material1);
        return "redirect:/listOfMaterials";
    }

    @GetMapping("list")
    public String listOfMaterials (){
        return "listOfMaterials";
    }

}
