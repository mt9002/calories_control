package calories_control.features.shared;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import calories_control.features.auth.infra.AvatarModel;
import calories_control.features.auth.infra.AvatarRapository;
import calories_control.features.auth.infra.UserModel;
import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalModelAttributes {

    private static final Logger logger = LoggerFactory.getLogger(GlobalModelAttributes.class);

    private final AvatarRapository avatarRepository;

    public GlobalModelAttributes(AvatarRapository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        UserModel user = SecurityContextUtil.getUser();
        if (user != null) {
            model.addAttribute("userName", user.getName());
            Optional<AvatarModel> avatarOpt = avatarRepository.getAvatar(user.getId());
            if (avatarOpt.isPresent()) {
                logger.info("Retrieved avatar URL for user {}: {}", user.getId(), avatarOpt.get().getAvatarUrl());
            } else {
                logger.info("No avatar found for user {}", user.getId());
            }
            model.addAttribute("avatarUrl", avatarOpt.map(AvatarModel::getAvatarUrl).orElse(null));
        }
    }
}