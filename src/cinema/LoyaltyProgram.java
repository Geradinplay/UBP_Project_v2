package cinema;

public class LoyaltyProgram {
	private int loyaltyProgramId;
	private int bonusPoints;
    private double discount;
	public LoyaltyProgram(int loyaltyProgramId, int bonusPoints, double discount) {
		super();
		this.loyaltyProgramId = loyaltyProgramId;
		this.bonusPoints = bonusPoints;
		this.discount = discount;
	}
	public int getLoyaltyProgramId() {
		return loyaltyProgramId;
	}
	public void setLoyaltyProgramId(int loyaltyProgramId) {
		this.loyaltyProgramId = loyaltyProgramId;
	}
	public int getBonusPoints() {
		return bonusPoints;
	}
	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "loyaltyProgramId=" + loyaltyProgramId + ", bonusPoints=" + bonusPoints + ", discount="
				+ discount;
	}
	



    
}

