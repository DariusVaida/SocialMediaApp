package org.example.spring1.config.properties;

import jakarta.persistence.Access;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import twitter4j.auth.AccessToken;

@ConfigurationProperties(prefix = "ext")
@Data
public class TwitterApiProperties {


    private String twitterConsumerKey;
    private String twitterConsumerSecret;

}
