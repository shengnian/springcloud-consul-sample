package org.shengnian.os;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cheng on 2017/5/12.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ConsulBlogApp {
//    @Autowired
//    private Environment environment;
    @Autowired
    private BlogRepository repository;

    @RequestMapping("/")
    public Object blog() {
        StringBuffer buffer = new StringBuffer();

        this.repository.findAll().forEach(blog -> {
            buffer.append(blog.getId());
            buffer.append("    ");
            buffer.append(blog.getTitle());
            buffer.append("    ");
            buffer.append(blog.getContent());
        });

        return buffer.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulBlogApp.class, args);
    }

}

@Repository
interface BlogRepository extends CrudRepository<Blog, Long> {}

@Entity
class Blog implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
