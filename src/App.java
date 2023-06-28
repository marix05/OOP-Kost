import java.util.Scanner;
import com.view.kamar.*;
import com.view.membership.Member;

public class App {
    private static String valUsername = "admin";
    private static String valPassword = "kostjaya";

    public static void main(String[] args) throws Exception {
        
        Deluxe D = new Deluxe();
        Regular R= new Regular();
        Member member = new Member();

        Scanner input = new Scanner(System.in);
        
        System.out.println("======== Selamat Datang ========\n");
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        if (username.equals(valUsername) && password.equals(valPassword)) {
            while(true) {
                System.out.println();
                System.out.print("============= MENU ============= \n"
                + "1. Ketersediaan Kamar\n"
                + "2. Update Ketersediaan Kamar\n"
                + "3. Transaksi Penyewaan\n"
                + "4. Jatuh Tempo Terdekat\n"
                + "0. Exit\n"
                + "Pilih Menu [1, 2, 3, 4, 0] : "
                );
    
                String pilihanMenu = input.nextLine();
    
                if (pilihanMenu.equalsIgnoreCase("0")) {
                    System.out.println("========= Terimakasih! =========");
                    break;
                } 
                
                switch (pilihanMenu) {
                    case "1":
                        System.out.print("1. Deluxe\n"
                        + "2. Regular\n"
                        + "Pilih Tipe [1, 2] : "
                        );
                        String pilihanTipe = input.nextLine();

                        switch (pilihanTipe) {
                            case "1":
                                D.getDataKamar();
                                break;
                            case "2":
                                R.getDataKamar();
                                break;
                            default:
                                System.out.println("Pilihan Salah");
                                break;
                        }
                        break;
                    case "2":
                        D.updateKetersediaanKamar();
                        break;
                    case "3":
                        System.out.println();
                        System.out.println("========= Transaksi Penyewaan ==========");
                        int idPenyewa = member.checkMembership();
                        System.out.print("Pilih Tipe Kamar :\n"
                        + "1. Deluxe\n"
                        + "2. Regular\n"
                        + "Pilih Tipe [1, 2] : "
                        );
                        String tipeKamar = input.nextLine();

                        switch (tipeKamar) {
                            case "1":
                                D.addPenyewa(idPenyewa);
                                break;
                            case "2":
                                R.addPenyewa(idPenyewa);
                                break;
                            default:
                                System.out.println("Pilihan Salah");
                                break;
                        }
                        break;
                    case "4":
                        member.getDataPenyewaan();
                        break;
                    default:
                        System.out.println("Pilihan Salah");
                        break;
                }
            }
        } else {
            System.out.println("Maaf Username dan Password Salah");
        }
        input.close();
    }
}
