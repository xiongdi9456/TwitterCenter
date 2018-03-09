package com.monringtec.jp.twittercenter.config;

import org.springframework.context.annotation.Bean;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4jConfig {

    /**
     * OAuth consumer key.
     */
    private String consumerKey;

    /**
     * OAuth consumer secret.
     */
    private String consumerSecret;

    /**
     * OAuth access token.
     */
    private String accessToken;

    /**
     * OAuth access token secret.
     */
    private String accessTokenSecret;


    public Twitter4jConfig(String consumerKey,String consumerSecret,String accessToken,String accessTokenSecret){

        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;

    }


    @Bean
    public TwitterFactory twitterFactory(){

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(false)
                .setOAuthConsumerKey(this.consumerKey)
                .setOAuthConsumerSecret(this.consumerSecret)
                .setOAuthAccessToken(this.accessToken)
                .setOAuthAccessTokenSecret(this.accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf;
    }

    @Bean
    public Twitter twitter(TwitterFactory twitterFactory){
        return twitterFactory.getInstance();
    }

}
