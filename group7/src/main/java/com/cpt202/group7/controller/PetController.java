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
@RequestMapping("/customer")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping("/petlist")
    public String showpets(Model model)
    {
        List<Pet> pets = petService.getPetList();
        model.addAttribute("pet",pets);
        return "/PetList";
    }


    @PostMapping("/delete")
    public String DeletePetc(@RequestParam Integer petId){
        System.out.println(petId);
        petService.DeletePetimp(petId);
        return "redirect:/customer/petlist";
    }


    @GetMapping("/petdetail")
    public String petdetail(Model model, Integer petId){
        Pet pet = petService.GetPet(petId);
        model.addAttribute("pet",pet);
        model.addAttribute("petid",petId);

        return "updatepet";
    }

    @PostMapping("/update")
    public String updatepet(@ModelAttribute("pet") Pet pet){
        System.out.println(pet);
        petService.UpdatePetimp(pet,pet.getPetId());
        return "redirect:/customer/petlist";
    }

    @RequestMapping("addpet")
    public String add(Model model){
        model.addAttribute("pet", new Pet());
        return "AddPet";
    }

    @PostMapping("/insert")
    public String InsertPet(HttpSession session, @ModelAttribute("pet") Pet pet){
        petService.InsertPet(pet,Integer.parseInt(session.getAttribute("userid").toString()));
        return "redirect:/customer/petlist";
    }


}
