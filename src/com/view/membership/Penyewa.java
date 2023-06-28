package com.view.membership;
import com.config.cConfig;

public class Penyewa {
    protected String nama;
    protected String noTelp;

    public void daftarMember(String nama, String noTelp) {
        System.out.println(cConfig.daftarMember(nama, noTelp));
    }

    public void getDataPenyewaan() {
        System.out.println();
        System.out.println("======== Data Penyewaan ========");
        System.out.println(cConfig.getDataPenyewaan());
    }
}
