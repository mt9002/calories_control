package calories_control.user.application.controller;

import calories_control.user.application.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

import java.io.IOException;


@RestController
public class AvatarUpdateController {

    private final AvatarService avatarService;

    public AvatarUpdateController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/users/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(avatarService.updateAvatar(file));
    }
}
