package tournament.models;

public class Player {
    private String nama;
    private int totalMenang = 0;
    private boolean isVIP;
    private boolean hasSpyCard = false;
    private boolean spyNextRound = false;

    public Player(String nama, boolean isVIP) {
        this.nama = nama;
        this.isVIP = isVIP;
        if (isVIP) this.hasSpyCard = true;
    }

    public String getNama() {
        return nama;
    }

    public int getTotalMenang() {
        return totalMenang;
    }

    public void tambahMenang() {
        totalMenang++;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public boolean hasSpyCard() {
        return hasSpyCard;
    }

    public void gunakanSpyCard() {
        this.hasSpyCard = false;
    }

    public boolean isSpyNextRound() {
        return spyNextRound;
    }

    public void setSpyNextRound(boolean spyNextRound) {
        this.spyNextRound = spyNextRound;
    }
}