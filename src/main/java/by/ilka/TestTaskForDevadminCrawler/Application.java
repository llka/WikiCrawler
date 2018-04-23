package by.ilka.TestTaskForDevadminCrawler;

import by.ilka.TestTaskForDevadminCrawler.entity.ArticleInfo;
import by.ilka.TestTaskForDevadminCrawler.exception.ServiceException;
import by.ilka.TestTaskForDevadminCrawler.logic.WikipediaArticleLogic;
import by.ilka.TestTaskForDevadminCrawler.service.WikipediaPageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private WikipediaPageService wikipediaPageService;

    @Autowired
    private WikipediaArticleLogic wikipediaArticleLogic;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {
        if (strings.length > 0) {
            ArrayList<ArticleInfo> articles = new ArrayList<>(strings.length);
            for (int i = 0; i < strings.length; i++) {
                try {
                    String title = wikipediaPageService.findWikiArticleTitle(strings[i]);
                    logger.info("Url - " + strings[i] + ", Title - " + title);
                    //articles.add(new ArticleInfo(title, strings[i], new byte[]{}));
                    articles.add(new ArticleInfo(title, strings[i], wikipediaPageService.loadImage(strings[i])));
                } catch (ServiceException e) {
                    logger.error(e);
                }
            }
            wikipediaArticleLogic.saveAll(articles);
        } else {
            logger.error("No arguments where passed");
        }
    }
}
