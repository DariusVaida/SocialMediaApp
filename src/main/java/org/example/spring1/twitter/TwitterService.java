package org.example.spring1.twitter;

import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterService {

    private Twitter twitter;

    public TwitterService() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("ULjq1tRyPyxGYfXrswHRpHAYU")
            .setOAuthConsumerSecret("FJgKa9qN15MiDCj6UxPPrUKGlT8DNOW6ry5cuIzahU023wCT15")
            .setOAuthAccessToken("1796542368625168384-V8FtGtj3Bw2CdjnQ8V2ZgIuYwmJVIB")
            .setOAuthAccessTokenSecret("RE8EOU0PR4qeIW1Iicw3nVzeGx4CTF2MbUhorfKGgj8g5");

        System.out.println("TwitterService constructor");
        System.out.println("cb: " + cb);
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }

    public String postTweet(String message) {
        try {
            Status status = twitter.updateStatus(message);

            return status.getText();
        } catch (TwitterException e) {
            e.printStackTrace();
            return "Failed to post tweet";
        }
    }
}
