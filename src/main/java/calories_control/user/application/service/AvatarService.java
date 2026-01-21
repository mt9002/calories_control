package calories_control.user.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import calories_control.auth.infra.security.util.SecurityContextUtil;
import calories_control.user.exception.AvatarNotFoundException;
import calories_control.user.exception.AvatarUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import calories_control.auth.domain.IAvatarRepository;
import calories_control.user.infra.avatar.AvatarModel;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarService {

    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private final IAvatarRepository avatarRepository;

    public AvatarService(IAvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public String updateAvatar(MultipartFile file) throws java.io.IOException {
        Long userId = SecurityContextUtil.getUserId();
        validateFile(file);
        String url = upload(file);
        AvatarModel avatar = avatarRepository.getAvatar(userId)
                .orElseThrow(() -> new AvatarNotFoundException("Avatar no encontrado"));
        avatar.setAvatarUrl(url);
        avatarRepository.updateAvatar(avatar);
        return url;
    }

    private String upload(MultipartFile file) {

        try {
            String contentType = file.getContentType();

            if (contentType == null){
                throw new IllegalArgumentException("El archivo no tiene tipo de contenido (contentType)");
            }

            String extension = getExtension(contentType);

            String filename = UUID.randomUUID() + extension;

            Path uploadDir = Paths.get("uploads/avatars");
            Files.createDirectories(uploadDir);

            Path filePath = uploadDir.resolve(filename);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String url = "/avatars/" + filename;

            logger.info("Avatar guardado en {} - URL pública {}", filePath, url);

            return url;

        } catch (IOException e) {
            logger.error("Error al subir el avatar", e);
            throw new AvatarUploadException("No se puede guardar el avatar", e);
        }
    }

    private String getExtension(String contentType) {
        return switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            default -> throw new IllegalArgumentException("Formato de imagen no soportado");
        };
    }

    private void validateFile(MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen");
        }

        // Opcional: tamaño máximo (ej. 2MB)
        long maxSize = 2 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("La imagen no debe superar los 2MB");
        }
    }
}
