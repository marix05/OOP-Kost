package com.view.waktu;

public class Bulanan implements IntervalWaktu{
    
    private static double diskon = 0.1;
    
    public static double getHargaSewa(int durasi, double harga) {
        double hargaSewa = harga * durasi;
        if (durasi >= 3) {
            int sisa = durasi%3;
            hargaSewa = (harga * durasi) - (harga * diskon) * durasi-sisa;
        } 
        return hargaSewa;
    }
}