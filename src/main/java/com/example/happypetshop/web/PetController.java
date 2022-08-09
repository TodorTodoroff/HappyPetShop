package com.example.happypetshop.web;

import com.example.happypetshop.models.dtos.PetRegisterDTO;
import com.example.happypetshop.models.dtos.UserRegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/pets")
public class PetController {



    @ModelAttribute("petModel")
    public PetRegisterDTO initUserModel() {
        return new PetRegisterDTO();
    }

    @GetMapping("/register-pet")
    public String registerPet() {
        return "register-pet";
    }

    @PostMapping("/register-pet")
    public String registerPet(@Valid PetRegisterDTO petModel,
                           BindingResult binding,
                           RedirectAttributes redirectAttributes
    ) {

        if (binding.hasErrors()) {
            redirectAttributes.addFlashAttribute("petModel", petModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.petModel",
                    binding);
            return "redirect:/pets/register-pet";
        }


        return "redirect:/pets/pets-all";
    }

    @GetMapping("/pets-all")
    public String getPets(){

        return "pets-all";
    }


}
