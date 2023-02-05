
public abstract class Assignment {

	private String assignmentName;

	public Assignment(String name) {
		this.assignmentName = name;
	}

	public String getName() {
		return this.assignmentName;
	}

	abstract String printAssignment();

	abstract double calculateGrade();

}
