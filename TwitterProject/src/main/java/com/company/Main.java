package com.company;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class Main {

    public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("JiJsMbG6lSJSbBQHlIIzvA0Yj")
                .setOAuthConsumerSecret("aLFHDzhhEJdKP8oKSqOpDJlaVku3fRRJqhVp0GCfWnMss3aYSE")
                .setOAuthAccessToken("1106547266456879104-TL9trHC8Ap7ZbJAv9ygEwexcoDHyZR")
                .setOAuthAccessTokenSecret("fLs7MFpGB2fUPlLROo1TyC5whJNNiTrKJ3VDxnPs1pMLD");

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();

       /*
       //get tweets of my account
       List<Status> status = twitter.getHomeTimeline();

        for (Status s:status)
        {
            System.out.println(s.getUser().getName()+ "     "+ s.getText());
        }

        //get tweets of specific user
        List<Status> statuses = twitter.getUserTimeline("SophiaH_espc");

        for (Status status : statuses) {
            String fmt = "@" + status.getUser().getScreenName() + " - " + status.getText();
            System.out.println(fmt);
        }


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
        */

        // The factory instance is re-useable and thread safe.


        Query query = new Query("Ahmed___Gamal__ in");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }

}

