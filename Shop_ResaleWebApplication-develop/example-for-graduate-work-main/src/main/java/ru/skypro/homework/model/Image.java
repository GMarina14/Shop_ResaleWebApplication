package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @SequenceGenerator(name = "imageSequence", sequenceName = "image_sequence", allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSequence")
    @Column(name = "id")
    private Integer id;

  //  @Lob
    @Column(name = "image")
    private byte[] image;

/*    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private User user;*/


}
