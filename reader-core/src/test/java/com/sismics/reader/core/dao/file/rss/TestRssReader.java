package com.sismics.reader.core.dao.file.rss;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.sismics.reader.core.model.jpa.Article;
import com.sismics.reader.core.model.jpa.Feed;

/**
 * Test of the RSS reader.
 * 
 * @author jtremeaux
 */
public class TestRssReader {
    @Test
    public void rssReaderKorbenTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_rss2_korben.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("Korben", feed.getTitle());
        Assert.assertEquals("http://korben.info", feed.getUrl());
        Assert.assertEquals("fr-FR", feed.getLanguage());
        Assert.assertEquals("Upgrade your mind", feed.getDescription());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(30, articleList.size());
        
        Article article = articleList.get(0);
        Assert.assertEquals("RetroN 5 – La console pour les nostalgiques de la cartouche", article.getTitle());
        Assert.assertEquals("http://korben.info/retron-5.html", article.getUrl());
        Assert.assertEquals("http://korben.info/?p=38958", article.getGuid());
        Assert.assertEquals("Korben", article.getCreator());
        Assert.assertEquals("http://korben.info/retron-5.html#comments", article.getCommentUrl());
        Assert.assertEquals(Integer.valueOf(4), article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("Hyper"));
        Assert.assertNotNull(article.getPublicationDate());
        Assert.assertNull(article.getEnclosureUrl());
        Assert.assertNull(article.getEnclosureLength());
        Assert.assertNull(article.getEnclosureType());
        
        article = articleList.get(14);
        Assert.assertEquals("http://media.eurekalert.org/multimedia_prod/pub/media/54033.flv", article.getEnclosureUrl());
        Assert.assertEquals(Integer.valueOf(7172109), article.getEnclosureLength());
        Assert.assertEquals("video/x-flv", article.getEnclosureType());
    }

    @Test
    public void rssReaderXkcdTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_rss2_xkcd.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("xkcd.com", feed.getTitle());
        Assert.assertEquals("http://xkcd.com/", feed.getUrl());
        Assert.assertEquals("en", feed.getLanguage());
        Assert.assertEquals("xkcd.com: A webcomic of romance and math humor.", feed.getDescription());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(4, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("Voyager 1", article.getTitle());
        Assert.assertEquals("http://xkcd.com/1189/", article.getUrl());
        Assert.assertEquals("http://xkcd.com/1189/", article.getGuid());
        Assert.assertNull(article.getCreator());
        Assert.assertNull(article.getCommentUrl());
        Assert.assertNull(article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("So far Voyager 1"));
        Assert.assertNotNull(article.getPublicationDate());
    }

    @Test
    public void atomReaderXkcdTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_atom_xkcd.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("xkcd.com", feed.getTitle());
        Assert.assertEquals("http://xkcd.com/", feed.getUrl());
        Assert.assertNull(feed.getLanguage());
        Assert.assertNull(feed.getDescription());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(4, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("Voyager 1", article.getTitle());
        Assert.assertEquals("http://xkcd.com/1189/", article.getUrl());
        Assert.assertEquals("http://xkcd.com/1189/", article.getGuid());
        Assert.assertNull(article.getCreator());
        Assert.assertNull(article.getCommentUrl());
        Assert.assertNull(article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("So far Voyager 1"));
        Assert.assertNotNull(article.getPublicationDate());
    }

    @Test
    public void rssReaderSpaceTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_rss2_space.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("SPACE.com", feed.getTitle());
        Assert.assertEquals("http://www.space.com/", feed.getUrl());
        Assert.assertEquals("en-us", feed.getLanguage());
        Assert.assertEquals("Something amazing every day.", feed.getDescription());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(50, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("Collision Course? A Comet Heads for Mars", article.getTitle());
        Assert.assertEquals("http://www.space.com/20417-collision-course-a-comet-heads-for-mars.html", article.getUrl());
        Assert.assertNotNull(article.getGuid());
        Assert.assertNull(article.getCreator());
        Assert.assertNull(article.getCommentUrl());
        Assert.assertNull(article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("A recently discovered comet"));
        Assert.assertNotNull(article.getPublicationDate());
    }

    @Test
    public void atomReaderMakikoTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_atom_makiko.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("Makiko Furuichi Blog", feed.getTitle());
        Assert.assertEquals("http://makiko-f.blogspot.com/", feed.getUrl());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(25, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("くいだおれ", article.getTitle());
        Assert.assertEquals("http://makiko-f.blogspot.com/2013/04/blog-post.html", article.getUrl());
        Assert.assertEquals("tag:blogger.com,1999:blog-9184161806327478331.post-186540250833288646", article.getGuid());
        Assert.assertEquals("Makiko Furuichi", article.getCreator());
        Assert.assertTrue(article.getDescription().contains("甘エビやホタルイカ、もちろん新鮮なお魚を始め"));
        Assert.assertNotNull(article.getPublicationDate());
    }
    
    @Test
    public void rssReaderPloumTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_rss2_ploum.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("ploum.net", feed.getTitle());
        Assert.assertEquals("http://ploum.net", feed.getUrl());
        Assert.assertEquals("en-US", feed.getLanguage());
        Assert.assertEquals("Le blog de Lionel Dricot", feed.getDescription());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(10, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("The Disruptive Free Price", article.getTitle());
        Assert.assertEquals("http://ploum.net/post/the-disruptive-free-price", article.getUrl());
        Assert.assertEquals("http://ploum.net/?p=2810", article.getGuid());
        Assert.assertEquals("Lionel Dricot", article.getCreator());
        Assert.assertEquals("http://ploum.net/post/the-disruptive-free-price#comments", article.getCommentUrl());
        Assert.assertEquals(Integer.valueOf(4), article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("During most of my life"));
        Assert.assertNotNull(article.getPublicationDate());
    }

    @Test
    public void rssReaderDeveloperWorksTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_rss2_developerworks.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("IBM developerWorks : Java technology", feed.getTitle());
        Assert.assertEquals("http://www.ibm.com/developerworks/", feed.getUrl());
        Assert.assertEquals("en", feed.getLanguage());
        Assert.assertEquals("The latest content from IBM developerWorks", feed.getDescription());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(100, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("Process real-time big data with Twitter Storm", article.getTitle());
        Assert.assertEquals("http://www.ibm.com/developerworks/opensource/library/os-twitterstorm/index.html?ca=drs-", article.getUrl());
        Assert.assertEquals("84fea3ea30ced7029c0ff7f617c0c8be695f5525", article.getGuid());
        Assert.assertNull(article.getCreator());
        Assert.assertNull(article.getCommentUrl());
        Assert.assertNull(article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("Storm is an open source"));
        Assert.assertNotNull(article.getPublicationDate());
    }
    
    @Test
    public void rssReaderSpaceDailyTest() throws Exception {
        String url = new File(getClass().getResource("/feed/feed_rss2_spacedaily.xml").getFile()).toURI().toString();
        RssReader reader = new RssReader(url);
        reader.readRssFeed();
        Feed feed = reader.getFeed();
        Assert.assertEquals("Space News From SpaceDaily.Com", feed.getTitle());
        Assert.assertEquals("http://www.spacedaily.com/index.html", feed.getUrl());
        Assert.assertEquals("en-us", feed.getLanguage());
        List<Article> articleList = reader.getArticleList();
        Assert.assertEquals(15, articleList.size());
        Article article = articleList.get(0);
        Assert.assertEquals("Collision Course? A Comet Heads for Mars", article.getTitle());
        Assert.assertEquals("http://www.spacedaily.com/reports/Collision_Course_A_Comet_Heads_for_Mars_999.html", article.getUrl());
        Assert.assertEquals("f8eb7749d7983f850be0ca7a7539a47022ff2b54", article.getGuid());
        Assert.assertNull(article.getCreator());
        Assert.assertNull(article.getCommentUrl());
        Assert.assertNull(article.getCommentCount());
        Assert.assertTrue(article.getDescription().contains("Over the years, the spacefaring nations of Earth"));
        Assert.assertNotNull(article.getPublicationDate());
    }
}