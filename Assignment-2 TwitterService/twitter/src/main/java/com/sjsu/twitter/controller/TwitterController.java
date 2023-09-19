package com.sjsu.twitter.controller;

/*
 * author: Sri Anudeep Velicheti
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.twitter.service.TwitterService;

import twitter4j.BooleanResponse;
import twitter4j.CreateTweetResponse;
import twitter4j.Status;
import twitter4j.TweetsResponse;
import twitter4j.TwitterResponse;
@RestController
public class TwitterController {

	@Autowired
	private TwitterService twitterService;

	@PostMapping("/create")
	public ResponseEntity<TwitterResponse> createTweet(@RequestBody String tweetText) throws Exception {
		TwitterResponse response=null;
		if(tweetText!="")
		{	
			try
			{
				response=twitterService.createTweet(tweetText);
				return new ResponseEntity<TwitterResponse>(response,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<TwitterResponse>(HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			return new ResponseEntity<TwitterResponse>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{tweetId}")
	public ResponseEntity<BooleanResponse> deleteTweet(@PathVariable long tweetId) throws Exception {
		if(tweetId!=0L)
		{
		try {
			return new ResponseEntity<BooleanResponse>(twitterService.deleteTweet(tweetId),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<BooleanResponse>(HttpStatus.BAD_REQUEST);
		}
		}
		else
		{
			return new ResponseEntity<BooleanResponse>(HttpStatus.BAD_REQUEST);
		}
	}

	public TwitterController(TwitterService twitterService) {
		super();
		this.twitterService = twitterService;
	}
	
	
}
