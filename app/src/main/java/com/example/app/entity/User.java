package com.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "HASTALAR")
public class User extends BaseEntity {

    @Id
    @SequenceGenerator(name = "user_seq_sen" , sequenceName = "user_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_seq_sen")
    @Column(name = "Dosya NO ")
    private Long id;
    @Column(name = "Hasta Ad Soyad", length = 100)
    private String fullName;
    @Column(name="TC", length = 11)
    private String TC;
    @Column(name = "Tanı Başlığı", length = 200)
    private String diagnosis;
    @Column(name = "Tanı Detayı",length = 1000)
    private String detail;
    @Column(name="Laborant Ad Soyad", length=100)
    private String assistant;

}
