package calories_control.features.auth.app.profile;

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