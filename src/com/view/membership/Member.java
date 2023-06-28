package com.view.membership;
import com.config.cConfig;
import java.util.Scanner;

public class Member extends Penyewa {
    
    Scanner input = new Scanner(System.in);

    public int checkMembership() {

        System.out.print("Nama : ");
        nama = input.nextLine();
        System.out.print("No Telepon : ");
        noTelp = input.nextLine();

        if (cConfig.checkMembershipHandler(nama, noTelp) == 0) {
            daftarMember(nama, noTelp);
        } 
        return cConfig.checkMembershipHandler(nama, noTelp);
    }
}