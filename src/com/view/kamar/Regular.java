package com.view.kamar;
import com.config.cConfig;
import java.util.ArrayList;

public class Regular extends Kamar {
    
    public Regular() {
        ArrayList<Double> harga = cConfig.getHargaKamarHandler("R");
        hargaPerhari = harga.get(0);
        hargaPerbulan = harga.get(1);
    }

    public void getDataKamar() {
                System.out.println();
        System.out.println("========= Ketersediaan Kamar Regular =========");
        System.out.println(cConfig.getDataKamarHandler("R"));
    };
}
