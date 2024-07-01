package com.example.finalpab;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("jk")
    private String jk;

    @SerializedName("agama")
    private String agama;

    // Konstruktor untuk membuat objek User baru
    public User(int id, String nik, String nama, String alamat, String jk, String agama) {
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.agama = agama;
    }

    // Konstruktor untuk membuat objek User tanpa id (misalnya, untuk menambahkan user baru)
    public User(String nik, String nama, String alamat, String jk, String agama) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.agama = agama;
    }

    // Getter untuk mendapatkan id user
    public int getId() {
        return id;
    }

    // Setter untuk mengatur id user
    public void setId(int id) {
        this.id = id;
    }

    // Getter untuk mendapatkan nik user
    public String getNik() {
        return nik;
    }

    // Setter untuk mengatur nik user
    public void setNik(String name) {
        this.nik = nik;
    }

    // Getter untuk mendapatkan nama user
    public String getNama() {
        return nama;
    }

    // Setter untuk mengatur nama user
    public void setNama(String nama) {this.nama = nama;}

    // Getter untuk mendapatkan alamat user
    public String getAlamat() {
        return alamat;
    }

    // Setter untuk mengatur alamat user
    public void setAlamat(String alamat) {this.alamat = alamat;}

    // Getter untuk mendapatkan jenis kelamin user
    public String getJk() {
        return jk;
    }

    // Setter untuk mengatur jenis kelamin user
    public void setJk(String jk) {this.jk = jk;}

    // Getter untuk mendapatkan agama user
    public String getAgama() {
        return agama;
    }

    // Setter untuk mengatur agama user
    public void setAgama(String agama) {this.agama = agama;}
}
