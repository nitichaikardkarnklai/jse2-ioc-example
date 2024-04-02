package com.usermanagement.usermanagement.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("")
    public List<Profile> getAllProfile() {
        return profileService.getProfiles();
    }

//    @PostMapping("")
//    public Profile createProfile(@RequestBody ProfileRequestDto profileRequestDto) {
//        return profileService.createProfile(profileRequestDto);
//    }
}

record ProfileRequestDto(
        @NotNull
        @Email
        String email,
        @NotNull(message = "Profile name cannot be null")
        @Size(min = 3, max = 20, message = "Profile name should be between 3 and 20 characters")
        String name
){}