package tournament.main;

import java.util.*;
import tournament.models.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> pemain = new ArrayList<>();

        while (true) {
            pemain.clear();
            for (int i = 1; i <= 4; i++) {
                System.out.print("Masukkan nama pemain ke-" + i + ": ");
                String nama = scanner.nextLine();

                System.out.print("Apakah anda punya kode unik? (Y/N): ");
                String jawaban = scanner.nextLine().trim().toUpperCase();

                boolean isVIP = false;
                if (jawaban.equals("Y")) {
                    System.out.print("Masukkan kode unik: ");
                    String kode = scanner.nextLine();
                    if (kode.equals("VIP123")) {
                        isVIP = true;
                        System.out.println("=== Akses VIP diterima! ===");
                    } else {
                        System.out.println("=== Kode salah. Lanjut sebagai pemain biasa. ===");
                    }
                }
                pemain.add(new Player(nama, isVIP));
            }

            Collections.shuffle(pemain);
            Player semi1p1 = pemain.get(0);
            Player semi1p2 = pemain.get(1);
            Player semi2p1 = pemain.get(2);
            Player semi2p2 = pemain.get(3);

            System.out.println("\n--- PASANGAN SEMIFINAL ---");
            System.out.println("Match 1: " + semi1p1.getNama() + " vs " + semi1p2.getNama());
            System.out.println("Match 2: " + semi2p1.getNama() + " vs " + semi2p2.getNama());

            System.out.println("\n--- SEMIFINAL ---");
            Player finalis1 = Match.mainkanMatch(semi1p1, semi1p2);
            Player finalis2 = Match.mainkanMatch(semi2p1, semi2p2);

            System.out.println("\n--- PASANGAN FINAL ---");
            System.out.println(finalis1.getNama() + " vs " + finalis2.getNama());

            System.out.println("\n--- FINAL ---");
            Player juara = Match.mainkanMatch(finalis1, finalis2);

            System.out.println("\n===  JUARA TURNAMEN: " + juara.getNama() + " ===\n");

            Turnamen.tampilkanSkor(pemain);
            Turnamen.tampilkanStatistik(Match.getStatistik());

            System.out.print("\nApakah ingin bermain lagi? (Y/N): ");
            String ulang = scanner.nextLine().trim().toUpperCase();
            if (!ulang.equals("Y")) break;
        }

        System.out.println("\nTerima kasih telah bermain!");
    }
}