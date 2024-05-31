package org.example.spring1.twiter;

import org.example.spring1.config.properties.TwitterApiProperties;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public class TwitterService {

    private Twitter twitter;
    private TwitterApiProperties twitterApiProperties;


    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
//        twitter.setOAuthConsumer(twitterApiProperties.getTwitterConsumerKey(), twitterApiProperties.getTwitterConsumerSecret());
//        AccessToken accessToken = new AccessToken();
//        twitter.setOAuthAccessToken(twitterApiProperties.getTwitterAccessToken());
    }

    public void postTweet(String message) throws TwitterException {
        twitter.updateStatus(message);
    }
}
