package by.ilka.TestTaskForDevadminCrawler.service;


import by.ilka.TestTaskForDevadminCrawler.exception.ServiceException;
import by.ilka.TestTaskForDevadminCrawler.exception.UtilException;
import by.ilka.TestTaskForDevadminCrawler.util.ImageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Here could be your advertisement +375 29 3880490
 */

@Service
public class WikipediaPageService {
    private static final Logger logger = LogManager.getLogger(WikipediaPageService.class);

    private static final String ARTICLE_TITLE_ID = "firstHeading";

    @Autowired
    private ImageUtil imageUtil;

    public String findWikiArticleTitle(String wikiUrl) throws ServiceException {
        Document document = null;
        try {
            document = Jsoup.connect(wikiUrl).get();
        } catch (IOException e) {
            throw new ServiceException("Can not connect to: " + wikiUrl, e);
        }
        Element titleElement;
        try {
            titleElement = document.getElementById(ARTICLE_TITLE_ID);
        } catch (NullPointerException e) {
            throw new ServiceException("Can not find " + ARTICLE_TITLE_ID, e);
        }
        return titleElement.text();
    }

    public byte[] loadImage(String wikiUrl) throws ServiceException {
        byte[] byteImage = null;
        String imageUrl = null;
        try {
            imageUrl = "https:" + findArticleImageUrl(wikiUrl);
        } catch (ServiceException e) {
            if ("There is no image".equals(e.getMessage())) {
                logger.warn("There is no image on the page!");
                return new byte[1];
            } else {
                throw new ServiceException("Can not load image", e);
            }
        }
        logger.info("image url - " + imageUrl);
        try {
            BufferedImage img = ImageIO.read(new URL(imageUrl));
            try {
                byteImage = imageUtil.convertBufferedImageToByte(img);
                return byteImage;
            } catch (UtilException e) {
                byteImage = new byte[1];
                logger.error(e);
            }
        } catch (IOException e) {
            throw new ServiceException("Can not load image", e);
        }
        return byteImage;
    }

    private String findArticleImageUrl(String articleUrl) throws ServiceException {
        Document document = null;
        try {
            document = Jsoup.connect(articleUrl).get();
        } catch (IOException e) {
            throw new ServiceException("Can not connect to: " + articleUrl, e);
        }
        try {
            return document.getElementsByClass("image").first().children().get(0).attributes().get("src");
        } catch (Exception e) {
            throw new ServiceException("There is no image");
        }
    }
}
