package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.awt.*;
import java.sql.Time;

@Entity
public class Hastalar {
    @Id
    @Column(name = "Dosya NO ")
    private Long id;
    @Column(name = "Hasta Ad Soyad")
    private String fullName;
    @Column(name="TC")
    private String TC;
    @Column(name = "Tanı Başlığı")
    private String tani;
    @Column(name = "Tanı Detayı")
    private String taniDetay;
    @Column(name = "Oluşturulan Tarih")
    private byte[] Image ;
    @Column(name = "Resim")
    private Time time;
    @Column(name = "Laborant Ad Soyad")
    private String labFullname;

}
