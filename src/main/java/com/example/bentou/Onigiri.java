package com.example.bentou;

public class Onigiri {
    private static String name = "おにぎり";
    private String guzai;

    public Onigiri(String guzai) {
        this.guzai = guzai;
    }

    public String getName() {
        return name;
    }

    public String getGuzai() {
        return guzai;
    }
}
