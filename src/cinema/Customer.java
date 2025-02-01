package cinema;

public class Customer {
    private int customerId;
    private String name;
    private int rewardPoints;
    private int loyaltyProgramId;

    public Customer(int customerId, String name, int rewardPoints, int loyaltyProgramId) {
        this.customerId = customerId;
        this.name = name;
        this.rewardPoints = rewardPoints;
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(int loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

	@Override
	public String toString() {
		return "customerId=" + customerId + ", name=" + name + ", rewardPoints="
				+ rewardPoints + ", loyaltyProgramId=" + loyaltyProgramId;
	}
}
