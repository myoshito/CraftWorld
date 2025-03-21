package world01;

public class Fish {

	private int fishId;    // 魚の種類ID
    private String fishName; // 魚の名前
    private int fishNo;    // 魚固有の番号

    public Fish(int fishID, String fishNAME, int fishNO) {
        this.fishId = fishID;
        this.fishName = fishNAME;
        this.fishNo = fishNO;
    }

    public int getFishID() {
        return fishId;
    }

    public String getFishNAME() {
        return fishName;
    }

    public int getFishNO() {
        return fishNo;
    }

    @Override
    public String toString() {
        return "Fish #" + fishNo + ": " + fishName + " (ID: " + fishId + ")";
    }
}
