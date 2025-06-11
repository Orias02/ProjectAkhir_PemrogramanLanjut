package tournament.models;

import java.util.*;

public class Match {
    private static final String[] pilihan = {"batu", "gunting", "kertas"};
    private static final Map<String, Integer> statistik = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static Player mainkanMatch(Player p1, Player p2) {
        int skor1 = 0, skor2 = 0;
        System.out.println("\n--- Match antara " + p1.getNama() + " vs " + p2.getNama() + " ---");
        if (p1.isVIP() || p2.isVIP()) {
            System.out.println("Anda melawan player VIP, GOODLUCK :)");
        }

        while (skor1 < 2 && skor2 < 2) {
            System.out.println("\n--- Ronde Baru ---");

            String pil1 = null, pil2 = null;

            // Spy Card p1
            if (p1.isVIP() && p1.hasSpyCard()) {
                System.out.print(p1.getNama() + ", apakah ingin menggunakan Spy Card? (Y/N): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
                    if (isUrutanPertama(p1, p2)) {
                        p1.setSpyNextRound(true);
                        System.out.println(p1.getNama() + " akan menggunakan Spy Card di ronde selanjutnya.");
                    } else {
                        pil2 = inputValid(p2);
                        
                        delay();
                        clearConsole();
                        System.out.println(p1.getNama() + ", lawanmu memilih: " + pil2);
                        p1.gunakanSpyCard();
                        System.out.println(p1.getNama() + ", sekarang giliranmu memilih...");
                        pil1 = inputValid(p1);
                    }
                }
            }

            // Spy Card p2
            if (p2.isVIP() && p2.hasSpyCard()) {
                System.out.print(p2.getNama() + ", apakah ingin menggunakan Spy Card? (Y/N): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
                    if (isUrutanPertama(p2, p1)) {
                        p2.setSpyNextRound(true);
                        System.out.println(p2.getNama() + " akan menggunakan Spy Card di ronde selanjutnya.");
                    } else {
                        pil1 = inputValid(p1);
                        
                        delay();
                        clearConsole();
                        System.out.println(p2.getNama() + ", lawanmu memilih: " + pil1);
                        p2.gunakanSpyCard();
                        System.out.println(p2.getNama() + ", sekarang giliranmu memilih...");
                        pil2 = inputValid(p2);
                    }
                }
            }

            // Gunakan Spy Card jika aktif dari ronde sebelumnya
            if (p1.isSpyNextRound()) {
                pil2 = inputValid(p2);
                
                delay();
                clearConsole();
                System.out.println(p1.getNama() + ", lawanmu memilih: " + pil2);
                System.out.println(p1.getNama() + ", sekarang giliranmu memilih...");
                pil1 = inputValid(p1);
                p1.setSpyNextRound(false);
            } else if (p2.isSpyNextRound()) {
                pil1 = inputValid(p1);
                
                delay();
                clearConsole();
                System.out.println(p2.getNama() + ", lawanmu memilih: " + pil1);
                System.out.println(p2.getNama() + ", sekarang giliranmu memilih...");
                pil2 = inputValid(p2);
                p2.setSpyNextRound(false);
            }

            if (pil1 == null) {
                pil1 = inputValid(p1);
                clearConsole();
            }
            if (pil2 == null) {
                pil2 = inputValid(p2);
                clearConsole();
            }

            statistik.put(pil1, statistik.getOrDefault(pil1, 0) + 1);
            statistik.put(pil2, statistik.getOrDefault(pil2, 0) + 1);

            countdown();

            System.out.println(p1.getNama() + " memilih: " + pil1);
            System.out.println(p2.getNama() + " memilih: " + pil2);

            String hasil = tentukanPemenangRonde(p1, pil1, p2, pil2);
            System.out.println(hasil);

            if (hasil.contains(p1.getNama())) skor1++;
            else if (hasil.contains(p2.getNama())) skor2++;
        }

        Player pemenang = skor1 == 2 ? p1 : p2;
        pemenang.tambahMenang();
        System.out.println("Pemenang match: " + pemenang.getNama() + "\n");
        return pemenang;
    }

    private static String inputValid(Player p) {
        System.out.print(p.getNama() + ", masukkan pilihan batu/gunting/kertas: ");
        String pilihanInput = scanner.nextLine().toLowerCase();
        while (!Arrays.asList(pilihan).contains(pilihanInput)) {
            System.out.print("Pilihan tidak valid. Pilih lagi: ");
            pilihanInput = scanner.nextLine().toLowerCase();
        }
        return pilihanInput;
    }

    private static String tentukanPemenangRonde(Player p1, String a, Player p2, String b) {
        if (a.equals(b)) return "Seri!";

        boolean p1Menang = (a.equals("batu") && b.equals("gunting")) ||
                           (a.equals("gunting") && b.equals("kertas")) ||
                           (a.equals("kertas") && b.equals("batu"));

        return p1Menang ? p1.getNama() + " menang ronde!" : p2.getNama() + " menang ronde!";
    }

    private static boolean isUrutanPertama(Player p, Player lawan) {
        return p.getNama().compareToIgnoreCase(lawan.getNama()) < 0;
    }

    private static void clearConsole() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static Map<String, Integer> getStatistik() {
        return statistik;
    }

    private static void countdown() {
        System.out.print("\nHitung mundur: ");
        try {
            for (int i = 3; i > 0; i--) {
                System.out.print(i + ".. ");
                Thread.sleep(1000);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void delay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}