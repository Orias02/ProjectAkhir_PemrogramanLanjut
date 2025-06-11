package tournament.models;

import java.util.*;

public class Turnamen {
    public static void tampilkanSkor(List<Player> pemain) {
        System.out.println("=== Skor Historis ===");
        pemain.sort((a, b) -> b.getTotalMenang() - a.getTotalMenang());
        for (Player p : pemain) {
            System.out.println(p.getNama() + ": " + p.getTotalMenang() + " kemenangan");
        }
    }

    public static void tampilkanStatistik(Map<String, Integer> statistik) {
        System.out.println("\n=== Statistik Pilihan ===");
        for (String key : statistik.keySet()) {
            System.out.println(capitalize(key) + ": " + statistik.get(key) + " kali");
        }
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
