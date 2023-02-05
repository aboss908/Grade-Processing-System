
public class Homework extends Assignment {

	private double weight;
	private double pointsReceived;
	private double totalPoints;

	public Homework(String name, double pointsReceived, double totalPoints, double weight) {
		super(name);

		if (weight <= 0) {
			weight *= -1;
		} else
			this.weight = weight;

		checkPoints(pointsReceived, totalPoints);
	}

	// Private method that checks for invalid points (0 out of 0 points)
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
		return weight;
	}

	public void setWeight(double value) {
		if (value <= 0) {
			return;
		}
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

		answer += "TYPE: HOMEWORK\n";
		answer += "Name: " + this.getName() + "\n";
		answer += "Weight: " + this.getWeight() + "% \n";
		answer += "Assignment Grade: " + this.getPointsReceived() + "/" + this.getTotalPoints() + " ("
				+ this.calculateGrade() + "%)";

		return answer;
	}

}
