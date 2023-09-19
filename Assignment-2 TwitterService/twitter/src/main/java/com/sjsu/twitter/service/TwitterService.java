package com.sjsu.twitter.service;
/*
 * author: Sri Anudeep Velicheti
 */
import org.springframework.stereotype.Service;

import twitter4j.BooleanResponse;
import twitter4j.Status;
import twitter4j.TweetsResponse;
import twitter4j.TwitterResponse;

@Service
public interface TwitterService {

	public TwitterResponse createTweet(String tweetText) throws Exception;
	
	public TweetsResponse retrieveTweet(long tweetId) throws Exception;
	
	public BooleanResponse deleteTweet(long tweetId) throws Exception;
}
