package com.sjsu.twitter.service.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sjsu.twitter.service.TwitterService;

import twitter4j.BooleanResponse;
import twitter4j.CreateTweetResponse;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.TwitterV2;
import twitter4j.TwitterV2ExKt;
import twitter4j.conf.ConfigurationBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TwitterServiceImplTest {
    private TwitterServiceImpl twitterService;

    @Mock
    private TwitterFactory twitterFactory;

    @Mock
    private Twitter twitter;

    @Mock
    private TwitterV2 twitterV2;
       
    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        twitterService = new TwitterServiceImpl();     
    }
    
    @Test
    public void testCreateTweet() throws Exception {
    	
    	Mockito.when(twitterFactory.getInstance()).thenReturn(twitter);
    	Mockito.when(TwitterV2ExKt.getV2(twitter)).thenReturn(twitterV2);

        String tweetText = "Test tweet text";
        CreateTweetResponse mockResponse = mock(CreateTweetResponse.class);
        Mockito.when(twitterV2.createTweet(
                null, null, null, null, null, null, null, null, null, null, null, tweetText))
            .thenReturn(mockResponse);

        TwitterResponse response = twitterService.createTweet(tweetText);
        Mockito.verify(twitterV2, times(1)).createTweet(
                null, null, null, null, null, null, null, null, null, null, null, tweetText);
        assertEquals((TwitterResponse)mockResponse, response);
    }

    

    @Test
    public void testDeleteTweet() throws Exception {
    	Mockito.when(twitterFactory.getInstance()).thenReturn(twitter);
    	Mockito.when(TwitterV2ExKt.getV2(twitter)).thenReturn(twitterV2);

        long tweetId = 123456789L;
        BooleanResponse mockResponse = mock(BooleanResponse.class);
        Mockito.when(twitterV2.deleteTweet(tweetId)).thenReturn(mockResponse);

        BooleanResponse response = twitterService.deleteTweet(tweetId);

        Mockito.verify(twitterV2, times(1)).deleteTweet(tweetId);
        assertEquals(mockResponse, response);
    }
}