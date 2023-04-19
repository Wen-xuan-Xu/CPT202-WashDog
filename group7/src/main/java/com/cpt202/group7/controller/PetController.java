package com.cpt202.group7.controller;

import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.service.PetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer/pet")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping("/list")
    public String showPets(Model model)
    {
        List<Pet> pets = petService.getPetList();
        model.addAttribute("pet",pets);
        return "/customer/pet/petList";
    }

    @PostMapping("/delete")
    public String deletePet(@RequestParam Integer petId){
        petService.deletePet(petId);
        return "redirect:/customer/pet/list";
    }

    @GetMapping("/detail")
    public String showPetDetails(Model model, Integer petId){
        Pet pet = petService.getPet(petId);
        model.addAttribute("pet",pet);
        model.addAttribute("petid",petId);
        model.addAttribute("allPetType",petService.getAllPetType());
        return "/customer/pet/updatePet";
    }

    @PostMapping("/update")
    public String updatePetDetails(@ModelAttribute("pet") Pet pet){
        petService.updatePet(pet,pet.getPetId());
        return "redirect:/customer/pet/list";
    }

    @RequestMapping("/add")
    public String showAddPetPage(Model model){
        model.addAttribute("pet", new Pet());
        model.addAttribute("allPetType",petService.getAllPetType());
        return "/customer/pet/addPet";
    }

    @PostMapping("/insert")
    public String insertPet(HttpSession session, @ModelAttribute("pet") Pet pet){
        petService.insertPet(pet,Integer.parseInt(session.getAttribute("userid").toString()));
        return "redirect:/customer/pet/list";
    }
}
