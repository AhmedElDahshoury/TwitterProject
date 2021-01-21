package com.company;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws TwitterException, IOException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("JiJsMbG6lSJSbBQHlIIzvA0Yj")
                .setOAuthConsumerSecret("aLFHDzhhEJdKP8oKSqOpDJlaVku3fRRJqhVp0GCfWnMss3aYSE")
                .setOAuthAccessToken("1106547266456879104-TL9trHC8Ap7ZbJAv9ygEwexcoDHyZR")
                .setOAuthAccessTokenSecret("fLs7MFpGB2fUPlLROo1TyC5whJNNiTrKJ3VDxnPs1pMLD");

        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
///*
        String tweet;

        ArrayList<String> stopwords= new ArrayList<String>();
        BufferedReader stop = new BufferedReader(new FileReader("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\stopwords.txt"));
        String line = "";
        while ((line = stop.readLine()) != null)
        {
            stopwords.add(line);
        }

        Map<String, String> map = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\Weights"));

        int totalScore = 0;
        int k = 0;
        line="";
        while ((line = in.readLine()) != null) {
            String parts[] = line.split("\t");

            map.put(parts[0], parts[1] );
            totalScore += Integer.parseInt(parts[1]);
            k++;
            //System.out.println( k + " : " +totalScore);
        }
        in.close();
        //System.out.println("\nMap: " + map.toString());
        //System.out.println("\n" + "Total wights score: " + totalScore);
        Scanner inputStream= new Scanner(new FileReader("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\TestTweets.csv"));

        Paging pg = new Paging();
        String s = "";
        FileWriter fr = new FileWriter("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\TestTweets.csv");

        int lks = 0, rtws = 0;
        int positiveTweetsCount = 0;
        int negativeTweetsCount = 0;
        int neutralTweetsCount =0;
        int positiveRetweetsCount = 0;
        int positiveLikesCount = 0;
        int negativeRetweetsCount = 0;
        int negativeLikesCount = 0;
        int neutralLikesCount = 0;
        int neutralRetweetsCount = 0;
        int tweetsWithKeyWords = 0;

        String userName = "HuffPostRelig";
        int numberOfTweets = 1000;
        long lastID = Long.MAX_VALUE;
        ArrayList<Status> tweets = new ArrayList<Status>();
        int count = 0;
        while (tweets.size () < numberOfTweets) {
            try {
                tweets.addAll(twitter.getUserTimeline(userName,pg));
                for (Status t: tweets) {
                    count++;
                    s = s + t.getText() + "\n";
                    rtws += t.getRetweetCount();
                    lks += t.getFavoriteCount();

                    String[] word=t.getText().split(" ");
                    int tweetWeight = 0;
                    boolean flag = false;
                    //check each word
                    for(int i=0; i<word.length;i++) {
                        int wordWeight = 0;
                        if (stopwords.contains(word[i].toLowerCase())) {
                            //Ignore words in tweets if these words exist in the stopWords file
                        }
                        else {
                            //if a match is found between keywords and tweets
                            if (map.get(word[i]) != null) {

                                String s1 = map.get(word[i].toLowerCase());
                                tweetWeight = (int) wordWeight + Integer.parseInt(s1);

                                //tweet with keyword found
                                flag = true;
                            }
                        }
                    }

                    if (flag == true){
                        tweetsWithKeyWords++;

                        if (tweetWeight > 0){
                            positiveTweetsCount ++;

                            positiveRetweetsCount += t.getRetweetCount();
                            positiveLikesCount += t.getFavoriteCount();
                        }
                        else if (tweetWeight < 0){
                            negativeTweetsCount++;

                            negativeRetweetsCount += t.getRetweetCount();
                            negativeLikesCount += t.getFavoriteCount();
                        }
                        else {
                            neutralTweetsCount++;

                            neutralRetweetsCount += t.getRetweetCount();
                            neutralLikesCount += t.getRetweetCount();
                        }
                    }

                    if (t.getId() < lastID) lastID = t.getId();
                    count++;
                }
            }
        catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            };
            pg.setMaxId(lastID-1);
        }

        fr.write(s);
        fr.close();

        ///*
        System.out.println("Total retweets: " + rtws);
        System.out.println("Total Likes: " + lks);

        System.out.println("Tweets with keywords: " + tweetsWithKeyWords);
        System.out.println("positive Tweets Count: " + positiveTweetsCount);
        System.out.println("negative Tweets Count: " + negativeTweetsCount);
        System.out.println("neutral Tweets Count: " + neutralTweetsCount);
        System.out.println("positive retweets Count: " + positiveRetweetsCount);
        System.out.println("positive likes Count: " + positiveLikesCount);
        System.out.println("negative retweets Count: " + negativeRetweetsCount);
        System.out.println("negative likes Count: " + negativeLikesCount);
        System.out.println("neutral Tweets Count: " + neutralTweetsCount);
        System.out.println("neutral Retweets Count: " + neutralRetweetsCount);
        System.out.println("neutral likes Count: " + neutralLikesCount);
        System.out.println("Total number of tweets: " + count);
//*/

    }

}

