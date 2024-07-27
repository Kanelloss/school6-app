package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.viewcontroller.*;

import java.awt.EventQueue;

public class Main {

	private final static MainMenuFrame mainMenuFrame = new MainMenuFrame();

	// Teachers Frames
	private final static TeachersMenuFrame teachersMenuFrame = new TeachersMenuFrame();
	private final static TeachersInsertFrame teachersInsertFrame = new TeachersInsertFrame();
	private final static TeachersUpdateDeleteFrame teachersUpdateDeleteFrame = new TeachersUpdateDeleteFrame();

	// Students Frames
	private final static StudentsMenuFrame studentsMenuFrame = new StudentsMenuFrame();
	private final static StudentsInsertFrame studentsInsertFrame = new StudentsInsertFrame();
	private final static StudentsUpdateDeleteFrame studentsUpdateDeleteFrame = new StudentsUpdateDeleteFrame();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mainMenuFrame.setLocationRelativeTo(null);
					mainMenuFrame.setVisible(true);
					
					teachersMenuFrame.setLocationRelativeTo(null);
					teachersMenuFrame.setVisible(false);
					
					teachersInsertFrame.setLocationRelativeTo(null);
					teachersInsertFrame.setVisible(false);
					
					teachersUpdateDeleteFrame.setLocationRelativeTo(null);
					teachersUpdateDeleteFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MainMenuFrame getMainMenuFrame() {
		return mainMenuFrame;
	}

	public static TeachersMenuFrame getTeachersMenuFrame() {
		return teachersMenuFrame;
	}

	public static TeachersInsertFrame getTeachersInsertFrame() {
		return teachersInsertFrame;
	}

	public static TeachersUpdateDeleteFrame getTeachersUpdateDeleteFrame() {
		return teachersUpdateDeleteFrame;
	}

	public static StudentsMenuFrame getStudentsMenuFrame() { return studentsMenuFrame; }

	public static StudentsInsertFrame getStudentsInsertFrame() { return studentsInsertFrame; }

	public static StudentsUpdateDeleteFrame getStudentsUpdateDeleteFrame() { return studentsUpdateDeleteFrame; }
}