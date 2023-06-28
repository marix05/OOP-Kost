package com.view.waktu;

public interface IntervalWaktu {
    
    public static double getHargaSewa(int durasi, double harga) {
        return durasi * harga;
    }
}
