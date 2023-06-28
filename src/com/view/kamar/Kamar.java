package com.view.kamar;
import com.view.waktu.*;
import java.util.Scanner;
import com.config.cConfig;
import java.time.LocalDate;

abstract public class Kamar{
    
    protected double hargaPerhari;
    protected double hargaPerbulan;
    
    Scanner input = new Scanner(System.in);

    abstract public void getDataKamar();
    
    public void updateKetersediaanKamar() {
        System.out.print("Id Kamar yang ingin di Update: ");
        int idKamar = input.nextInt();

        System.out.println(cConfig.updateKetersediaanKamarHandler(idKamar)); 
    };

    public void addPenyewa(int idPenyewa) {
        LocalDate tanggalSewa = LocalDate.now();
        LocalDate tanggalSelesai = tanggalSewa;
        double hargaSewa = 0;
        
        System.out.print("No Kamar : ");
        int idKamar = input.nextInt();
        System.out.print("Durasi Sewa : ");
        int durasi = input.nextInt();
        
        System.out.print("Satuan Waktu \n"
        + "1. Hari\n"
        + "2. Bulan\n"
        + "3. Tahun\n"
        + "Pilih Satuan [1, 2, 3] : "
        );
        
        String satuan = input.next();
        
        switch (satuan) {
            case "1":
                satuan = "Hari";
                tanggalSelesai = tanggalSewa.plusDays(durasi);
                hargaSewa = Harian.getHargaSewa(durasi, hargaPerhari);
                break;
            case "2":
                satuan = "Bulan";
                tanggalSelesai = tanggalSewa.plusMonths(durasi);
                hargaSewa = Bulanan.getHargaSewa(durasi, hargaPerbulan);
                break;
            case "3":
                satuan = "Tahun";
                tanggalSelesai = tanggalSewa.plusYears(durasi);
                hargaSewa = Tahunan.getHargaSewa(durasi, hargaPerbulan);
                break;
            default: 
                System.out.println("Pilihan Salah");
                break;
        }

        System.out.println("Tanggal Sewa : " + tanggalSewa);
        System.out.println("Tanggal Selesai : " + tanggalSelesai);
        System.out.println("Harga Sewa : Rp " + hargaSewa);
        System.out.print("Jumlah Bayar: Rp ");
        double jumlahBayar = input.nextDouble();
        while (jumlahBayar < hargaSewa) {
            System.out.print("Ulangi pengimputan Jumlah Bayar : Rp ");
            jumlahBayar = input.nextDouble();
        }
        double kembalian = jumlahBayar - hargaSewa;
        System.out.print("Kembalian : Rp " + kembalian + "\n");

        System.out.println(cConfig.addPenyewaHandler(idPenyewa, idKamar, tanggalSewa.toString(), 
        durasi, satuan, hargaSewa, jumlahBayar, kembalian, tanggalSelesai.toString()));
        System.out.println();
    };
}
