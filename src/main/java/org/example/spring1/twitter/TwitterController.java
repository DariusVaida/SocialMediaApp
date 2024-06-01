package org.example.spring1.twitter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/twitter")
public class TwitterController {

    private final TwitterService twitterService;

    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @PostMapping("/tweet")
    public String postTweet(@RequestBody String message) {
        return twitterService.postTweet(message);
    }
}
