package com.example.bentou;

public class Onigiri {
    private Kome kome;
    private String guzai;

    public Onigiri(Kome kome, String guzai) {
        this.kome = kome;
        this.guzai = guzai;
    }

    public Kome getKome() {
        return kome;
    }

    public String getGuzai() {
        return guzai;
    }
}
