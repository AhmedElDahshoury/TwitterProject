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
/*
        Paging pg = new Paging();
        String s = "";
        FileWriter fr = new FileWriter("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\TestTweets.csv");

        int lks = 0, rtws = 0;

        String userName = "HuffPostRelig";
        int numberOfTweets = 1000;
        long lastID = Long.MAX_VALUE;
        ArrayList<Status> tweets = new ArrayList<Status>();
        int count = 0;
        while (tweets.size () < numberOfTweets) {
            try {
                tweets.addAll(twitter.getUserTimeline(userName,pg));
                for (Status t: tweets) {
                    s = s + t.getText() + "\n";
                    rtws += t.getRetweetCount();
                    lks += t.getFavoriteCount();

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

        System.out.println("Total retweets: " + rtws);
        System.out.println("Total Likes: " + lks);
        //System.out.println(s);
*/
        try{
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
                    System.out.println( k + " : " +totalScore);
            }
            in.close();
            //System.out.println("\nMap: " + map.toString());
            //System.out.println("\n" + "Total wights score: " + totalScore);
            Scanner inputStream= new Scanner(new FileReader("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\TestTweets.csv"));
            while(inputStream.hasNextLine())
            {
                float tweetscore=0;
                tweet= inputStream.nextLine();
                String[] word=tweet.split(" ");

                for(int i=0; i<word.length;i++)
                {
                    if(stopwords.contains(word[i].toLowerCase()))
                    {

                    }
                    else{
                        if(map.get(word[i])!=null)
                        {
                            String wordscore= map.get(word[i].toLowerCase());
                            tweetscore=(float) tweetscore + Integer.parseInt(wordscore);

                        }
                    }
                }
                //System.out.println("\n" + "Total Score = " + tweetscore + "\n");

                Map<String, Float> sentiment= new HashMap<String, Float>();
                sentiment.put(tweet, tweetscore);
                //System.out.println( sentiment.toString());
            }
        }
        catch(FileNotFoundException e)
        {
           e.printStackTrace();
        }
    }

}

