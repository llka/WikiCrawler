package by.ilka.TestTaskForDevadminCrawler.dao;

import by.ilka.TestTaskForDevadminCrawler.entity.ArticleInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Here could be your advertisement +375 29 3880490
 */
@Repository
public interface ArticleInfoRepository extends CrudRepository<ArticleInfo, Integer> {

    @Override
    Optional<ArticleInfo> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    List<ArticleInfo> findAll();

    @Override
    <S extends ArticleInfo> S save(S s);

    @Override
    <S extends ArticleInfo> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();
}
