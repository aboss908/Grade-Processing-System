import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Student gets created with input from the scanner.
		Student student = null;

		boolean running = true;
		boolean createStudent = false;

		Scanner stringSelector = new Scanner(System.in);
		Scanner numberSelector = new Scanner(System.in);

		String stringResponse = "";

		int menuResponse = 0;
		int numberResponse = 0;

		DecimalFormat formatter = new DecimalFormat("#");
		formatter.setMaximumFractionDigits(0);
		// Removes scientific notation in big number IDs.

		while (running) {

			while (!createStudent) {
				String name = readUserString(stringResponse, stringSelector, "STUDENT NAME: ");

				Double id = readUserNumber(numberResponse, numberSelector, "STUDENT ID: ");

				student = new Student(name, id);
				createStudent = true;

				// CREATING TEST CLASS BELOW

				AssignmentManager classAssignments = new AssignmentManager("MATH141");

				Exam Exam1 = new Exam("Midterm1", 19, 32, 20);

				Exam Exam2 = new Exam("Midterm2", 89, 100, 20);

				Exam finalExam = new Exam("Final", 142, 200, 20);

				finalExam.setWeight(35);

				Homework homework1 = new Homework("H1", 10, 10, 1.66);

				Homework homework2 = new Homework("H2", 10, 10, 1.66);

				Homework homework3 = new Homework("H3", 4, 10, 1.66);

				Other projectOne = new Other("Project 1", 198, 200, "PROJECT", 20);

				classAssignments.addAssignment(Exam1);

				classAssignments.addAssignment(Exam2);

				classAssignments.addAssignment(finalExam);

				classAssignments.addAssignment(homework1);

				classAssignments.addAssignment(homework2);

				classAssignments.addAssignment(homework3);

				classAssignments.addAssignment(projectOne);

				student.addClass(classAssignments);

			}

			System.out.println("-----GRADE PROCESSING SYSTEM-----");
			System.out.println("STUDENT NAME: " + student.getName());
			System.out.println("ID: " + formatter.format(student.getId()));
			System.out.println("PRESS 1 TO VIEW ENROLLED CLASSES");
			System.out.println("PRESS 2 TO ENTER GRADE PROCESSOR");
			System.out.println("PRESS 3 TO QUIT");
			System.out.println("---------------------------------");

			while (true) {
				try {
					menuResponse = numberSelector.nextInt();
					break;
				} catch (Exception e) {
					numberSelector.nextLine();
					System.out.println("Not a selection! Please try again!");
				}
			}

			if (menuResponse == 1) {
				student.printoutClasses();
			} else if (menuResponse == 2) {

				// BULK OF THE CODE GOES HERE
				double decision = 0;

				while (decision <= 0 || decision > 5) {
					System.out.println("--------------------------------");
					System.out.println("PRESS 1 TO ADD OR REMOVE A CLASS");
					System.out.println("PRESS 2 TO ADD AN ASSIGNMENT TO A CLASS");
					System.out.println("PRESS 3 TO REMOVE AN ASSIGNMENT FROM A CLASS");
					System.out.println("PRESS 4 TO EDIT A CLASS ASSIGNMENT");
					System.out.println("PRESS 5 TO VIEW CLASS GRADES");
					System.out.println("PRESS 6 TO GO BACK TO THE MAIN MENU");
					System.out.println("--------------------------------");
					decision = readUserNumber(decision, numberSelector, "");
				}
				if (decision == 1) {
					// ADDING OR REMOVING A CLASS
					decision = 0;
					while (decision <= 0 || decision > 3) {
						System.out.println("--------------------------------");
						System.out.println("PRESS 1 TO ADD A CLASS");
						System.out.println("PRESS 2 TO REMOVE A CLASS");
						System.out.println("PRESS 3 TO GO BACK TO MAIN MENU");
						System.out.println("--------------------------------");
						decision = readUserNumber(decision, numberSelector, "");
					}

					if (decision == 1) {
						System.out.println("\n(PRESS -1 TO EXIT BACK TO MAIN MENU)\n");
						String className = readUserString(stringResponse, stringSelector, "Name of class: ");
						if (!className.equals("-1")) {
							AssignmentManager addedClass = new AssignmentManager(className);
							student.addClass(addedClass);
						}
					} else if (decision == 2) {

						if (student.getClasses().size() == 0) {
							// IF THE STUDENT ISN'T ENROLLED IN ANY CLASSES
							student.printoutClasses();
						} else {
							student.printoutClasses();
							decision = 0;

							while ((decision) > student.getClasses().size() || (decision - 1) < 0) {
								// Decision - 1, because every decision number relates to index: number-1
								// Student picks the class based off of a list number
								decision = readUserNumber(decision, numberSelector,
										"Which class would you like to remove? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
								if (decision > student.getClasses().size() || ((decision - 1) < 0) && decision != -1) {
									System.out.println("That's not a valid option! Choose a class in the list!\n");
								}
								if (decision == -1) {
									break;
								}
							}
							
							if (decision == -1) {
								System.out.println("EXIT TO MAIN MENU");
							} else {
								student.removeClass(student.getClasses().get(((int) decision) - 1));
							}
						}
					}

				} else if (decision == 2) {
					// ADDING AN ASSIGNMENT TO A CLASS
					decision = 0;

					student.printoutClasses();

					if (student.getClasses().size() != 0) {

						while ((decision) > student.getClasses().size() || (decision - 1) < 0) {
							decision = readUserNumber(decision, numberSelector,
									"Class to add assignment to? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
							if (decision > student.getClasses().size() || ((decision - 1) < 0) && decision != -1) {
								System.out.println("That's not a valid option! Choose a class in the list!\n");
							}
							if (decision == -1) {
								break;
							}
						}
						if (decision == -1) {
							System.out.println("EXIT TO MAIN MENU");
						} else {
							AssignmentManager classToEdit = (student.getClasses().get(((int) decision) - 1));
							while (true) {
								System.out.println("--------------------------------");
								System.out.println("TYPES:\n");
								System.out.println("1. EXAM");
								System.out.println("2. QUIZ");
								System.out.println("3. HOMEWORK");
								System.out.println("4. OTHER");
								System.out.println("--------------------------------");
								decision = 0;
								while (decision > 4 || decision < 1) {
									decision = readUserNumber(decision, numberSelector, "What type of assignment do you want added? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
									if (decision > 4 || decision < 0 && decision != -1) {
										System.out.println("That's not a valid option! Choose a type from the list!\n");
									}
									if (decision == -1) {
										break;
									}
								}

								if (decision == -1) {
									System.out.println("EXIT");
									break;
								} else {
									if (decision == 1) {
										createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Exam");
										System.out.println(classToEdit.printTotalGrade());
									} else if (decision == 2) {
										createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Quiz");
										System.out.println(classToEdit.printTotalGrade());
									} else if (decision == 3) {
										createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Homework");
										System.out.println(classToEdit.printTotalGrade());
									} else if (decision == 4) {
										createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Other");
										System.out.println(classToEdit.printTotalGrade());
									}
								}
							}

						}

					}

				} else if (decision == 3) {
					// REMOVING AN ASSIGNMENT FROM A CLASS
					decision = 0;
					student.printoutClasses();

					if (student.getClasses().size() != 0) {

						while ((decision) > student.getClasses().size() || (decision - 1) < 0) {
							// Decision - 1, because every decision number relates to index: number-1
							// Student picks the class based off of a list number
							decision = readUserNumber(decision, numberSelector,
									"Class to remove assignment from? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
							if (decision > student.getClasses().size() || ((decision - 1) < 0) && decision != -1) {
								System.out.println("That's not a valid option! Choose a class in the list!\n");
							}
							if (decision == -1) {
								break;
							}
						}

						if (decision == -1) {
							System.out.println("EXIT TO MAIN MENU");
						} else {
							AssignmentManager classToEdit = (student.getClasses().get(((int) decision) - 1));

							if (classToEdit.getClassAssignments().size() == 0) {
								System.out.println("\nCLASS HAS NO ASSIGNMENTS\n");
							} else {
								while (true) {
									System.out.println("--------------------------------");
									for (int i = 0; i < classToEdit.getClassAssignments().size(); i++) {
										System.out.println((i + 1) + ". " + classToEdit.getClassAssignments().get(i).getName());
									}
									System.out.println("--------------------------------");

									decision = 0;
									while ((decision) > classToEdit.getClassAssignments().size() || ((decision - 1) < 0)) {
										// Decision - 1, because every decision number relates to index: number-1
										// Student picks the class based off of a list number
										decision = readUserNumber(decision, numberSelector, "Which assignment do you want removed? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
										if (decision == -1) {
											break;
										}
										if (decision > classToEdit.getClassAssignments().size() || ((decision - 1) < 0) && decision != -1) {
											System.out.println("That's not a valid option! Choose an assignment in the list!\n");
										}

									}

									if (decision == -1) {
										System.out.println("EXIT");
										break;
									} else {
										Assignment assignment = classToEdit.getClassAssignments().get(((int) decision) - 1);
										classToEdit.removeAssignment(assignment);
										System.out.println(classToEdit.printTotalGrade());
									}
								}
							}
						}
					}

				} else if (decision == 4) {
					// EDITING ASSIGNMENTS
					decision = 0;
					if (student.getClasses().size() == 0) {
						student.printoutClasses();
					} else {
						student.printoutClasses();
						decision = 0;
						while ((decision) > student.getClasses().size() || (decision - 1) < 0) {
							// Decision - 1, because every decision number relates to index: number-1
							// Student picks the class based off of a list number
							decision = readUserNumber(decision, numberSelector, "Which class is the assignment in? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
							if (decision == -1) {
								break;
							}
							if (decision > student.getClasses().size() || ((decision - 1) < 0) && decision != -1) {
								System.out.println("That's not a valid option! Choose a class in the list!\n");
							}
						}
						if (decision != -1) {
							AssignmentManager classToEdit = (student.getClasses().get(((int) decision) - 1));

							System.out.println(classToEdit.printTotalGrade());

							// CHECKS INSIDE THE CLASS TO SEE WHICH ASSIGNMENT IS GOING TO GET EDITED
							if (classToEdit.getClassAssignments().size() == 0) {
								System.out.println("Class has no assignments!\n");
							} else {
								decision = 0;
								System.out.println("--------------------------------");
								for (int i = 0; i < classToEdit.getClassAssignments().size(); i++) {
									System.out.println((i + 1) + ". " + classToEdit.getClassAssignments().get(i).getName());
								}
								System.out.println("--------------------------------");
								while (decision > classToEdit.getClassAssignments().size() || (decision - 1) < 0) {
									decision = readUserNumber(decision, numberSelector, "Which assignment do you want to edit? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
									if (decision == -1) {
										break;
									}
									if (decision > classToEdit.getClassAssignments().size() || (decision -1) < 0 && decision != -1) {
										System.out.println("That's not a valid option! Choose an assignment in the list!\n");
									}
								}

								if (decision != -1) {
									Assignment assignmentToEdit = classToEdit.getClassAssignments().get(((int) decision) - 1);

									if (assignmentToEdit.getClass() == Exam.class) {

										Assignment assignmentHolder = assignmentToEdit;
										classToEdit.removeAssignment(assignmentToEdit);
										if (!createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Exam")) {
											assignmentToEdit = assignmentHolder;
											classToEdit.addAssignment(assignmentToEdit);
										}
										System.out.println(classToEdit.printTotalGrade());

									} else if (assignmentToEdit.getClass() == Quiz.class) {
										Assignment assignmentHolder = assignmentToEdit;
										classToEdit.removeAssignment(assignmentToEdit);
										if (!createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Quiz")) {
											assignmentToEdit = assignmentHolder;
											classToEdit.addAssignment(assignmentToEdit);
										}
										System.out.println(classToEdit.printTotalGrade());

									} else if (assignmentToEdit.getClass() == Homework.class) {
										Assignment assignmentHolder = assignmentToEdit;
										classToEdit.removeAssignment(assignmentToEdit);
										if (!createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Homework")) {
											assignmentToEdit = assignmentHolder;
											classToEdit.addAssignment(assignmentToEdit);
										}
										System.out.println(classToEdit.printTotalGrade());

									} else if (assignmentToEdit.getClass() == Other.class) {

										Assignment assignmentHolder = assignmentToEdit;
										classToEdit.removeAssignment(assignmentToEdit);
										if (!createAssignment(classToEdit, stringResponse, stringSelector, numberResponse, numberSelector, "Other")) {
											assignmentToEdit = assignmentHolder;
											classToEdit.addAssignment(assignmentToEdit);
										}
										System.out.println(classToEdit.printTotalGrade());

									}
								}
							}
						}
					}

				} else if (decision == 5) {
					// VIEWING CLASS GRADES
					decision = 0;
					while (decision <= 0 || decision > 2) {
						System.out.println("--------------------------------");
						System.out.println("PRESS 1 TO VIEW ALL CLASS GRADES");
						System.out.println("PRESS 2 TO VIEW THE GRADES OF ONE CLASS");
						System.out.println("--------------------------------");
						decision = readUserNumber(decision, numberSelector, "");
						if (decision <= 0 || decision > 2) {
							System.out.println("That's not a valid option! Choose an option in the list!\n");
						}
					}

					if (decision == 1) {
						if (student.getClasses().size() == 0) {
							System.out.println("\nStudent is not enrolled in any classes.\n");
						} else {
							for (int i = 0; i < student.getClasses().size(); i++) {
								System.out.println(student.getClasses().get(i).printTotalGrade());
							}
						}
					} else if (decision == 2) {

						if (student.getClasses().size() == 0) {
							student.printoutClasses();
						} else {
							student.printoutClasses();
							decision = 0;

							while ((decision) > student.getClasses().size() || (decision - 1) < 0) {
								// Decision - 1, because every decision number relates to index: number-1
								// Student picks the class based off of a list number
								decision = readUserNumber(decision, numberSelector, "Which class would you like to view? (CHOOSE A NUMBER OR PRESS -1 TO EXIT)");
								if (decision == -1) {
									break;
								}
								if (decision > student.getClasses().size() || ((decision - 1) < 0) && decision != -1) {
									System.out.println("That's not a valid option!\n");
								}
							}
							if (decision != -1) {
								System.out.println(student.getClasses().get(((int) decision) - 1).printTotalGrade());
							}
						}

					}

				} else if (decision == 6) {
					// RETURNS BACK TO THE MAIN MENU
					System.out.println("EXIT TO MAIN MENU");
				}

			} else if (menuResponse == 3) {
				// EXIT OUT OF APPLICATION
				break;
			}

		}

		numberSelector.close();
		stringSelector.close();
		System.out.println("\nEND");

	}

	private static boolean createAssignment(AssignmentManager classToEdit, String stringResponse, Scanner stringScanner,
			double numberResponse, Scanner numberScanner, String type) {

		String assignmentName = readUserString(stringResponse, stringScanner, "Name of assignment?");
		Double pointsEarned = readUserNumber(numberResponse, numberScanner,
				"How many points did you get on this assignment?");
		Double pointsTotal = readUserNumber(numberResponse, numberScanner, "Total number of points possible?");
		Double assignmentWeight = readUserNumber(numberResponse, numberScanner,
				"What's the weight of this assignment on your total grade? (EX: IF ONE MIDTERM EXAM IS WORTH 20% OF A TOTAL GRADE, ENTER 20)");
		if (type == "Exam") {
			Exam addedExam = new Exam(assignmentName, pointsEarned, pointsTotal, assignmentWeight);
			classToEdit.addAssignment(addedExam);
			if (classToEdit.getTotalWeight() > 100) {
				System.out.println("\nERROR ADDING ASSIGNMENT: TOTAL WEIGHT EXCEEDS 100% OF FINAL GRADE\n");
				classToEdit.removeAssignment(addedExam);
				return false;
			} else {
				return true;
			}
		} else if (type == "Quiz") {
			Quiz addedQuiz = new Quiz(assignmentName, pointsEarned, pointsTotal, assignmentWeight);
			classToEdit.addAssignment(addedQuiz);
			if (classToEdit.getTotalWeight() > 100) {
				System.out.println("\nERROR ADDING ASSIGNMENT: TOTAL WEIGHT EXCEEDS 100% OF FINAL GRADE\n");
				classToEdit.removeAssignment(addedQuiz);
				return false;
			} else {
				return true;
			}
		} else if (type == "Homework") {
			Homework addedHomework = new Homework(assignmentName, pointsEarned, pointsTotal, assignmentWeight);
			classToEdit.addAssignment(addedHomework);
			if (classToEdit.getTotalWeight() > 100) {
				System.out.println("\nERROR ADDING ASSIGNMENT: TOTAL WEIGHT EXCEEDS 100% OF FINAL GRADE\n");
				classToEdit.removeAssignment(addedHomework);
				return false;
			} else {
				return true;
			}
		} else if (type == "Other") {
			String assignmentType = readUserString(stringResponse, stringScanner,
					"What's the type of this assignment?");
			Other addedOther = new Other(assignmentName, pointsEarned, pointsTotal, assignmentType, assignmentWeight);
			classToEdit.addAssignment(addedOther);
			if (classToEdit.getTotalWeight() > 100) {
				System.out.println("\nERROR ADDING ASSIGNMENT: TOTAL WEIGHT EXCEEDS 100% OF FINAL GRADE\n");
				classToEdit.removeAssignment(addedOther);
				return false;
			} else {
				return true;
			}
		}

		return false;

	}

	private static String readUserString(String responseString, Scanner scanner, String question) {
		while (true) {
			System.out.println(question);
			responseString = scanner.nextLine();
			if (!responseString.isEmpty()) {
				break;
			}
		}
		return responseString;
	}

	private static double readUserNumber(double response, Scanner scanner, String question) {
		System.out.println(question);
		while (true) {
			try {
				response = scanner.nextDouble();
				break;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Not a valid number. Try again.");
			}
		}
		return response;
	}

}
