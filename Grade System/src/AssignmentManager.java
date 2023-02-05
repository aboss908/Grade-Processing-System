import java.util.ArrayList;

public class AssignmentManager {

	// responsible for calculating assignments, adding, removing assignments

	private String name;
	private ArrayList<Assignment> classAssignments;
	private double totalWeight;
	private String letterGrade;

	public AssignmentManager(String name) {
		this.name = name;
		this.classAssignments = new ArrayList<Assignment>();
	}

	public String getName() {
		return this.name;
	}
	
	public double getTotalWeight() {
		return this.totalWeight;
	}

	public String getLetterGrade() {
		return this.letterGrade;
	}

	public ArrayList<Assignment> getClassAssignments() {
		return this.classAssignments;
	}

	private boolean findAssignment(String assignmentName) {
		for (int i = 0; i < this.classAssignments.size(); i++) {
			if (this.classAssignments.get(i).getName().equals(assignmentName)) {
				return true;
			}
		}
		return false;
	}

	public boolean addAssignment(Assignment assignment) {
		if (assignment == null) {
			return false;
		}

		if (findAssignment(assignment.getName())) {
			System.out.println("Assignment already exists!");
			return false;
		} else {
			this.classAssignments.add(assignment);
			this.calculateFinalWeightedGrade();
			return true;
		}
	}

	public boolean removeAssignment(Assignment assignment) {
		if (assignment == null) {
			return false;
		}

		if (!findAssignment(assignment.getName())) {
			return false;
		} else {
			int index = this.classAssignments.indexOf(assignment);
			this.classAssignments.remove(index);
			this.calculateFinalWeightedGrade();
			return true;
		}
	}

	public boolean changeWeight(Assignment assignment, double newWeight) {
		if (assignment == null || newWeight <= 0) {
			return false;
		}

		if (assignment.getClass() == Exam.class) {
			((Exam) assignment).setWeight(newWeight);
			return true;
		} else if (assignment.getClass() == Quiz.class) {
			((Quiz) assignment).setWeight(newWeight);
			return true;
		} else if (assignment.getClass() == Homework.class) {
			((Homework) assignment).setWeight(newWeight);
			return true;
		} else if (assignment.getClass() == Other.class) {
			((Other) assignment).setWeight(newWeight);
			return true;
		} else {
			return false;
		}
	}

	public double calculateFinalWeightedGrade() {
		double finalGrade = 0d;
		double finalWeight = 0;

		for (int i = 0; i < this.classAssignments.size(); i++) {

			if (this.classAssignments.get(i).getClass().equals(Exam.class)) {

				Exam theExam = ((Exam) this.classAssignments.get(i));
				finalGrade += (theExam.calculateGrade() * (theExam.getWeight() * 0.01));
				finalWeight += theExam.getWeight();

			} else if (this.classAssignments.get(i).getClass().equals(Quiz.class)) {

				Quiz theQuiz = ((Quiz) this.classAssignments.get(i));
				finalGrade += (theQuiz.calculateGrade() * (theQuiz.getWeight() * 0.01));
				finalWeight += theQuiz.getWeight();

			} else if (this.classAssignments.get(i).getClass().equals(Homework.class)) {

				Homework theHomework = ((Homework) this.classAssignments.get(i));
				finalGrade += (theHomework.calculateGrade() * (theHomework.getWeight() * 0.01));
				finalWeight += theHomework.getWeight();

			} else {
				Other otherAssignment = ((Other) this.classAssignments.get(i));
				finalGrade += (otherAssignment.calculateGrade() * (otherAssignment.getWeight() * 0.01));
				finalWeight += otherAssignment.getWeight();
			}
		}

		this.totalWeight = finalWeight;
		double weightedGrade = Math.round(finalGrade / (finalWeight * 0.01));
		if (weightedGrade >= 97) {
			this.letterGrade = "A+";
		} else if (weightedGrade >= 93) {
			this.letterGrade = "A";
		} else if (weightedGrade >= 90) {
			this.letterGrade = "A-";
		} else if (weightedGrade >= 87) {
			this.letterGrade = "B+";
		} else if (weightedGrade >= 83) {
			this.letterGrade = "B";
		} else if (weightedGrade >= 80) {
			this.letterGrade = "B-";
		} else if (weightedGrade >= 77) {
			this.letterGrade = "C+";
		} else if (weightedGrade >= 73) {
			this.letterGrade = "C";
		} else if (weightedGrade >= 70) {
			this.letterGrade = "C-";
		} else if (weightedGrade >= 67) {
			this.letterGrade = "D+";
		} else if (weightedGrade >= 65) {
			this.letterGrade = "D";
		} else {
			this.letterGrade = "F";
		}
		return weightedGrade;
	}

	public String printTotalGrade() {

		String answer = "--------==== GRADES ====--------" + "\n";
		answer += "CLASS ID: " + this.name + "\n";
		answer += "--------------------------------\n";

		// PRINTS OUT ASSIGNMENTS BY TYPE: EXAM TYPE IS 0, QUIZ TYPE IS 1, HOMEWORK TYPE
		// IS 2, ANY OTHER TYPE IS 3

		for (int type = 0; type < 4; type++) {

			for (int index = 0; index < this.classAssignments.size(); index++) {

				if (type == 0) {
					if (this.classAssignments.get(index).getClass().equals(Exam.class)) {
						answer += this.classAssignments.get(index).printAssignment() + "\n";
						answer += "--------------------------------\n";
					}
				} else if (type == 1) {
					if (this.classAssignments.get(index).getClass().equals(Quiz.class)) {
						answer += this.classAssignments.get(index).printAssignment() + "\n";
						answer += "--------------------------------\n";
					}
				} else if (type == 2) {
					if (this.classAssignments.get(index).getClass().equals(Homework.class)) {
						answer += this.classAssignments.get(index).printAssignment() + "\n";
						answer += "--------------------------------\n";
					}
				} else if (type == 3) {
					if (this.classAssignments.get(index).getClass().equals(Other.class)) {
						answer += this.classAssignments.get(index).printAssignment() + "\n";
						answer += "--------------------------------\n";
					}
				}
			}
		}

		answer += "CURRENT GRADE: " + calculateFinalWeightedGrade() + "%\n";
		answer += "TOTAL WEIGHT ACCOUNTS FOR ROUGHLY " + Math.round(this.totalWeight) + "% OF FINAL GRADE\n";
		answer += "--------------------------------\n";

		return answer;
	}

}
