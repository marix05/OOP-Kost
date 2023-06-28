package com.view.kamar;
import com.config.cConfig;
import java.util.ArrayList;

public class Deluxe extends Kamar {
    
    public Deluxe() {
        ArrayList<Double> harga = cConfig.getHargaKamarHandler("D");
        hargaPerhari = harga.get(0);
        hargaPerbulan = harga.get(1);
    }

    public void getDataKamar() {
        System.out.println();
        System.out.println("========= Ketersediaan Kamar Deluxe =========");
        System.out.println(cConfig.getDataKamarHandler("D"));
    };
}
