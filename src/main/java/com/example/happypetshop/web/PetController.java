package com.example.happypetshop.web;

import com.example.happypetshop.models.dtos.PetDetailDTO;
import com.example.happypetshop.models.dtos.PetRegisterDTO;
import com.example.happypetshop.models.user.PetShopUserDetails;
import com.example.happypetshop.services.PetService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

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
                              RedirectAttributes redirectAttributes,
                              Principal userDetails
                              ) {
        if (binding.hasErrors()) {
            redirectAttributes.addFlashAttribute("petModel", petModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.petModel",
                    binding);
            return "redirect:/pets/register-pet";
        }

        this.petService.save(petModel, userDetails);

        return "redirect:/pets/pets-all";
    }

    @GetMapping("/pets-all")
    public String getPets(
            Model model,
            @PageableDefault(
                    sort = "price",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 5) Pageable pageable) {

        model.addAttribute("pets", this.petService.getAllPets(pageable));

        return "pets-all";
    }

    @GetMapping("/{id}/details")
    public String getPetDetails(
            @PathVariable("id") Long id,
            Model model
    ) {

        var petDetailDTO = this.petService.getPetById(id)
                .orElseThrow(() ->
                        new RuntimeException("pet not found id: " + id));

        model.addAttribute("pet", petDetailDTO);

        return "pet-details";
    }


}
