package com.company;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

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

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();


        //get tweets of specific user
        List<Status> statuses = twitter.getUserTimeline("HuffPostRelig");
        String s = "";
        for (Status status : statuses) {
            s = s + status.getText() + "\n";
            //System.out.println(s);
        }
        FileWriter fr = new FileWriter("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\TestTweets.csv");
        fr.write(s);
        fr.close();
/*
        //get followers of specific user *error, only 20 follower are retrieved*
        PagableResponseList<User> followersList;

        ArrayList<String> list = new ArrayList<String>();

            List<User> friendList = null;
            try   {
                followersList = twitter.getFollowersList("SophiaH_espc", -1);

                for (int i = 0; i < followersList.size(); i++)
                {
                    User user = followersList.get(i);
                    String name = user.getName();
                    list.add(name);
                    System.out.println("Name" + i + ":" + name);
                }

            } catch (IllegalStateException e) {
                e.printStackTrace();

            }
 */
        /*
        IDs followersIds = twitter.getFollowersIDs("ReviewReligions",-1);
        long [] ids = followersIds.getIDs();

        List<User> followers = new ArrayList<User>();
        for(int i = 0; i < 500; i++) {
            followers.add(twitter.showUser(ids[i]));
            System.out.println("Follower " + i + ": " + followers.get(i));
        }


        // The factory instance is re-useable and thread safe.

        Query query = new Query("Ahmed___Gamal__ in");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
*/

        try{
            int count=0;
            String tweet;

            ArrayList<String> stopwords= new ArrayList<String>();
            BufferedReader stop = new BufferedReader(new FileReader("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\stopwords.txt"));
            String line = "";
            while ((line = stop.readLine()) != null)
            {
                stopwords.add(line);
            }

            Map<String, String> map = new HashMap<String, String>();
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\agama\\source\\repos\\TwitterProject\\TwitterProject\\Data\\AFINN"));

            int totalScore = 0;
            line="";
            while ((line = in.readLine()) != null) {
                String parts[] = line.split("\t");

                map.put("\n" + parts[0], parts[1]);
                totalScore += Integer.parseInt(parts[1]);

                count++;
            }
            in.close();
            System.out.println(map.toString());
            System.out.println("\n" + "Total Score = " + totalScore + "\n");

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
                Map<String, Float> sentiment= new HashMap<String, Float>();
                sentiment.put(tweet, tweetscore);
                System.out.println( sentiment.toString());
            }
        }
        catch(FileNotFoundException e)
        {
           e.printStackTrace();

        }
    }

}

