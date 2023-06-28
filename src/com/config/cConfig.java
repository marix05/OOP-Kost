package com.config;
import java.sql.DriverManager;
import java.sql.Connection;
// class untuk mengeksekusi query
import java.sql.Statement;
// class untuk menyimpan hasil read database
import java.sql.ResultSet;
// class array list
import java.util.ArrayList;

public class cConfig {
    
    // Konfigurasi koneksi ke Database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_kost";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    // Instansi object dari class yang sudah diimport
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    // method untuk koneksi ke Database
    public static void connection() {
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        } catch (Exception e) {
            System.out.println("Koneksi ke Database gagal");
        }
    }
    
    public static String getDataKamarHandler(String idTipeKamar) {
        cConfig.connection();

        String data = "Data Tidak Ditemukan";

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "SELECT id_kamar, ketersediaan FROM kamar WHERE id_tipe = '" 
            + idTipeKamar + "' ORDER BY ketersediaan DESC";
            rs = stmt.executeQuery(query);
            
            data = "";

            while (rs.next()) {
                data += "No Kamar : " + rs.getString("id_kamar")
                + "; Status : ";
                if ( rs.getBoolean("ketersediaan")) {
                    data += "Tersedia\n";
                } else {
                    data += "Tidak Tersedia\n";
                }
                
            }
            
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String updateKetersediaanKamarHandler(int idKamar) {
        cConfig.connection();

        String data = "Update Ketersediaan Kamar Gagal. Data Tidak Ditemukan";

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "UPDATE kamar SET ketersediaan = '1' WHERE id_kamar = '" + idKamar + "'";
            
            if (!stmt.execute(query)) {
                data = "Update Ketersediaan Kamar Berhasil";
            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static ArrayList<Double> getHargaKamarHandler(String idTipeKamar) {
        cConfig.connection();
        
        ArrayList<Double> data = new ArrayList<Double>(); 

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "SELECT harga_perhari, harga_perbulan FROM tipe_kamar WHERE id_tipe = '" + idTipeKamar + "'";
            rs = stmt.executeQuery(query);
            

            while (rs.next()) {
                data.add(rs.getDouble("harga_perhari"));
                data.add(rs.getDouble("harga_perbulan"));
            }

            stmt.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }

    public static int checkMembershipHandler(String nama, String noTelp) {
        cConfig.connection();

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "SELECT * FROM penyewa WHERE nama LIKE '" + nama + "' AND no_telp LIKE '" + noTelp + "'";
            rs = stmt.executeQuery(query);
        
            if (rs.next()) {
                return rs.getInt("id_penyewa");
            }

            stmt.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String daftarMember(String nama, String noTelp) {
        cConfig.connection();

        String data = "Gagal menambah member";

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "INSERT INTO penyewa(nama, no_telp) VALUES ('" + nama + "','" + noTelp + "')";
            
            if (!stmt.execute(query)) {
                data = "Berhasil mendaftarkan member";
            }
            
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }

    public static String addPenyewaHandler(int idPenyewa, int idKamar, String tanggalSewa, int durasi, 
    String satuan, double hargaSewa, double jumlahBayar, double kembalian, String tanggalSelesai) {
        cConfig.connection();

        String data = "Gagal menambahkan transaksi penyewaan";

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "INSERT INTO penyewaan(id_penyewa, id_kamar, tanggal_sewa,"
            + " durasi_sewa, satuan_waktu, harga_sewa, jumlah_bayar, kembalian, tanggal_selesai) VALUES "
            + "('" + idPenyewa + "', '" + idKamar + "', '" + tanggalSewa + "', '" + durasi
            + "', '" + satuan + "', '" + hargaSewa + "', '" + jumlahBayar + "', '" + kembalian
            + "', '" + tanggalSelesai + "')";
            
            if (!stmt.execute(query)) {
                String queryUpdate = "UPDATE kamar SET ketersediaan = '0' WHERE id_kamar = '" + idKamar + "'";
                if (!stmt.execute(queryUpdate)) {
                    data = "Transaksi penyewaan berhasil dilakukan";
                }
            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }

    public static String getDataPenyewaan() {
        cConfig.connection();

        String data = "Data Tidak Ditemukan";

        try {
            // object statement yang diambil dari koneksi
            stmt = con.createStatement();

            String query = "SELECT id_kamar, tipe_kamar, nama, no_telp, tanggal_selesai FROM "
            + "penyewaan NATURAL JOIN kamar NATURAL JOIN tipe_kamar NATURAL JOIN penyewa WHERE " 
            + "tanggal_selesai > CURRENT_DATE ORDER BY tanggal_selesai ASC";
            rs = stmt.executeQuery(query);
            
            data = "";

            while (rs.next()) {
                data += "No Kamar : " + rs.getString("id_kamar")
                + "; Tipe Kamar : " + rs.getString("tipe_kamar") 
                + "; Nama : " + rs.getString("nama")
                + "; No Telepon : " + rs.getString("no_telp") 
                + "; Jatuh Tempo : " + rs.getDate("tanggal_selesai") + "\n";
            }
            
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}