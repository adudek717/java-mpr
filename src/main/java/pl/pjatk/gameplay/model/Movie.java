package pl.pjatk.gameplay.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private Long id;
    @NotNull
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Movie(){}

    public Movie(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
    public Movie(Long id, String title, String description, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
