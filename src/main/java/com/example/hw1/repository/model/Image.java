package com.example.hw1.repository.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    @SequenceGenerator(name = "image_seq", sequenceName = "image_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "link", nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
