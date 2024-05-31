package org.example.spring1.twiter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/api/twitter")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @PostMapping("/post")
    public String postTweet(@RequestParam("message") String message) {
        try {
            System.out.println("message: " + message);
            twitterService.postTweet(message);
            return "Tweet posted successfully!";
        } catch (TwitterException e) {
            return "Error posting tweet: " + e.getMessage();
        }
    }
}
