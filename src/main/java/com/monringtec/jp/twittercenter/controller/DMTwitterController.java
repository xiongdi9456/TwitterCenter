package com.monringtec.jp.twittercenter.controller;

import com.monringtec.jp.twittercenter.config.Twitter4jConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mtwitter")
public class DMTwitterController {

    private Twitter twitterInstance;
    private List<Long> customerIdList;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){


        return "dm/index";
    }

    @RequestMapping(value="/keys",method = RequestMethod.POST)
    @ResponseBody
    public String setKey(@RequestParam(value = "ckey", required = true) String customerKey,
                         @RequestParam(value = "csec", required = true) String customerSecret,
                         @RequestParam(value = "akey", required = true) String authKey,
                         @RequestParam(value = "asec", required = true) String authSecret){

        System.out.println("hello post keys:"+customerKey);
        Twitter4jConfig twconfig = new Twitter4jConfig(customerKey,customerSecret,authKey,authSecret);
        TwitterFactory tf = twconfig.twitterFactory();
        twitterInstance = twconfig.twitter(tf);

        return "dm/keys";
    }

    @RequestMapping("/listfollowers")
    @ResponseBody
    public String listFollowers(){

        customerIdList = new ArrayList<Long>();
        try{
            //Twitter twitter = new TwitterFactory().getInstance();
            long cursor = -1;
            IDs ids;
            System.out.println("Listing followers's ids.");
            do {
                ids = twitterInstance.getFollowersIDs("gakuen_senki",cursor);
                for (long id : ids.getIDs()) {
                    System.out.println(id);
                    customerIdList.add(id);
                }
            } while ((cursor = ids.getNextCursor()) != 0);

        }
        catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get followers' ids: " + te.getMessage());
            System.exit(-1);
        }

        return customerIdList.toString();
        //return "dm/listfollowers";
    }

    @RequestMapping("/dm")
    public String dm(){

        return "dm/dm";
    }








}
