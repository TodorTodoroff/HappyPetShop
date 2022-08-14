package com.example.happypetshop.web;


import com.example.happypetshop.models.dtos.UserUpdateUsernameDTO;
import com.example.happypetshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AccountDetailsController {

    private final UserService userService;

    public AccountDetailsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/details/{id}")
    public String userDetails(
            @PathVariable("id") Long id,
            Model model) {

        model.addAttribute("user", this.userService.getUserById(id));

        return "details";
    }

    @ModelAttribute("userDetails")
    public UserUpdateUsernameDTO initUserModel() {
        return new UserUpdateUsernameDTO();
    }

    @GetMapping("/user/edit-username")
    public String userEditUsername(
    ) {
        return "edit-username";
    }

    @PostMapping("/user/edit-username/update/{id}")
    public String updateNewUsername(
            @PathVariable("id") Long id,
            @Valid UserUpdateUsernameDTO userDetails,
            BindingResult br,
            RedirectAttributes redirectAttributes
    ){

        if (br.hasErrors()){
            redirectAttributes.addFlashAttribute("userDetails", userDetails);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDetails",
                    br);

            return "redirect:/user/edit-username";
        }

        boolean match =
                this.userService.
                        checkIfUsernamesMatch(userDetails.getUsername(), userDetails.getRepeatUsername());

        if (!match) {
            redirectAttributes.addFlashAttribute("usernamesDoNotMatch", true);
            return "redirect:/user/edit-username";
        }

        boolean isOccupied =
                this.userService.checkIfUsernameIsOccupied(userDetails.getUsername());

        if (isOccupied){
            redirectAttributes.addFlashAttribute("usernameOccupied", true);
            return "redirect:/user/edit-username";
        }

            this.userService.updateUsername(userDetails.getUsername(), id);


        return "redirect:/";
    }

    @GetMapping("/users")
    public String getUsersDetailsAdminPage(){
        return "user-admin-details";
    }

}
