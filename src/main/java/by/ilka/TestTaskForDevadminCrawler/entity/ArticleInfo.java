package by.ilka.TestTaskForDevadminCrawler.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

/**
 * Here could be your advertisement +375 29 3880490
 */

@Entity
@Table(name = "ArticleInfo")
public class ArticleInfo implements IDatabaseEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "URL")
    private String url;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    public ArticleInfo() {
    }

    public ArticleInfo(String title, String url, byte[] image) {
        this.title = title;
        this.url = url;
        this.image = image;
    }

    public ArticleInfo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleInfo that = (ArticleInfo) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url);
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
