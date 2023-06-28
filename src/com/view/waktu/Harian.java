package com.view.waktu;

public class Harian implements IntervalWaktu{
    
    private static double diskon = 0.05;
    
    public static double getHargaSewa(int durasi, double harga) {
        double hargaSewa = harga * durasi;
        if (durasi >= 14) {
            int sisa = durasi%14;
            hargaSewa = (harga * durasi) - (harga * diskon) * durasi-sisa;
        } 
        return hargaSewa;
    }
}
