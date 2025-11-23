package calories_control.features.auth.app.avatar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import calories_control.features.auth.infra.AvatarModel;
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

    private static final Logger logger = LoggerFactory.getLogger(UpdateController.class);

    private final AvatarUseCase avatarUseCase;

    public UpdateController(AvatarUseCase avatarUseCase) {
        this.avatarUseCase = avatarUseCase;
    }

    @PostMapping(value = "/users/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateAvatar(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
            throws IOException, java.io.IOException {

        if (!file.getContentType().startsWith("image/")) {
            redirectAttributes.addFlashAttribute("error", "Invalid file type. Only images are allowed.");
            return "redirect:/profile"; // Assuming a profile page
        }
        String url = upload(file);
        logger.info("Updating avatar for userId: {} with URL: {}", SecurityContextUtil.getUserId(), url);
        Long userId = SecurityContextUtil.getUserId();

        Result<AvatarModel> avatar = avatarUseCase.updateAvatar(userId, url);
        if (avatar.getState() == State.SUCCESS) {
            redirectAttributes.addFlashAttribute("success", avatar.getMessage());
        } else {
            redirectAttributes.addFlashAttribute("error", avatar.getMessage());
        }

        return "redirect:/profile";
    }

    public String upload(MultipartFile file) throws IOException, java.io.IOException {
        String filename = UUID.randomUUID() + ".jpg";
        Path uploadPath = Paths.get("src/main/resources/static/avatars/" + filename);
        Files.createDirectories(uploadPath.getParent());
        Files.copy(file.getInputStream(), uploadPath);
        String url = "/avatars/" + filename;
        logger.info("Saved avatar file to: {} with URL: {}", uploadPath.toString(), url);
        return url; // URL pública correcta
    }

}
