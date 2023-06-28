package com.view.waktu;

public class Tahunan implements IntervalWaktu {
    
    private static double diskon = 0.2;
    
    public static double getHargaSewa(int durasi, double harga) {
        double hargaSewa = ((harga * 12) * durasi) - ((harga * 12) * diskon) * durasi;
        return hargaSewa;
    }
}
