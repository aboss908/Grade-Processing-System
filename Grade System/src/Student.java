import java.util.ArrayList;

public class Student {

	private String name;
	private double id;
	private ArrayList<AssignmentManager> classesGrades;

	public Student(String name, double id) {
		this.name = name;
		this.id = id;
		this.classesGrades = new ArrayList<AssignmentManager>();
	}

	private boolean findClass(String className) {
		for (int i = 0; i < this.classesGrades.size(); i++) {
			if (this.classesGrades.get(i).getName().equals(className)) {
				return true;
			}
		}
		return false;
	}

	public boolean addClass(AssignmentManager theClass) {
		if (theClass == null) {
			return false;
		}

		if (findClass(theClass.getName())) {
			System.out.println("Assignment already exists!");
			return false;
		} else {
			this.classesGrades.add(theClass);
			return true;
		}
	}

	public boolean removeClass(AssignmentManager theClass) {
		if (theClass == null) {
			return false;
		}

		if (!findClass(theClass.getName())) {
			return false;
		} else {
			int index = this.classesGrades.indexOf(theClass);
			this.classesGrades.remove(index);
			return true;
		}
	}

	public void printoutClasses() {
		if (this.classesGrades.isEmpty()) {
			System.out.println("\nStudent is not enrolled in any classes.\n");
		} else {
			System.out.println("\nCLASSES:\n");
			for (int i = 0; i < this.classesGrades.size(); i++) {
				AssignmentManager currentClass = this.classesGrades.get(i);
				System.out.println((i + 1) + ". " + currentClass.getName() + ": "
						+ currentClass.calculateFinalWeightedGrade() + "% (" + currentClass.getLetterGrade() + ")\n");
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public double getId() {
		return this.id;
	}

	public ArrayList<AssignmentManager> getClasses() {
		return this.classesGrades;
	}

}
