package calories_control.user.application.controller;

import calories_control.user.application.service.ProfileService;
import calories_control.user.application.dtos.ProfileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/view")
    public ResponseEntity<ProfileResponse> profile() {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.viewProfile());
    }
}