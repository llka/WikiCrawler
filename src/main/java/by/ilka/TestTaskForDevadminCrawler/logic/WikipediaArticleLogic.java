package by.ilka.TestTaskForDevadminCrawler.logic;

import by.ilka.TestTaskForDevadminCrawler.dao.ArticleInfoRepository;
import by.ilka.TestTaskForDevadminCrawler.entity.ArticleInfo;
import by.ilka.TestTaskForDevadminCrawler.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Here could be your advertisement +375 29 3880490
 */
@Service
public class WikipediaArticleLogic {
    private static final Logger logger = LogManager.getLogger(WikipediaArticleLogic.class);

    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    public ArticleInfo findById(int id) throws LogicException {
        if (articleInfoRepository.findById(id).isPresent()) {
            return articleInfoRepository.findById(id).get();
        } else {
            throw new LogicException("No article with id = " + id);
        }
    }

    public List<ArticleInfo> findAll() throws LogicException {
        List<ArticleInfo> articles = articleInfoRepository.findAll();
        if (articles.isEmpty()) {
            throw new LogicException("No articles");
        } else {
            return articles;
        }
    }

    public ArticleInfo save(ArticleInfo articleInfo) {
        return articleInfoRepository.save(articleInfo);
    }

    public Iterable<ArticleInfo> saveAll(List<ArticleInfo> articleInfoList) {
        return articleInfoRepository.saveAll(articleInfoList);
    }

    public void deleteById(int id) throws LogicException {
        if (articleInfoRepository.existsById(id)) {
            articleInfoRepository.deleteById(id);
        } else {
            throw new LogicException("No article with id = " + id);
        }
    }

    public void deleteAll() {
        articleInfoRepository.deleteAll();
    }
}
