import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.TwitterException
import twitter4j.Status
import twitter4j.auth.AccessToken
import twitter4j.IDs

/**
 * 自動フォロー返しを行います
 */
class ReFollowBot {

  def main(args: Array[String]): Unit = {

    val consumerKey = "CONSUMER_KEY";
    val consumerSecret = "CONSUMER_SECRET";
    val accessToken = "ACCESS_TOKEN";
    val accessTokenSecret = "ACCESS_TOKEN_SECRET";

    val twitter: Twitter = new TwitterFactory().getInstance();
    twitter.setOAuthConsumer(consumerKey, consumerSecret);
    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

    val friends = twitter.getFriendsIDs(twitter.getId(), -1).getIDs();
    val followers = twitter.getFollowersIDs(twitter.getId(), -1).getIDs();

    followers.filter(!friends.contains(_)).foreach(twitter.createFriendship(_));

  }
}