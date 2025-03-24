package com.sos_fauna.denuncias.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dhhpcutqf",
                "api_key", "874563538133274",
                "api_secret", "c0Y7Aqmv4LRk_CehOShzcIqZhFo",
                "secure", true
        ));
    }
}
