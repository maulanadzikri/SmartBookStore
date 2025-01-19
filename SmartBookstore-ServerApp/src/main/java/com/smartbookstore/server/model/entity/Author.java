package com.smartbookstore.server.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "author")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Book> books;
}