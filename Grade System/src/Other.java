
public class Other extends Assignment {

	private String type;
	private double weight;
	private double pointsReceived;
	private double totalPoints;

	public Other(String name, double pointsReceived, double totalPoints, String type, double weight) {
		super(name);

		this.type = type;
		checkPoints(pointsReceived, totalPoints);

		if (weight <= 0) {
			weight *= -1;
		} else
			this.weight = weight;
	}

	private void checkPoints(double pointsReceived, double totalPoints) {
		if (totalPoints == 0) {
			if (pointsReceived != 0) {
				this.pointsReceived = pointsReceived;
				this.totalPoints = pointsReceived;
			} else {
				this.pointsReceived = pointsReceived;
				this.totalPoints = 0d;
			}
		} else {
			this.pointsReceived = pointsReceived;
			this.totalPoints = totalPoints;
		}
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double value) {
		this.weight = value;
	}

	public double getPointsReceived() {
		return this.pointsReceived;
	}

	public double getTotalPoints() {
		return this.totalPoints;
	}

	public double calculateGrade() {
		return Math.round(((this.getPointsReceived() / this.getTotalPoints()) * 100));
	}

	public String printAssignment() {

		String answer = "";

		answer += "TYPE: " + this.type + "\n";
		answer += "Name: " + this.getName() + "\n";
		answer += "Weight: " + this.getWeight() + "% \n";
		answer += "Assignment Grade: " + this.getPointsReceived() + "/" + this.getTotalPoints() + " ("
				+ this.calculateGrade() + "%)";

		return answer;
	}

}
