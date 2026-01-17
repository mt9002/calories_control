package calories_control.features.auth.app.avatar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import calories_control.features.auth.infra.avatar.AvatarModel;
import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;
import io.jsonwebtoken.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UpdateController {

    private final AvatarService avatarService;

    public UpdateController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/users/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateAvatar(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException, java.io.IOException {
        return avatarService.updateAvatar(file);
    }
}
