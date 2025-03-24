package com.sos_fauna.denuncias.Services;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

@Service
public class ImageAcessService {
    @Autowired
    private Cloudinary cloudinary;

    public String generateSignedUrl(String publicId) {
        try {
            Map options = ObjectUtils.asMap(
                    "resource_type", "image",
                    "type", "upload",
                    "sign_url", false,
                    "expires_at", System.currentTimeMillis() / 1000 + 3600 // Expira em 1 hora
            );
            String signedUrl = cloudinary.url().signed(true).generate(publicId);
            System.out.println("URL assinada gerada: " + signedUrl);
            return signedUrl;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL assinada", e);
        }
    }
}
