package com.sjsu.twitter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sjsu.twitter.service.TwitterService;

import twitter4j.BooleanResponse;
import twitter4j.CreateTweetResponse;
import twitter4j.Status;
import twitter4j.TweetsResponse;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.TwitterV2;
import twitter4j.TwitterV2ExKt;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterServiceImpl implements TwitterService{

	 	@Value("${twitter.api.key}")
	    private String apiKey;

	    @Value("${twitter.api.secret-key}")
	    private String apiSecretKey;

	    @Value("${twitter.access.token}")
	    private String accessToken;

	    @Value("${twitter.access.token-secret}")
	    private String accessTokenSecret;

	    @Override
	    public TwitterResponse createTweet(String tweetText) throws Exception {
	        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	        configurationBuilder.setOAuthConsumerKey(apiKey)
	            .setOAuthConsumerSecret(apiSecretKey)
	            .setOAuthAccessToken(accessToken)
	            .setOAuthAccessTokenSecret(accessTokenSecret);

	        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
	        TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
	        TwitterResponse resp=v2.createTweet(null, null, null, null, null, null, null, null, null, null, null, tweetText);
	        System.out.println(resp.toString());
	        return resp;
	    }

	    @Override
	    public TweetsResponse retrieveTweet(long tweetId) throws Exception {
	        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	        configurationBuilder.setOAuthConsumerKey(apiKey)
	            .setOAuthConsumerSecret(apiSecretKey)
	            .setOAuthAccessToken(accessToken)
	            .setOAuthAccessTokenSecret(accessTokenSecret);

	        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
	        TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
	        long[] arr=new long[1];
	        arr[0]=tweetId;
	        return v2.getTweets(arr, null, null, null, null, null, null);
	    }

	    @Override
	    public BooleanResponse deleteTweet(long tweetId) throws Exception {
	        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	        configurationBuilder.setOAuthConsumerKey(apiKey)
	            .setOAuthConsumerSecret(apiSecretKey)
	            .setOAuthAccessToken(accessToken)
	            .setOAuthAccessTokenSecret(accessTokenSecret);

	        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
	        TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
	        BooleanResponse resp=v2.deleteTweet(tweetId);
	        return resp;
	    }
	    
	    

}
