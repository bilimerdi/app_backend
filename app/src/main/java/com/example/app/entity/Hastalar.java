package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hastalar extends BaseEntity {
    @Id
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
    @Column(name = "Resim")
    private byte[] Image ;

}
