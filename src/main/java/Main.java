// Levi Hung
// 05/18/24
// CS 213 Final Exam Project.

// This program takes the GUI work from Homework12. Preparation2 for the  Final 
// and takes the StudentFileManager, CourseFileManager, EnrollmentFileManager 
// from the midterm and integrate all of them together.
// It implements a Student Enrollment System.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
   The Main class demonstrates a menu system.
*/

public class Main extends JFrame
{
  private JLabel fileTopicLabel;
  private final int LABEL_WIDTH = 500;
  private final int LABEL_HEIGHT = 550;

  // The following will reference menu components.
  private JMenuBar menuBar; 
  private JMenu fileMenu;
  private JMenu studentMenu;
  private JMenu courseMenu;
  private JMenu enrollMenu;
  private JMenu reportMenu;

  private JMenuItem exitItem;
  private JMenuItem addStudentItem;
  private JMenuItem viewStudentItem;
  private JMenuItem editStudentItem;
  private JMenuItem addCourseItem;
  private JMenuItem viewCourseItem;
  private JMenuItem editCourseItem;
  private JMenuItem addEnrollItem;
  private JMenuItem viewEnrollItem;
  private JMenuItem editEnrollItem;
  private JMenuItem generateReportItem;

  private JPanel	studentPanel;
  private JPanel	coursePanel;
  private JPanel	enrollPanel;
  private JPanel	reportPanel;

  private JLabel studentTopicLabel;
  private JTextField studentIDTField;
  private JTextField studentFNameTField;
  private JTextField studentLNameTField;
  private JTextField studentAddressTField;
  private JTextField studentCityTField;
  private JComboBox  studentStateComboBox;
  private JTextField studentZipCodeTField;

  private JButton studentButton;
  private JButton studentCancelButton;
  private JButton studentSearchButton;



  private JLabel courseTopicLabel;
  private JTextField courseIDTField;
  private JTextField courseNameTField;
  private JTextField courseDescriptionTField;
  private JTextField courseNumberTField;
  private JTextField courseInstructorTField;

  private JButton courseButton;
  private JButton courseCancelButton;
  private JButton courseSearchButton;

  private JLabel enrollTopicLabel;
  private JLabel enrollIDLabel;

  private JTextField enrollIDTField;
  private JTextField enrollStudentIDTField;
  private JTextField enrollStudentNameTField;
  private JTextField enrollCourseIDTField;
  private JTextField enrollCourseNameTField;
  private JTextField enrollYearTField;
  private JComboBox  enrollSemesterComboBox;

  private JTextField enrollGradeTField;

  private JButton enrollButton;
  private JButton enrollCancelButton;
  private JButton enrollSearchButton;
  private JButton enrollStudentCheckButton;
  private JButton enrollCourseCheckButton;

  private JLabel reportTopicLabel;
  private JTextField reportCourseNameTField;
  private JComboBox<String>  reportSemesterComboBox;
  private JTextField reportYearTField;
  private JTextArea reportTextArea;
  private JButton reportButton;

  private static StudentFileManager    studentFM;
  private static CourseFileManager     courseFM;
  private static EnrollmentFileManager enrollmentFM;

  public static final int MSG_INFO 		= 0;
  public static final int MSG_WARNING = 1;
  public static final int MSG_ERROR 	= 2;

  /**
   *  Constructor
   */

  public Main()
  {
    // Set the title.
    setTitle("CS 213 Final Project");

    // Specify an action for the close button.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the fileTopicLabel label.
    fileTopicLabel = new JLabel("Welcome to CS 213 Final Project.",
                                SwingConstants.CENTER);
    fileTopicLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));

    // Set the label's preferred size.
    fileTopicLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));

    // Set the label's foreground color.
    fileTopicLabel.setForeground(Color.black);

    // Add the label to the content pane.
    add(fileTopicLabel);

    // Build the menu bar.
    buildMenuBar();

    // Pack and display the window.
    pack();
    setVisible(true);
  }

  /**
   *  The buildMenuBar method builds the menu bar.
   */

   private void buildMenuBar()
   {
     // Create the menu bar.
     menuBar = new JMenuBar();

     // Create the file, student, course, and enrollment menus.
     buildFileMenu();
     buildStudentMenu();
     buildCourseMenu();
     buildEnrollMenu();
     buildReportMenu();

     // Add the file, student, course, and enrollment menus to the menu bar.
     menuBar.add(fileMenu);
     menuBar.add(studentMenu);
     menuBar.add(courseMenu);
     menuBar.add(enrollMenu);
     menuBar.add(reportMenu);

     // Set the window's menu bar.
     setJMenuBar(menuBar);

     buildStudentPanel();
     buildCoursePanel();
     buildEnrollPanel();
     buildReportPanel();

  }

  /**
   *  The buildFileMenu method builds the File menu and its items.
   */

  private void buildFileMenu()
  {
    // Create an Exit menu item.
    exitItem = new JMenuItem("Exit");
    exitItem.setMnemonic(KeyEvent.VK_X);
    exitItem.addActionListener(new ExitListener());

    // Create a JMenu object for the File menu.
    fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);

    // Add the Exit menu item to the File menu.
    fileMenu.add(exitItem);
  }

  /**
   *  The buildStudentMenu method builds the student menu and its items.
   */

  private void buildStudentMenu()
  {
    // Create the menu items of add, view, and edit student.
    // Add the action listener to each one.
    addStudentItem = new JMenuItem("Add");
    addStudentItem.setMnemonic(KeyEvent.VK_B);
    addStudentItem.addActionListener(new studentListener());

    viewStudentItem = new JMenuItem("View");
    viewStudentItem.setMnemonic(KeyEvent.VK_R);
    viewStudentItem.addActionListener(new studentListener());

    editStudentItem = new JMenuItem("Edit");
    editStudentItem.setMnemonic(KeyEvent.VK_U);
    editStudentItem.addActionListener(new studentListener());

    // Create a JMenu object for the student menu.
    studentMenu = new JMenu("Student");
    studentMenu.setMnemonic(KeyEvent.VK_T);

    // Add the menu items to the student menu.
    studentMenu.add(addStudentItem);
    studentMenu.add(viewStudentItem);
    studentMenu.add(editStudentItem);
  }

  /**
   *  The buildCourseMenu method builds the course menu and its items.
   */

  private void buildCourseMenu()
  {
    // Create the menu items of add, view, and edit course.
    // Add the action listener to each one.
    addCourseItem = new JMenuItem("Add");
    addCourseItem.setMnemonic(KeyEvent.VK_B);
    addCourseItem.addActionListener(new courseListener());

    viewCourseItem = new JMenuItem("View");
    viewCourseItem.setMnemonic(KeyEvent.VK_R);
    viewCourseItem.addActionListener(new courseListener());

    editCourseItem = new JMenuItem("Edit");
    editCourseItem.setMnemonic(KeyEvent.VK_U);
    editCourseItem.addActionListener(new courseListener());

    // Create a JMenu object for the course menu.
    courseMenu = new JMenu("Course");
    courseMenu.setMnemonic(KeyEvent.VK_T);

    // Add the menu items to the course menu.
    courseMenu.add(addCourseItem);
    courseMenu.add(viewCourseItem);
    courseMenu.add(editCourseItem);
  }

  /**
   *  The buildEnrollMenu method builds the enrollment menu and its items.
   */

  private void buildEnrollMenu()
  {
    // Create the menu items of add, view, and edit course.
    // Add the action listener to each one.
    addEnrollItem = new JMenuItem("Add");
    addEnrollItem.setMnemonic(KeyEvent.VK_B);
    addEnrollItem.addActionListener(new enrollListener());

    viewEnrollItem = new JMenuItem("View");
    viewEnrollItem.setMnemonic(KeyEvent.VK_R);
    viewEnrollItem.addActionListener(new enrollListener());

    editEnrollItem = new JMenuItem("Edit");
    editEnrollItem.setMnemonic(KeyEvent.VK_U);
    editEnrollItem.addActionListener(new enrollListener());

    // Create a JMenu object for the enrollment menu.
    enrollMenu = new JMenu("Enrollment");
    enrollMenu.setMnemonic(KeyEvent.VK_T);

    // Add the menu items to the enrollment menu.
    enrollMenu.add(addEnrollItem);
    enrollMenu.add(viewEnrollItem);
    enrollMenu.add(editEnrollItem);
  }

  /**
   *  The buildReportMenu method builds the reports menu and its items.
   */

  private void buildReportMenu()
  {
    // Create the menu items of add, view, and edit course.
    // Add the action listener to each one.
    generateReportItem = new JMenuItem("Generate");
    generateReportItem.setMnemonic(KeyEvent.VK_B);
    generateReportItem.addActionListener(new reportListener());

    // Create a JMenu object for the enrollment menu.
    reportMenu = new JMenu("Reports");
    reportMenu.setMnemonic(KeyEvent.VK_T);

    // Add the menu items to the report menu.
    reportMenu.add(generateReportItem);
  }

  /**
   *  The buildStudentPanel method builds the student panel and its objects.
   */

  private void buildStudentPanel()
  {
    studentPanel = new JPanel();
    studentPanel.setLayout(null);

    // Create the studentTopicLabel label and studentIDTField TextField.
    studentTopicLabel = new JLabel("Add Student");
    studentTopicLabel.setBounds(200, 30, 200, 20);
    studentTopicLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));

    JLabel studentIDLabel =	new JLabel("Student ID");
    studentIDLabel.setBounds(140, 100, 100, 20);

    // Create the other labels and TextFields.
    studentIDTField	= new	JTextField(30);
    studentIDTField.setBounds(240, 100, 120, 20);

    studentSearchButton = new JButton("Search");
    studentSearchButton.setBounds(380, 100, 100, 20);
    studentSearchButton.addActionListener(new studentButtonListener());

    JLabel studentFNameLabel =	new JLabel("First Name");
    studentFNameLabel.setBounds(140, 150, 100, 20);

    studentFNameTField	= new	JTextField(30);
    studentFNameTField.setBounds(240, 150, 120, 20);

    JLabel studentLNameLabel =	new JLabel("Last Name");
    studentLNameLabel.setBounds(140, 200, 100, 20);

    studentLNameTField	= new	JTextField(30);
    studentLNameTField.setBounds(240, 200, 120, 20);

    JLabel studentAddressLabel =	new JLabel("Address");
    studentAddressLabel.setBounds(140, 250, 100, 20);

    studentAddressTField	= new	JTextField(30);
    studentAddressTField.setBounds(240, 250, 120, 20);

    JLabel studentCityLabel =	new JLabel("City");
    studentCityLabel.setBounds(140, 300, 100, 20);

    studentCityTField	= new	JTextField(30);
    studentCityTField.setBounds(240, 300, 120, 20);

    JLabel stateLabel = new JLabel("State");
    stateLabel.setBounds(140, 350, 100, 20);

    String[] state = {"Alabama", "Alaska", "Arizona", "Arkansas", 
                      "American Samoa", "California", "Colorado", "Connecticut", 
                      "Delaware", "District of Columbia", "Florida", "Georgia", 
                      "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", 
                      "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", 
                      "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                      "Missouri", "Montana", "Nebraska", "Nevada", 
                      "New Hampshire", "New Jersey", "New Mexico", "New York", 
                      "North Carolina", "North Dakota", "Northern Mariana Islands", 
                      "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", 
                      "Rhode Island", "South Carolina", "South Dakota", 
                      "Tennessee", "Texas", "Trust Territories", "Utah", 
                      "Vermont", "Virginia", "Virgin Islands", "Washington",
                      "West Virginia", "Wisconsin", "Wyoming", "Other"};

    studentStateComboBox = new JComboBox<>(state);
    studentStateComboBox.setBounds(240, 350, 120, 20);

    JLabel studentZipCodeLabel =	new JLabel("Zip Code");
    studentZipCodeLabel.setBounds(140, 400, 100, 20);

    studentZipCodeTField	= new	JTextField(30);
    studentZipCodeTField.setBounds(240, 400, 120, 20);

    // Create the add and cancel buttons.
    studentButton = new JButton("Add");
    studentButton.setBounds(140, 500, 100, 20);
    studentButton.addActionListener(new studentButtonListener());

    studentCancelButton = new JButton("Cancel");
    studentCancelButton.setBounds(270, 500, 100, 20);
    studentCancelButton.addActionListener(new studentButtonListener());

    // Add the objects	to the student panel.
    studentPanel.add(studentTopicLabel);
    studentPanel.add(studentIDLabel);
    studentPanel.add(studentIDTField);
    studentPanel.add(studentSearchButton);
    studentPanel.add(studentFNameLabel);
    studentPanel.add(studentFNameTField);
    studentPanel.add(studentLNameLabel);
    studentPanel.add(studentLNameTField);
    studentPanel.add(studentAddressLabel);
    studentPanel.add(studentAddressTField);
    studentPanel.add(studentCityLabel);
    studentPanel.add(studentCityTField);
    studentPanel.add(stateLabel);
    studentPanel.add(studentStateComboBox);
    studentPanel.add(studentZipCodeLabel);
    studentPanel.add(studentZipCodeTField);
    studentPanel.add(studentButton);
    studentPanel.add(studentCancelButton);    
  }

  /**
   *  The buildCoursePanel method builds the course panel and its objects.
   */

  private void buildCoursePanel()
  {
    coursePanel = new JPanel();

    coursePanel.setLayout(null);

    // Create the courseTopicLabel label and studentIDTField TextField.
    courseTopicLabel = new JLabel("Add Course");
    courseTopicLabel.setBounds(200, 30, 200, 20);
    courseTopicLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));

    JLabel CourseIDLabel =	new JLabel("Course ID");
    CourseIDLabel.setBounds(140, 100, 100, 20);

    courseIDTField	= new	JTextField(30);
    courseIDTField.setBounds(240, 100, 120, 20);

    courseSearchButton = new JButton("Search");
    courseSearchButton.setBounds(380, 100, 100, 20);
    courseSearchButton.addActionListener(new courseButtonListener());

    // Create the other labels and TextFields.
    JLabel courseNameLabel =	new JLabel("Course Name");
    courseNameLabel.setBounds(140, 150, 100, 20);

    courseNameTField	= new	JTextField(30);
    courseNameTField.setBounds(240, 150, 120, 20);

    JLabel courseDescriptionLabel =	new JLabel("Description");
    courseDescriptionLabel.setBounds(140, 200, 100, 20);

    courseDescriptionTField	= new	JTextField(30);
    courseDescriptionTField.setBounds(240, 200, 120, 20);

    JLabel courseNumberLabel =	new JLabel("Number");
    courseNumberLabel.setBounds(140, 250, 100, 20);

    courseNumberTField	= new	JTextField(30);
    courseNumberTField.setBounds(240, 250, 120, 20);


    JLabel coursInstructorLabel =	new JLabel("Instructor");
    coursInstructorLabel.setBounds(140, 300, 100, 20);

    courseInstructorTField	= new	JTextField(30);
    courseInstructorTField.setBounds(240, 300, 120, 20);

    // Create the add and cancel buttons.
    courseButton = new JButton("Add");
    courseButton.setBounds(140, 500, 100, 20);
    courseButton.addActionListener(new courseButtonListener());

    courseCancelButton = new JButton("Cancel");
    courseCancelButton.setBounds(270, 500, 100, 20);
    courseCancelButton.addActionListener(new courseButtonListener());

    // Add the objects	to the course panel.
    coursePanel.add(courseTopicLabel);
    coursePanel.add(CourseIDLabel);
    coursePanel.add(courseIDTField);
    coursePanel.add(courseSearchButton);
    coursePanel.add(courseNameLabel);
    coursePanel.add(courseNameTField);
    coursePanel.add(courseDescriptionLabel);
    coursePanel.add(courseDescriptionTField);
    coursePanel.add(courseNumberLabel);
    coursePanel.add(courseNumberTField);
    coursePanel.add(coursInstructorLabel);
    coursePanel.add(courseInstructorTField);
    coursePanel.add(courseButton);
    coursePanel.add(courseCancelButton);

  }  

  /**
   *  The buildEnrollPanel method builds the enrollment panel and its objects.
   */

  private void buildEnrollPanel()
  {
    enrollPanel = new JPanel();

    enrollPanel.setLayout(null);

    // Create the fileTopicLabel label and studentIDTField TextField.
    enrollTopicLabel = new JLabel("Add Enrollment");
    enrollTopicLabel.setBounds(200, 30, 200, 20);
    enrollTopicLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));

    enrollIDLabel =	new JLabel("Enroll ID");
    enrollIDLabel.setBounds(140, 100, 100, 20);

    enrollIDTField	= new	JTextField(30);
    enrollIDTField.setBounds(240, 100, 120, 20);

    enrollSearchButton = new JButton("Search");
    enrollSearchButton.setBounds(380, 100, 100, 20);
    enrollSearchButton.addActionListener(new enrollButtonListener());

    // Create the other labels and TextFields.
    JLabel enrollStudentIDLabel =	new JLabel("Student ID");
    enrollStudentIDLabel.setBounds(140, 150, 100, 20);

    enrollStudentIDTField	= new	JTextField(30);
    enrollStudentIDTField.setBounds(240, 150, 120, 20);

    enrollStudentCheckButton = new JButton("Check SID");
    enrollStudentCheckButton.setBounds(380, 150, 100, 20);
    enrollStudentCheckButton.addActionListener(new enrollButtonListener());

    JLabel enrollStudentNameLabel =	new JLabel("Student Name");
    enrollStudentNameLabel.setBounds(140, 200, 120, 20);

    enrollStudentNameTField	= new	JTextField(30);
    enrollStudentNameTField.setBounds(240, 200, 120, 20);
    enrollStudentNameTField.setEditable(false);

    JLabel enrollCourseIDLabel =	new JLabel("Course ID");
    enrollCourseIDLabel.setBounds(140, 250, 100, 20);

    enrollCourseIDTField	= new	JTextField(30);
    enrollCourseIDTField.setBounds(240, 250, 120, 20);

    enrollCourseCheckButton = new JButton("Check CID");
    enrollCourseCheckButton.setBounds(380, 250, 100, 20);
    enrollCourseCheckButton.addActionListener(new enrollButtonListener());


    JLabel enrollCourseNameLabel =	new JLabel("Course Name");
    enrollCourseNameLabel.setBounds(140, 300, 100, 20);

    enrollCourseNameTField	= new	JTextField(30);
    enrollCourseNameTField.setBounds(240, 300, 120, 20);
    enrollCourseNameTField.setEditable(false);

    JLabel enrollYearLabel =	new JLabel("Year");
    enrollYearLabel.setBounds(140, 350, 100, 20);

    enrollYearTField	= new	JTextField(30);
    enrollYearTField.setBounds(240, 350, 120, 20);

    JLabel enrollSemesterLabel =	new JLabel("Semester");
    enrollSemesterLabel.setBounds(140, 400, 100, 20);

    //enrollSemesterTField	= new	JTextField(30);
    //enrollSemesterTField.setBounds(240, 400, 120, 20);

    String[] semester = {"spring", "summer", "fall", "winter"};

    enrollSemesterComboBox = new JComboBox<>(semester);
    enrollSemesterComboBox.setBounds(240, 400, 120, 20);

    JLabel enrollGradeLabel =	new JLabel("Grade");
    enrollGradeLabel.setBounds(140, 450, 100, 20);

    enrollGradeTField	= new	JTextField(30);
    enrollGradeTField.setBounds(240, 450, 120, 20);

    // Create the add and cancel buttons.
    enrollButton = new JButton("Add");
    enrollButton.setBounds(140, 500, 100, 20);
    enrollButton.addActionListener(new enrollButtonListener());

    enrollCancelButton = new JButton("Cancel");
    enrollCancelButton.setBounds(270, 500, 100, 20);
    enrollCancelButton.addActionListener(new enrollButtonListener());

    // Add the objects to the enrollment panel.
    enrollPanel.add(enrollTopicLabel);
    enrollPanel.add(enrollIDLabel);
    enrollPanel.add(enrollIDTField);
    enrollPanel.add(enrollSearchButton);
    enrollPanel.add(enrollStudentIDLabel);
    enrollPanel.add(enrollStudentIDTField);
    enrollPanel.add(enrollStudentCheckButton);
    enrollPanel.add(enrollStudentNameLabel);
    enrollPanel.add(enrollStudentNameTField);
    enrollPanel.add(enrollCourseIDLabel);
    enrollPanel.add(enrollCourseIDTField);
    enrollPanel.add(enrollCourseCheckButton);
    enrollPanel.add(enrollCourseNameLabel);
    enrollPanel.add(enrollCourseNameTField);
    enrollPanel.add(enrollYearLabel);
    enrollPanel.add(enrollYearTField);
    enrollPanel.add(enrollSemesterLabel);
    enrollPanel.add(enrollSemesterComboBox);
    enrollPanel.add(enrollGradeLabel);
    enrollPanel.add(enrollGradeTField);
    enrollPanel.add(enrollButton);
    enrollPanel.add(enrollCancelButton);
  }  

  /**
   *  The buildEnrollPanel method builds the enrollment panel and its objects.
   */

  private void buildReportPanel()
  {
    reportPanel = new JPanel();

    reportPanel.setLayout(null);

    // Create the fileTopicLabel label and studentIDTField TextField.
    reportTopicLabel = new JLabel("Generate Reports");
    reportTopicLabel.setBounds(150, 30, 300, 30);
    reportTopicLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));

    JLabel courseNameLabel = new JLabel("Course Name");
    JLabel semesterLabel   = new JLabel("Semester");
    JLabel yearrLabel      = new JLabel("Year");
    courseNameLabel.setBounds(30, 100, 100, 20);
    semesterLabel.setBounds(150, 100, 100, 20);
    yearrLabel.setBounds(270, 100, 100, 20);


    reportCourseNameTField = new JTextField();
    reportCourseNameTField.setBounds(20, 150, 100, 20);

    String[] semester = {"spring", "summer", "fall", "winter"};

    reportSemesterComboBox = new JComboBox<>(semester);
    reportSemesterComboBox.setBounds(140, 150, 110, 20);

    reportYearTField = new JTextField();
    reportYearTField.setBounds(260, 150, 100, 20);

    reportButton = new JButton("Generate");
    reportButton.addActionListener(new reportButtonListener());
    reportButton.setBounds(380, 150, 105, 20);

    reportTextArea = new JTextArea();
    reportTextArea.setBounds(20, 200, 460, 320);

    JScrollPane scroll = new JScrollPane(reportTextArea);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setBounds(20, 200, 460, 320);

    // Add the objects to the enrollment panel.
    reportPanel.add(reportTopicLabel);
    reportPanel.add(courseNameLabel);
    reportPanel.add(semesterLabel);
    reportPanel.add(yearrLabel);
    reportPanel.add(reportCourseNameTField);
    reportPanel.add(reportSemesterComboBox);
    reportPanel.add(reportYearTField);
    reportPanel.add(reportButton);
    //reportPanel.add(reportTextArea);
    reportPanel.add(scroll);
  }  

  private void clearStudentInfo()
  {
    studentIDTField.setText("");
    studentFNameTField.setText("");
    studentLNameTField.setText("");
    studentAddressTField.setText("");
    studentCityTField.setText("");
    studentStateComboBox.setSelectedIndex(-1);
    studentZipCodeTField.setText("");
  }

  private void clearCourseInfo()
  {
    courseIDTField.setText("");
    courseNameTField.setText("");
    courseDescriptionTField.setText("");
    courseNumberTField.setText("");
    courseInstructorTField.setText("");
  }

  private void clearEnrollInfo()
  {
    enrollIDTField.setText("");
    enrollStudentIDTField.setText("");
    enrollStudentNameTField.setText("");
    enrollCourseIDTField.setText("");
    enrollCourseNameTField.setText("");
    enrollYearTField.setText("");
    enrollSemesterComboBox.setSelectedIndex(-1);
    enrollGradeTField.setText("");
  }

  private void clearReportInfo()
  {
    reportCourseNameTField.setText("");
    reportSemesterComboBox.setSelectedIndex(-1);
    reportYearTField.setText("");
    reportTextArea.setText("");
  }

  private void showMessage(String msg, int type)
  {
    if (type == MSG_INFO)
    {
      JOptionPane.showMessageDialog(null, msg, "Information", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    else if (type == MSG_WARNING)
    {
      JOptionPane.showMessageDialog(null, msg, "Warning", 
                                    JOptionPane.WARNING_MESSAGE);
    }
    else
    {
      JOptionPane.showMessageDialog(null, msg, "Error", 
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   *  Private inner class that handles the event that
   *  is generated when the user selects Exit from
   *  the File menu.
   */

  private class ExitListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      System.exit(0);
    }
  }

  /**
   *  Private inner class that handles the event that
   *  is generated when the user selects a menuItem from
   *  the student menu.
   */

  private class studentListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      // Get the action command.
      String actionCommand = e.getActionCommand();

      fileTopicLabel.setVisible(false);
      coursePanel.setVisible(false);
      enrollPanel.setVisible(false);
      reportPanel.setVisible(false);
      studentPanel.setVisible(true);
      add(studentPanel);

      // Determine which button was clicked and display
      // a message.
      if (actionCommand.equals("Add"))
      {
        clearStudentInfo();      	
        studentTopicLabel.setText("Add Student");
        studentIDTField.setEnabled(true);
        studentSearchButton.setVisible(false);
        studentButton.setVisible(true);
        studentCancelButton.setVisible(true);
        studentButton.setText("Add");
      }
      else if (actionCommand.equals("View"))
      {
        clearStudentInfo();      	
        studentTopicLabel.setText("View Student");
        studentIDTField.setEnabled(true);
        studentSearchButton.setVisible(true);
        studentButton.setVisible(false);
        studentCancelButton.setVisible(false);
      }
      else if (actionCommand.equals("Edit"))
      {      	
        clearStudentInfo();
        studentTopicLabel.setText("Edit Student");
        studentIDTField.setEnabled(true);
        studentSearchButton.setVisible(true);
        studentButton.setVisible(true);
        studentCancelButton.setVisible(true);
        studentButton.setText("Edit");
      }
    }
  }

  /**
   * Private inner class that handles the event when
   * the user clicks a button.
   */

  private class studentButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      // Get the action command.
      String actionCommand = event.getActionCommand();

      if (actionCommand.equals("Cancel"))
      {
        clearStudentInfo();
        //studentIDTField.setEditable(true);
        studentIDTField.setEnabled(true);
        if (studentTopicLabel.getText() != "Add Student")
        {
          studentSearchButton.setVisible(true);
        }
      }
      else if (actionCommand.equals("Search"))
      {
        try 
        {
          // Call the accessor methods of studentFileManager Object 
          // to get the student's information and display it.
          int id 				 = Integer.parseInt(studentIDTField.getText());
          String fname   = studentFM.getStudentFName(id);
          String lname   = studentFM.getStudentLName(id);
          String address = studentFM.getStudentAddress(id);
          String city 	 = studentFM.getStudentCity(id);
          String state 	 = studentFM.getStudentState(id);
          String zipCode = studentFM.getStudentZipCode(id);

          studentFNameTField.setText(fname);
          studentLNameTField.setText(lname);
          studentAddressTField.setText(address);
          studentCityTField.setText(city);
          if (state.length() > 0 )
          {
            studentStateComboBox.setSelectedItem(state);
          }
          else
          {
            studentStateComboBox.setSelectedIndex(-1);;
          }
          studentZipCodeTField.setText(zipCode);

          if (studentTopicLabel.getText() == "Edit Student")
          {
            studentIDTField.setEnabled(false);
            studentSearchButton.setVisible(false);
          } 
        }
        catch (InvalidStudentID e) 
        {
          showMessage(e.getMessage(), MSG_WARNING);
          clearStudentInfo();
        }
        catch (NumberFormatException e)
        {
          showMessage("The student ID must be integer.", MSG_WARNING);
          clearStudentInfo();
        }
        catch (Exception e)
        {
          showMessage(e.getMessage(), MSG_ERROR);
          clearStudentInfo();
        }
      }
      else
      {
        // actionCommand.equals("Add") or actionCommand.equals("Edit")
        try 
        {
          int id 				  = Integer.parseInt(studentIDTField.getText());
          String fname 		= studentFNameTField.getText();
          String lname 		= studentLNameTField.getText();
          String address 	= studentAddressTField.getText();
          String city 		= studentCityTField.getText();
          Object stateObj = studentStateComboBox.getSelectedItem();
          String state;
          if (stateObj == null)
          {
            state = "";
          }
          else
          {
            state = (String)stateObj;
          }

          String zipCode 	= studentZipCodeTField.getText();

          if (actionCommand.equals("Add"))
          {
            // Call addStudent method to add the student to the end of the ArrayList
            // and write the student information in the file. 
            studentFM.addStudent(id, fname, lname, address, 
                                 city, state, zipCode);
            showMessage("Add Student Successfully.", MSG_INFO);	   
            clearStudentInfo();
          }
          else
          {
            // Call updateStudent method to update the student of the ArrayList
            // and write the student information in the file. 
            studentFM.updateStudent(id, fname, lname, address, 
                                    city, state, zipCode);
            showMessage("Edit Student successfully.", MSG_INFO);
            //clearStudentInfo();
          }
        }
        catch (InvalidStudentIDExist | InvalidStudentID | InvalidStudentInfo e) 
        {
          showMessage(e.getMessage(), MSG_WARNING);	          
        }
        catch (NumberFormatException e)
        {
          showMessage("The student ID must be integer.", MSG_WARNING);	          
        }
        catch (IOException e)
        {           
          showMessage(e.getMessage(), MSG_ERROR);
        }
        catch (Exception e)
        {
          showMessage(e.getMessage(), MSG_ERROR);
        }
      }
    }
  }

  /**
   *  Private inner class that handles the event that
   *  is generated when the user selects a menuItem from
   *  the course menu.
   */

  private class courseListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      // Get the action command.
      String actionCommand = e.getActionCommand();

      fileTopicLabel.setVisible(false);
      studentPanel.setVisible(false);
      coursePanel.setVisible(true);
      enrollPanel.setVisible(false);
      reportPanel.setVisible(false);

      add(coursePanel);

      clearCourseInfo();      	

      // Determine which button was clicked and display
      // a message.
      if (actionCommand.equals("Add"))
      {
        courseTopicLabel.setText("Add Course");
        //courseIDTField.setEditable(true);
        courseIDTField.setEnabled(true);
        courseSearchButton.setVisible(false);      	
        courseButton.setVisible(true);
        courseCancelButton.setVisible(true);
        courseButton.setText("Add");
      }
      else if (actionCommand.equals("View"))
      {
        courseTopicLabel.setText("View Course");
        //courseIDTField.setEditable(true);
        courseIDTField.setEnabled(true);
        courseSearchButton.setVisible(true);
        courseButton.setVisible(false);
        courseCancelButton.setVisible(false);

      }
      else if (actionCommand.equals("Edit"))
      {
        courseTopicLabel.setText("Edit Course");
        //courseIDTField.setEditable(true);        
        courseIDTField.setEnabled(true);
        courseSearchButton.setVisible(true);
        courseButton.setVisible(true);
        courseCancelButton.setVisible(true);
        courseSearchButton.setVisible(true);
        courseButton.setText("Edit");
      }
    }
  }
  /**
   * Private inner class that handles the event when
   * the user clicks a button.
   */

  private class courseButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
       // Get the action command.
       String actionCommand = event.getActionCommand();

        if (actionCommand.equals("Cancel"))
        {
          clearCourseInfo();
          //courseIDTField.setEditable(true);
          courseIDTField.setEnabled(true);

          if (courseTopicLabel.getText() != "Add Course")
          {
            courseSearchButton.setVisible(true);
          }

        }
        else if (actionCommand.equals("Search"))
        {
          try 
          {
            // Call the accessor methods of courseFileManager Object 
            // to get the student's information and display it.
            int id 				 = Integer.parseInt(courseIDTField.getText());
            String name    = courseFM.getCourseName(id);
            String desc    = courseFM.getCourseDesc(id);
            int number     = courseFM.getCourseNumber(id);
            String instr   = courseFM.getCourseInstructor(id);

            courseNameTField.setText(name);
            courseDescriptionTField.setText(desc);
            courseNumberTField.setText(String.valueOf(number));
            courseInstructorTField.setText(instr);

            courseDescriptionTField.setCaretPosition(0);
            courseInstructorTField.setCaretPosition(0);

            if (courseTopicLabel.getText() == "Edit Course")
            {
              courseIDTField.setEnabled(false);
              courseSearchButton.setVisible(false);
            } 
          }
          catch (InvalidCourseID e) 
          {
            showMessage(e.getMessage(), MSG_WARNING);	          
            clearCourseInfo();
          }
          catch (NumberFormatException e)
          {
            showMessage("The Course ID and number must be integer.", MSG_WARNING);	          
            clearCourseInfo();
          }
          catch (Exception e)
          {
            showMessage(e.getMessage(), MSG_ERROR);
            clearCourseInfo();
          }
        }
        else
        {
        // Determine which button was clicked and display
        // a message.
          try 
          {
            int id 			 = Integer.parseInt(courseIDTField.getText());
            String name  = courseNameTField.getText();
            String desc  = courseDescriptionTField.getText();
            int number 	 = Integer.parseInt(courseNumberTField.getText());
            String instr = courseInstructorTField.getText();

            if (actionCommand.equals("Add"))
            {
              // Call addCourse method to add the student to the end of the ArrayList
              // and write the student information in the file. 
              courseFM.addCourse(id, name, desc, number, instr);
              showMessage("Add Course successfully.", MSG_INFO);
              clearCourseInfo();
            }
            else
            {
              // Call updateStudent method to update the student of the ArrayList
              // and write the student information in the file. 
              courseFM.updateCourse(id, name, desc, number, instr);
              showMessage("Edit Course successfully.", MSG_INFO);
            }
          }
          catch (InvalidCourseIDExist | InvalidCourseID | InvalidCourseInfo e) 
          {
            showMessage(e.getMessage(), MSG_WARNING);	          
          }
          catch (NumberFormatException e)
          {
            showMessage("The Course ID and number must be and integer.", MSG_WARNING);	          
          }
          catch (IOException e)
          {           
            showMessage(e.getMessage(), MSG_ERROR);
          }
          catch (Exception e)
          {
            showMessage(e.getMessage(), MSG_ERROR);
          }
        }
     }
  }

  /**
   *  Private inner class that handles the event that
   *  is generated when the user selects a menuItem from
   *  the enrollment menu.
   */

  private class enrollListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      // Get the action command.
      String actionCommand = e.getActionCommand();

      // Determine which button was clicked and display
      // a message.
      fileTopicLabel.setVisible(false);
      studentPanel.setVisible(false);
      coursePanel.setVisible(false);
      enrollPanel.setVisible(true);
      reportPanel.setVisible(false);
      add(enrollPanel);

      clearEnrollInfo();
      if (actionCommand.equals("Add"))
      {
        enrollTopicLabel.setText("Add Enrollment");
        enrollIDLabel.setVisible(false);
        enrollIDTField.setVisible(false);
        enrollSearchButton.setVisible(false);
        enrollButton.setVisible(true);
        enrollButton.setText("Add");
        enrollCancelButton.setVisible(true);
        enrollStudentCheckButton.setVisible(true);
        enrollCourseCheckButton.setVisible(true);

      }
      else if (actionCommand.equals("View"))
      {
        enrollTopicLabel.setText("View Enrollment");
        enrollIDLabel.setVisible(true);
        enrollIDTField.setVisible(true);
        enrollIDTField.setEnabled(true);
        enrollSearchButton.setVisible(true);
        enrollButton.setVisible(false);
        enrollCancelButton.setVisible(false);
        enrollStudentCheckButton.setVisible(false);
        enrollCourseCheckButton.setVisible(false);

      }
      else if (actionCommand.equals("Edit"))
      {
        enrollTopicLabel.setText("Edit Enrollment");
        enrollIDLabel.setVisible(true);
        enrollIDTField.setVisible(true);
        enrollIDTField.setEnabled(true);
        enrollSearchButton.setVisible(true);
        enrollButton.setVisible(true);
        enrollButton.setText("Edit");
        enrollCancelButton.setVisible(true);
        enrollStudentCheckButton.setVisible(true);
        enrollCourseCheckButton.setVisible(true);
      }
    }
  }
  /**
   * Private inner class that handles the event when
   * the user clicks a button.
   */

  private class enrollButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      String actionCommand = event.getActionCommand();

      if (actionCommand.equals("Cancel"))
      {
        clearEnrollInfo();
        enrollIDTField.setEnabled(true);
        if (enrollTopicLabel.getText() != "Add Enrollment")
        {
          enrollSearchButton.setVisible(true);
        }
      }
      else if (actionCommand.equals("Check SID"))
      {
        try 
        {
          enrollStudentNameTField.setText("");
          int sid 			 = Integer.parseInt(enrollStudentIDTField.getText());
          String fname   = studentFM.getStudentFName(sid);
          String lname   = studentFM.getStudentLName(sid);
          enrollStudentNameTField.setText(fname + " " + lname);
          enrollStudentNameTField.setCaretPosition(0);
        }
        catch (InvalidStudentID e) 
        {
          showMessage(e.getMessage(), MSG_WARNING);	          
        }
        catch (NumberFormatException e)
        {
          showMessage("The student ID must be an integer.", MSG_WARNING);	          
        }
        catch (Exception e)
        {
          showMessage(e.getMessage(), MSG_ERROR);
        }
      }
      else if (actionCommand.equals("Check CID"))
      {
        try 
        {
          enrollCourseNameTField.setText("");
          int cid 		  = Integer.parseInt(enrollCourseIDTField.getText());
          String name   = courseFM.getCourseName(cid);
          enrollCourseNameTField.setText(name);
          enrollCourseNameTField.setCaretPosition(0);
        }
        catch (InvalidCourseID e) 
        {
          showMessage(e.getMessage(), MSG_WARNING);	          
        }
        catch (NumberFormatException e)
        {
          showMessage("The Course ID must be an integer.", MSG_WARNING);	          
        }
        catch (Exception e)
        {
          showMessage(e.getMessage(), MSG_ERROR);
        }      	
      }
      else if (actionCommand.equals("Search"))
      {
        try 
        {
          // Call the accessor methods of studentFileManager Object 
          // to get the enrollment's information and display it.
          int eid 			 = Integer.parseInt(enrollIDTField.getText());
          int sid 			 = enrollmentFM.getEnrollmentSid(eid);
          int cid 			 = enrollmentFM.getEnrollmentCid(eid);
          int year       = enrollmentFM.getEnrollmentYear(eid);
          String semester= enrollmentFM.getEnrollmentSemester(eid);
          char grade		 = enrollmentFM.getEnrollmentGrade(eid);

          enrollStudentIDTField.setText(String.valueOf(sid));
          enrollCourseIDTField.setText(String.valueOf(cid));
          enrollYearTField.setText(String.valueOf(year));

          if (semester.length() > 0 )
          {
            enrollSemesterComboBox.setSelectedItem(semester);
          }
          else
          {
            enrollSemesterComboBox.setSelectedIndex(-1);;
          }
          if (grade == '\0')
          {
            enrollGradeTField.setText("");
          }
          else
          {
            enrollGradeTField.setText(Character.toString(grade));
          }
          String fname   = studentFM.getStudentFName(sid);
          String lname   = studentFM.getStudentLName(sid);

          enrollStudentNameTField.setText(fname + " " + lname);
          enrollStudentNameTField.setCaretPosition(0);

          String name   = courseFM.getCourseName(cid);

          enrollCourseNameTField.setText(name);
          enrollCourseNameTField.setCaretPosition(0);

          if (enrollTopicLabel.getText() == "Edit Enrollment")
          {
            enrollIDTField.setEnabled(false);
            enrollSearchButton.setVisible(false);
          } 
        }
        catch (InvalidEnrollmentID | InvalidStudentID | InvalidCourseID e) 
        {
          showMessage(e.getMessage(), MSG_WARNING);
          clearEnrollInfo();
        }
        catch (NumberFormatException e)
        {
          showMessage("The enrollment ID must be an integer.", MSG_WARNING);
          clearEnrollInfo();
        }
        catch (Exception e)
        {
          showMessage(e.getMessage(), MSG_ERROR);
          clearStudentInfo();
        }
      }
      else
      {
        // actionCommand.equals("Add") || actionCommand.equals("Edit")
        try 
        {
          int sid						= Integer.parseInt(enrollStudentIDTField.getText());
          int cid           = Integer.parseInt(enrollCourseIDTField.getText());
          int year          = Integer.parseInt(enrollYearTField.getText());
          Object semesterObj= enrollSemesterComboBox.getSelectedItem();
          String semester;
          if (semesterObj == null)
          {
            semester = "";
          }
          else
          {
            semester = (String)semesterObj;
          }

          String gradeStr = enrollGradeTField.getText();
          char grade;
          if (gradeStr.isEmpty())
          {
            grade = '\0';
          }
          else
          {
            grade = gradeStr.charAt(0);
          }

          if (gradeStr.length() > 1)
          {
            showMessage("The Grade field must be empty or one letter", MSG_WARNING);
          }
          else
          {
            String fname   = studentFM.getStudentFName(sid);
            String name    = courseFM.getCourseName(cid);

            if (actionCommand.equals("Add"))
            {
              // Check Course number is less than the enrollment number.
              if (enrollmentFM.getEnrollmentNumber(cid, semester, year) < 
                  courseFM.getCourseNumber(cid))
              {

              // Call addStudent method to add the student to the end of the ArrayList
              // and write the student information in the file. 
              enrollmentFM.addEnrollment(sid, cid, year, semester, grade);
              showMessage("Add Enrollment Successfully.", MSG_INFO);
              clearEnrollInfo();
              }
              else
              {
                showMessage("Add Enrollment Failed. Exceed the Course Number.", 
                            MSG_WARNING);
              }
            }
            else
            {
              if (enrollmentFM.getEnrollmentNumber(cid, semester, year) <= 
                  courseFM.getCourseNumber(cid))
              {
                int eid 				  = Integer.parseInt(enrollIDTField.getText());

                // Call updateStudent method to update the student of the ArrayList
                // and write the student information in the file. 
                enrollmentFM.updateEnrollment(eid, sid, cid, year, semester, grade);
                showMessage("Edit Enrollment successfully.", MSG_INFO);	
              }
              else
              {
                showMessage("Edit Enrollment Failed. Exceed the Course Number.", 
                            MSG_WARNING);
              }
            }
          }
        }
        catch (InvalidEnrollmentIDExist | 
               InvalidEnrollmentID | 
               InvalidEnrollmentInfo |
               InvalidStudentID | 
               InvalidCourseID e) 
        {
          showMessage(e.getMessage(), MSG_WARNING);
        }
        catch (NumberFormatException e)
        {
          showMessage("The enrollment SID, CID, and year must be integers.",
                      MSG_WARNING);
        }
        catch (IOException e)
        {           
          showMessage(e.getMessage(), MSG_ERROR);
        }
        catch (Exception e)
        {
          showMessage(e.getMessage(), MSG_ERROR);
        }
      }	  
    }
  }

  /**
   *  Private inner class that handles the event that
   *  is generated when the user selects a menuItem from
   *  the enrollment menu.
   */

  private class reportListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      // Get the action command.
      String actionCommand = e.getActionCommand();

      // Determine which button was clicked and display
      // a message.
      fileTopicLabel.setVisible(false);
      studentPanel.setVisible(false);
      coursePanel.setVisible(false);
      enrollPanel.setVisible(false);
      reportPanel.setVisible(true);

      add(reportPanel);

      clearReportInfo();

    }
  }
  /**
   * Private inner class that handles the event when
   * the user clicks a button.
   */

  private class reportButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      String actionCommand = event.getActionCommand();

      reportTextArea.setText("");

      try 
      {
        String courseName = reportCourseNameTField.getText();
        Object semesterObj= reportSemesterComboBox.getSelectedItem();
        String semesterSearch;
        if (semesterObj == null)
        {
          semesterSearch = "";
        }
        else
        {
          semesterSearch = (String)semesterObj;
        }

        int yearSearch = Integer.parseInt(reportYearTField.getText());

        if (courseName.isEmpty() || semesterSearch.isEmpty())
        {
          showMessage("The Course Name and Semester fields cannot be empty.", 
                      MSG_WARNING);
        }
        else
        {

          int[] cid = courseFM.getCourseId(courseName);
          String reportText = "";
          reportText = courseName + " " + semesterSearch + " " + yearSearch +
                  " Report:\n" + 
                 "EID  - Student Name\t: Grade\n" +
                 "---------------------------------------------------------\n";
          reportTextArea.append(reportText);

          for (int cIndex = 0; cIndex < cid.length; cIndex ++)
          {
            if(cid[cIndex] >= 0)
            {
              int[] eid = enrollmentFM.getEnrollmentId(cid[cIndex]);
              for (int eIndex = 0; eIndex < eid.length; eIndex++)
              {
                if (eid[eIndex] >= 0) 
                {
                  int sid = enrollmentFM.getEnrollmentSid(eid[eIndex]);

                  String fname = studentFM.getStudentFName(sid);
                  String lname = studentFM.getStudentLName(sid);
                  char grade   = enrollmentFM.getEnrollmentGrade(eid[eIndex]);
                  String semester = enrollmentFM.getEnrollmentSemester(eid[eIndex]);
                  int year = enrollmentFM.getEnrollmentYear(eid[eIndex]);
                  if (semester.equals(semesterSearch) && year == yearSearch)
                  {
                    if (fname.length() + lname.length() > 7)
                    {
                      reportText= String.format("%5d - %s\t: %c\n",
                                  eid[eIndex], fname + " " + lname, grade);
                    }
                    else
                    {
                      reportText= String.format("%5d - %s\t\t: %c\n", 
                                  eid[eIndex], fname + " " + lname, grade);
                    }
                    reportTextArea.append(reportText);
                  }
                }
              }
            }
          }
          reportTextArea.setCaretPosition(0);

        }
      } 
      catch (InvalidCourseName | InvalidEnrollment e)
      {
        showMessage(e.getMessage(), MSG_WARNING);
      }
      catch (NumberFormatException e)
      {
        showMessage("The Year field must be an integer.", MSG_WARNING);
      }
      catch (Exception e)
      {
        showMessage(e.getMessage(), MSG_ERROR);
      }  
    }
  }
  /**
      The main method creates an instance of the
      MenuWindow class, which causes it to display
      its window.
   */

   public static void main(String[] args)
   {
     try 
     {
       // Create a StudentFileManager object passing student as the filename.
       studentFM = new StudentFileManager("student");

       // Create a CourseFileManager object passing course as the filename.
       courseFM = new CourseFileManager("course");

       // Create a EnrollmentFileManager object passing enrollment as the filename.
       enrollmentFM = new EnrollmentFileManager("enrollment");
     } 
     catch (IOException e) 
     {
       e.printStackTrace();
     }

     new Main();
  }
}


/**
* StudentFileManager Class
*/
class StudentFileManager
{
 private ArrayList<Student> studentList;
 private String studentFileName;

 /**
  * Constructor
  * @param filename
  */
 StudentFileManager(String filename) throws IOException
 {
   // Create an ArrayList to hold some Student objects.
   studentList = new ArrayList<Student>();

   File fileStudent = new File(filename);
   studentFileName = filename;

   if (fileStudent.exists())
   {
     // Open file.
     Scanner inputFile = new Scanner(fileStudent);

     while (inputFile.hasNext())
     {
       // Read the student information from the file.
       String str = inputFile.nextLine();

       // A snippet of code to parse the comma separated fields. 
       String[] student_Array = str.split(",");

       int id       = Integer.parseInt(student_Array[0]);

       // A snippet of code to parse the space separated fields. 
       String[] name_Array = student_Array[1].split(" ");
       String fname;
       String lname;
       String addr;
       String city;
       String state;
       String zip;
       if (name_Array.length < 1) 
       {
         fname = "";
         lname = "";        	
       }
       else if (name_Array.length < 2)
       {
         fname = name_Array[0];
         lname = "";        	
       }
       else
       {
         fname = "";
         for (int i = 0; i < name_Array.length - 1; i++)
         {
           fname += name_Array[i];
           if (i < name_Array.length - 2)
           {
             fname += " "; 
           }
         }
         lname = name_Array[name_Array.length-1];
       }

       if (student_Array.length < 3)
       {
         addr = "";
       }
       else
       {
         addr = student_Array[2];
       }

       if (student_Array.length < 4)
       {
         city = "";
       }
       else
       {
         city = student_Array[3];
       }

       if (student_Array.length < 5)
       {
         state = "";
       }
       else
       {
         state = student_Array[4];
       }

       // Bug Fixed - 
       // When zip is empty, it causes student_Array[5] not to exist. 
       if (student_Array.length < 6)
       {
         zip = "";
       }
       else
       {
        zip   = student_Array[5];
       }
       // Add a student to the ArrayList.
       studentList.add(new Student(id, fname, lname, addr, city, state, zip));
     }

     // Close file.
     inputFile.close();						
   }
 }

 /**
  * addStudent method
  * Calls GetStudent method with id to check if the student with that id exist
  * in the Student ArrayList.
  * If no, then adds the student to the end of the ArrayList.
  * Opens the file and writes the ArrayList to the file. Return true.
  * If the student with id exist, Displays error message. Return false
  * @param id The student ID.
  * @param fname The student first name.
  * @param lname The student last name.
  * @param address The address.
  * @param city The city.
  * @param state The state.
  * @param zip The zip code.
  */
 public void addStudent(int id, String fname, String lname, String address,
                        String city,  String state, String zip)
                        throws Exception
 {
   // Calls getStudentInfo method with id to check if the student with 
   // that id exist in the Student ArrayList.
   Student studentInfo = getStudentInfo(id);

   // If no, then adds the student to the end of the ArrayList.
   // Opens the file and writes the ArrayList to the file.
   if (studentInfo == null)
   {
     // If the student information is empty, 
     // it throws an InvalidStudentInfo exception.
     if (id < 0) {
       throw new InvalidStudentInfo("Student ID");
     }
     if (fname.isEmpty()) {
       throw new InvalidStudentInfo("First Name");
     }
     if (lname.isEmpty()) {
       throw new InvalidStudentInfo("Last Name");
     }
     if (address.isEmpty()) {
       throw new InvalidStudentInfo("Address");
     }
     if (city.isEmpty()) {
       throw new InvalidStudentInfo("City");
     }
     if (state.isEmpty()) {
       throw new InvalidStudentInfo("State");
     }
     if (zip.isEmpty()) {
       throw new InvalidStudentInfo("Zip Code");
     }

     studentList.add(new Student(id, fname, lname, address, city, state, zip));

     FileWriter fwriter     = new FileWriter(studentFileName, false);

     PrintWriter outputFile = new PrintWriter(fwriter);

     for (int index = 0; index < studentList.size(); index++)
     {
       studentInfo = studentList.get(index);
       outputFile.print(studentInfo.getId() + ",");
       outputFile.print(studentInfo.getFName() + " ");
       outputFile.print(studentInfo.getLName() + ",");
       outputFile.print(studentInfo.getAddress() + ",");
       outputFile.print(studentInfo.getCity() + ",");
       outputFile.print(studentInfo.getState() + ",");
       outputFile.println(studentInfo.getZip());
     }      			
     outputFile.close();
   }
   else
   {
     // If the student with id exist, throw an InvalidStudentIDExist exception.
     throw new InvalidStudentIDExist(id);
   }		
 }

 /**
  * getStudentInfo method
  * Checks to see if the student with that id exist in the ArrayList.
  * If yes, then read the student info into student object and returns student object.
  * If the student with id does not exist, return null.
  * @param id
  * @return
  */
 public Student getStudentInfo(int id)
 {
   Student studentInfo = null;

   // Check to see if the student with that id exist in the ArrayList.
   for (int index = 0; index < studentList.size(); index++)
   {
     if (id == studentList.get(index).getId())
     {
       // If yes, then read the student info into student object and 
       // returns student object.
       studentInfo = studentList.get(index);
     }
   }      

   return studentInfo;
 }

 /**
  * UpdateStudent method
  * Calls GetStudent method with id to check to see if the student with that id exist
  * in the Student ArrayList.
  * If found, updates the objects in the Arraylist for that student id.
  * Writes the whole arraylist back to the file and returns true.
  * If not found, displays error message and returns false.
  * @param id The student ID.
  * @param fname The student first name.
  * @param lname The student name name.
  * @param address The address.
  * @param city The city.
  * @param state The state.
  * @param zip The zip code.
  */
 public void updateStudent(int id, String fname, String lname, String address, 
                              String city, String state, String zip)  
                              throws Exception
 {
   // Calls getStudentInfo method with id to check to see if the student with 
   // that id exist in the Student ArrayList.
   Student studentInfo = getStudentInfo(id);

   // If found, updates the objects in the Arraylist for that student id.
   // Writes the whole arraylist back to the file and returns true.
   if (studentInfo != null)
   {
     // If the student information is empty, 
     // it throws an InvalidStudentInfo exception.
     if (id < 0) {
       throw new InvalidStudentInfo("Student ID");
     }
     if (fname.isEmpty()) {
       throw new InvalidStudentInfo("First Name");
     }
     if (lname.isEmpty()) {
       throw new InvalidStudentInfo("Last Name");
     }
     if (address.isEmpty()) {
       throw new InvalidStudentInfo("Address");
     }
     if (city.isEmpty()) {
       throw new InvalidStudentInfo("City");
     }
     if (state.isEmpty()) {
       throw new InvalidStudentInfo("State");
     }
     if (zip.isEmpty()) {
       throw new InvalidStudentInfo("Zip Code");
     }

     FileWriter fwriter     = new FileWriter(studentFileName, false);

     PrintWriter outputFile = new PrintWriter(fwriter);

     for (int index = 0; index < studentList.size(); index++)
     {
       studentInfo = studentList.get(index);

       if (id == studentInfo.getId()) {
         // call the mutator methods of Student Object 
         // to set the student infomation.
         studentInfo.setFName(fname);
         studentInfo.setLName(lname);
         studentInfo.setAddress(address);
         studentInfo.setCity(city);
         studentInfo.setState(state);
         studentInfo.setZip(zip);

         // Set a Student object in the ArrayList.
         studentList.set(index, studentInfo);        	
       }

       outputFile.print(studentInfo.getId() + ",");
       outputFile.print(studentInfo.getFName() + " ");
       outputFile.print(studentInfo.getLName() + ",");
       outputFile.print(studentInfo.getAddress() + ",");
       outputFile.print(studentInfo.getCity() + ",");
       outputFile.print(studentInfo.getState() + ",");
       outputFile.println(studentInfo.getZip());
     }      			
     outputFile.close();
   }
   else
   {
     // If not found, throw an InvalidStudentID exception.
     throw new InvalidStudentID(id);
   }	
 }

 /**
  * getStudentFName method
  * @param id
  * @return
  */
 public String getStudentFName(int id) throws Exception
 {
   Student studentInfo = getStudentInfo(id);

   if (studentInfo != null)
   {
     return studentInfo.getFName();
   }
   else
   {
     throw new InvalidStudentID(id);
   }
 }

 /**
  * getStudentFName method
  * @param id
  * @return
  */
 public String getStudentLName(int id) throws Exception
 {
   Student studentInfo = getStudentInfo(id);

   if (studentInfo != null)
   {
     return studentInfo.getLName();
   }
   else
   {
     throw new InvalidStudentID(id);
   }
 }

 /**
  * getStudentAddress method
  * @param id
  * @return
  */
 public String getStudentAddress(int id) throws Exception 
 {
   Student studentInfo = getStudentInfo(id);

   if (studentInfo != null)
   {
     return studentInfo.getAddress();
   }
   else
   {
     throw new InvalidStudentID(id);
   }
 }

 /**
  * getStudentCity method
  * @param id
  * @return
  */
 public String getStudentCity(int id) throws Exception 
 {
   Student studentInfo = getStudentInfo(id);

   if (studentInfo != null)
   {
     return studentInfo.getCity();
   }
   else
   {
     throw new InvalidStudentID(id);
   }
 }

 /**
  * getStudentState method
  * @param id
  * @return
  */
 public String getStudentState(int id) throws Exception 
 {
   Student studentInfo = getStudentInfo(id);

   if (studentInfo != null)
   {
     return studentInfo.getState();
   }
   else
   {
     throw new InvalidStudentID(id);
   }
 }

 /**
  * getStudentZipCode method
  * @param id
  * @return
  */
 public String getStudentZipCode(int id) throws Exception 
 {
   Student studentInfo = getStudentInfo(id);

   if (studentInfo != null)
   {
     return studentInfo.getZip();
   }
   else
   {
     throw new InvalidStudentID(id);
   }
 }

}


/**
 * CourseFileManager Class
 */
class CourseFileManager
{
  private ArrayList<Course> courseList;
  private String courseFileName;

  /**
   * Constructor
   * @param filename The file name.
   */
  CourseFileManager(String filename) throws IOException
  {
    // Create an ArrayList to hold some Course objects.
    courseList = new ArrayList<Course>();

    File fileCourse = new File(filename);
    courseFileName = filename;

    if (fileCourse.exists())
    {
      // Open file.
      Scanner inputFile = new Scanner(fileCourse);

      while (inputFile.hasNext())
      {
        // Read the student information from the file.
        String str = inputFile.nextLine();

        // A snippet of code to parse the comma separated fields. 
        String[] course_Array = str.split(",");
        int cid            = Integer.parseInt(course_Array[0]);

        String courseName;
        String courseDesc;
        int courseNumber;
        String courseInstructor;

        if (course_Array.length < 2)
        {
          courseName = "";
        }
        else
        {
          courseName  = course_Array[1];				
        }

        // Bug Fixed - 
        // When description is empty, it causes course_Array[2] not to exist.
        if (course_Array.length < 3)
        {
          courseDesc = "";
        }
        else
        {
          courseDesc  = course_Array[2];				
        }

        if (course_Array.length < 4)
        {
          courseNumber = 0;
        }
        else
        {
          courseNumber = Integer.parseInt(course_Array[3]);			
        }

        if (course_Array.length < 5)
        {
          courseInstructor = "";
        }
        else
        {
          courseInstructor  = course_Array[4];				
        }

        // Add a Course to the ArrayList.
        courseList.add(new Course(cid, 
                           courseName, 
                           courseDesc, 
                           courseNumber, 
                           courseInstructor));
      }

      // Close file.
      inputFile.close();						
    }
  }

  /**
   * addCourse
   * Calls GetCourse method with id to check if the Course with that id exist
   * in the Course ArrayList.
   * If no, then adds the Course to the end of the ArrayList.
   * Opens the file and writes the ArrayList to the file. Return true.
   * If the Course with id exist, Displays error message. Return false
   * @param cid The Course ID.
   * @param courseName The Course name.
   * @param courseDesc The Course description.
   * @param courseNumber The Course number.
   * @param courseInstructor The Course instructor.
   */
  public void addCourse(int cid, String courseName, 
                        String courseDesc, int courseNumber, 
                        String courseInstructor) throws Exception
  {
    // Calls GetCourse method with id to check if the Course with 
    // that id exist in the Course ArrayList.
    Course courseInfo = getCourseInfo(cid);

    // If no, then adds the Course to the end of the ArrayList.
    // Opens the file and writes the ArrayList to the file. Return true.
    if (courseInfo == null)
    {

      // If the course information is invalid, it
      // throws an InvalidCourseInfo exception.
      if (cid < 0) {
        throw new InvalidCourseInfo("Course ID");
      }

      if (courseName.isEmpty()) {
        throw new InvalidCourseInfo("Course Name");
      }

      if (courseDesc.isEmpty()) {
         throw new InvalidCourseInfo("Description");
      }

      if (courseNumber <= 0) {
        throw new InvalidCourseInfo("Number");
      }

      if (courseInstructor.isEmpty()) {
        throw new InvalidCourseInfo("Instructor");
      }

      courseList.add(new Course(cid, 
                                courseName, 
                                courseDesc, 
                                courseNumber, 
                                courseInstructor));

      FileWriter fwriter     = new FileWriter(courseFileName, false);

      PrintWriter outputFile = new PrintWriter(fwriter);

      for (int index = 0; index < courseList.size(); index++)
      {
        courseInfo = courseList.get(index);
        outputFile.print(courseInfo.getCid() + ",");
        outputFile.print(courseInfo.getCourseName() + ",");
        outputFile.print(courseInfo.getCourseDesc() + ",");
        outputFile.print(courseInfo.getCourseNumber() + ",");
        outputFile.println(courseInfo.getCourseInstructor());
      }      			
      outputFile.close();
    }
    else
    {
      // If the Course with id exist, throw an InvalidCourseIDExist exception.
      throw new InvalidCourseIDExist(cid);
    }		
  }

  /**
   * getCourseInfo method
   * Checks to see if the Course with that id exist in the ArrayList.
   * If yes, then read the Course info into Course object and returns Course object.
   * If the Course with id does not exist, return null.
   * @param cid The Course ID.
   * @return
   */
  public Course getCourseInfo(int cid)
  {
    Course courseInfo = null;

    // Checks to see if the Course with that id exist in the ArrayList.
    for (int index = 0; index < courseList.size(); index++)
    {
      if (cid == courseList.get(index).getCid())
      {
        // If yes, then read the Course info into Course object and 
        // returns Course object.
        courseInfo = courseList.get(index);
      }
    }      

    return courseInfo;
  }

  /**
   * updateCourse method
   * Calls getCourseInfo method with id to check to see if the Course with 
   * that id exist in the Course ArrayList.
   * If found, updates the objects in the Arraylist for that Course id.
   * Writes the whole arraylist back to the file and returns true.
   * If not found, displays error message and returns false.
   * @param cid The Course ID.
   * @param courseName The Course name.
   * @param courseDesc The Course description.
   * @param courseNumber The Course number.
   * @param courseInstructor The Course instructor name.
   */
  public void updateCourse(int cid, String courseName, 
                           String courseDesc, int courseNumber, 
                           String courseInstructor)  throws Exception
  {
    // Calls GetCourse method with id to check to see if the Course 
    // with that id exist in the Course ArrayList.
    Course courseInfo = getCourseInfo(cid);

    // If found, updates the objects in the Arraylist for that Course id.
    // Writes the whole arraylist back to the file and returns true.
    if (courseInfo != null)
    {
      // If the course information is invalid, it
      // throws an InvalidCourseInfo exception.
      if (cid < 0) {
        throw new InvalidCourseInfo("Course ID");
      }

      if (courseName.isEmpty()) {
        throw new InvalidCourseInfo("Course Name");
      }

      if (courseDesc.isEmpty()) {
         throw new InvalidCourseInfo("Description");
      }

      if (courseNumber <= 0) {
        throw new InvalidCourseInfo("Number");
      }

      if (courseInstructor.isEmpty()) {
        throw new InvalidCourseInfo("Instructor");
      }

      FileWriter fwriter     = new FileWriter(courseFileName, false);

      PrintWriter outputFile = new PrintWriter(fwriter);

      for (int index = 0; index < courseList.size(); index++)
      {
        courseInfo = courseList.get(index);

        if (cid == courseInfo.getCid()) {
          // call the mutator methods of Course Object 
          // to set the Course infomation.
          courseInfo.setCourseName(courseName);
          courseInfo.setCourseDesc(courseDesc);
          courseInfo.setCourseNumber(courseNumber);
          courseInfo.setCourseInstructor(courseInstructor);

          // Set a Course object in the ArrayList.
          courseList.set(index, courseInfo);        	
        }

        outputFile.print(courseInfo.getCid() + ",");
        outputFile.print(courseInfo.getCourseName() + ",");
        outputFile.print(courseInfo.getCourseDesc() + ",");
        outputFile.print(courseInfo.getCourseNumber() + ",");
        outputFile.println(courseInfo.getCourseInstructor());
      }      			
      outputFile.close();
    }
    else
    {
      // If not found, throw an InvalidCourseID exception.
      throw new InvalidCourseID(cid);
    }		

  }

  /**
   * getCourseName method
   * @param id
   * @return
   */
  public String getCourseName(int id) throws Exception
  {
    Course courseInfo = getCourseInfo(id);

    if (courseInfo != null)
    {
      return courseInfo.getCourseName();
    }
    else
    {
      throw new InvalidCourseID(id);
    }
  }

  /**
   * getCourseDesc method
   * @param id
   * @return
   */
  public String getCourseDesc(int id) throws Exception
  {
    Course courseInfo = getCourseInfo(id);

    if (courseInfo != null)
    {
      return courseInfo.getCourseDesc();
    }
    else
    {
      throw new InvalidCourseID(id);
    }
  }

  /**
   * getCourseNumber method
   * @param id
   * @return
   */
  public int getCourseNumber(int id) throws Exception 
  {
    Course courseInfo = getCourseInfo(id);

    if (courseInfo != null)
    {
      return courseInfo.getCourseNumber();
    }
    else
    {
      throw new InvalidCourseID(id);
    }
  }

  /**
   * getCourseInstructor method
   * @param id
   * @return
   */
  public String getCourseInstructor(int id) throws Exception 
  {
    Course courseInfo = getCourseInfo(id);

    if (courseInfo != null)
    {
      return courseInfo.getCourseInstructor();
    }
    else
    {
      throw new InvalidCourseID(id);
    }
  }

  /**
   * getCourseId method
   * Checks to see if the Course with that name exist in the ArrayList.
   * If yes, then read the Course id into resultID array and returns it.
   * If the Course with name does not exist, throw an 
      throw new InvalidCourseName exception.
   * @param name The Course name.
   * @return
   */
  public int[] getCourseId(String name) throws Exception
  {
    Course courseInfo = null;
    boolean found = false;

    if (courseList.size() == 0)
    {
      throw new InvalidCourseName();
    }

    int[] resultID = new int[courseList.size()];;
    for (int i = 0; i < resultID.length; i++)
    {
      resultID[i] = -1;
    }

    // Checks to see if the Course with that id exist in the ArrayList.
    for (int index = 0; index < courseList.size(); index++)
    {
      if (courseList.get(index).getCourseName().equals(name))
      {
        resultID[index] = courseList.get(index).getCid();
        found = true;
      }
    }    

    if (!found)
    {
      throw new InvalidCourseName();
    }


    return resultID;
  }
}

/**
 * EnrollmentFileManager Class
 */
class EnrollmentFileManager
{
  private ArrayList<Enrollment> enrollmentList;
  private String enrollmentFileName;
  private int last_eid;
  /**
   * Constructor
   * Opens file, reads each enrollment information into ArrayList<Enrollment>, 
   * closes file
   * @param filename The file name.
   */
  EnrollmentFileManager(String filename) throws IOException
  {
    // Create an ArrayList to hold some Enrollment objects.
    enrollmentList = new ArrayList<Enrollment>();

    File fileEnrollment = new File(filename);
    enrollmentFileName = filename;
    last_eid = 0;

    if (fileEnrollment.exists())
    {
      Scanner inputFile = new Scanner(fileEnrollment);

      while (inputFile.hasNext())
      {
        // Read the student information from the file.
        String str = inputFile.nextLine();

        // A snippet of code to parse the comma separated fields. 
        String[] enrollment_Array = str.split(",");

        int eid         = Integer.parseInt(enrollment_Array[0]);
        int sid         = Integer.parseInt(enrollment_Array[1]);
        int cid         = Integer.parseInt(enrollment_Array[2]);
        int year        = Integer.parseInt(enrollment_Array[3]);

        String semester;
        char grade;	  	
        if (enrollment_Array.length < 5)
        {        	
          semester = "";
        }
        else
        {
          semester = enrollment_Array[4];
        }

        // Bug Fixed - 
        // When grade is empty, it causes enrollment_Array[5] not to exist.
        if (enrollment_Array.length < 6)
        {        	
          grade = '\0';
        }
        else
        {
          grade = enrollment_Array[5].charAt(0);		  	
        }


        // Add a enrollment to the ArrayList.
        enrollmentList.add(new Enrollment(eid, sid, cid, year, semester, grade));

        // Set the last enrollment ID for a new enrollment.
        last_eid = eid;
      }
      inputFile.close();						
    }
  }

  /**
   * addEnrollment
   * Calls GetEnrollment method with id to check if the enrollment with that id exist
   * in the enrollment ArrayList.
   * Checks if student id, Course id, year, semester exist.
   * If no, then adds the enrollment to the end of the ArrayList.
   * Opens the file and writes the ArrayList to the file. Return true.
   * If the enrollment with id exist, Displays error message. Return false
   * @param sid The student ID.
   * @param cid The Course ID.
   * @param year The year.
   * @param semester The semester.
   * @param grade The grade.
   */
  public void addEnrollment(int sid, int cid, int year, 
                            String semester, char grade) throws Exception
  {
    int eid = last_eid + 1;

    // Calls GetEnrollment method with id to check if the enrollment 
    // with that id exist in the enrollment ArrayList.
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);
    if (enrollmentInfo == null)
    {
      // Calls GetEnrollment method with student ID, Course ID, year, and
      // semester to check if the enrollment with these information exist.
      // If no, then adds the enrollment to the end of the ArrayList.
      // Open the file and writes the ArrayList to the file. Return true.
      enrollmentInfo = getEnrollmentInfo(sid, cid, year, semester);
      if (enrollmentInfo == null)
      {
        // If the enrollment information is invalid, it
        // throws an InvalidCourseInfo exception.
        if (sid < 0) {
          throw new InvalidEnrollmentInfo("Student ID");
        }

        if (cid < 0) {
          throw new InvalidEnrollmentInfo("Course ID");
        }

        if (year <= 0) {
          throw new InvalidEnrollmentInfo("Year");
        }

        if (semester.length() == 0)
        {
          throw new InvalidEnrollmentInfo("Semester");
        }

        enrollmentList.add(new Enrollment(eid, sid, cid, year, semester, grade));

        FileWriter fwriter     = new FileWriter(enrollmentFileName, false);

        PrintWriter outputFile = new PrintWriter(fwriter);

        for (int index = 0; index < enrollmentList.size(); index++)
        {
          enrollmentInfo = enrollmentList.get(index);
          outputFile.print(enrollmentInfo.getEid() + ",");
          outputFile.print(enrollmentInfo.getSid() + ",");
          outputFile.print(enrollmentInfo.getCid() + ",");
          outputFile.print(enrollmentInfo.getYear() + ",");
          outputFile.print(enrollmentInfo.getSemester() + ",");
          outputFile.println(enrollmentInfo.getGrade());
        }      			
        outputFile.close();

        // Update last_eid to the last enrollment ID.
        last_eid = eid;
      }
      else
      {
        // If the enrollment with id exist, throw an InvalidEnrollIDExist exception.
        throw new InvalidEnrollmentIDExist();
        // If the enrollment with these information exist, display error message.
        //System.out.println("ERROR! The enrollment with the student ID, " +
        //                   "course ID, year, and semester already exist.");
      }		
    }
    else
    {
      // If the enrollment with id exist, throw an InvalidEnrollIDExist exception.
      throw new InvalidEnrollmentIDExist();
      // If the enrollment with id exist, displays error message.
      //System.out.println("ERROR! The enrollment with the ID already exist.");			
    }
  }

  /**
   * getEnrollmentInfo method
   * Checks to see if the enrollment with student ID, course ID, year, and 
   * semester exist in the ArrayList.
   * If yes, then read the enrollment info into enrollment object and 
   * returns enrollment object.
   * If the enrollment with id does not exist, return null.
   * @param sid The student ID.
   * @param cid The Course ID.
   * @param year The year.
   * @param semester The semester.
   * @return Enrollment Object
   */
  public Enrollment getEnrollmentInfo(int sid, int cid, int year, String semester)
  {
    Enrollment enrollmentInfo = null;

    // Checks to see if the enrollment with student ID, course ID, year, and 
    // semester exist in the ArrayList.
    for (int index = 0; index < enrollmentList.size(); index++)
    {
      if (sid == enrollmentList.get(index).getSid() &&
          cid == enrollmentList.get(index).getCid() && 
          year == enrollmentList.get(index).getYear() &&
          semester.equals(enrollmentList.get(index).getSemester()))
      {
        // If yes, then read the enrollment info into enrollment object and 
        // returns enrollment object.
        enrollmentInfo = enrollmentList.get(index);
      }
    }      

    return enrollmentInfo;
  }

  /**
   * getEnrollmentInfo method
   * Checks to see if the enrollment with the ID exist in the ArrayList.
   * If yes, then read the enrollment info into enrollment object and 
   * returns enrollment object.
   * If the enrollment with id does not exist, return null.
   * @param eid The enrollment ID.
   * @return Enrollment Object
   */
  public Enrollment getEnrollmentInfo(int eid)
  {
    Enrollment enrollmentInfo = null;

    // Checks to see if the enrollment with the ID exist in the ArrayList.
    for (int index = 0; index < enrollmentList.size(); index++)
    {
      if (eid == enrollmentList.get(index).getEid())
      {
        // If yes, then read the enrollment info into enrollment object and 
        // returns enrollment object.
        enrollmentInfo = enrollmentList.get(index);
      }
    }      

    return enrollmentInfo;
  }

  /**
   * updateEnrollment method
   * Calls GetEnrollment method with id to check to see if the enrollment with 
   * that id exist in the enrollment ArrayList.
   * If found, updates the objects in the Arraylist for that enrollment id.
   * Writes the whole arraylist back to the file and returns true.
   * If not found, displays error message and returns false.
   * @param eid The enrollment ID.
   * @param sid The student ID.
   * @param cid The Course ID.
   * @param year The year.
   * @param semester The semester.
   * @param grade The grade.
   * @return result
   */
  public void updateEnrollment(int eid, int sid, int cid, int year, 
                               String semester, char grade) throws Exception
  {
    boolean isEditGrade = false;

    // Calls getEnrollmentInfo method with id to check to see if 
    //the enrollment with that ID exist in the enrollment ArrayList.
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);

    // If found, check edit grade only and the enrollment with 
    // other information exist.
    if (enrollmentInfo != null)
    {
      // If the enrollment information is invalid, it
      // throws an InvalidCourseInfo exception.
      if (sid < 0) {
        throw new InvalidEnrollmentInfo("Student ID");
      }

      if (cid < 0) {
        throw new InvalidEnrollmentInfo("Course ID");
      }

      if (year <= 0) {
        throw new InvalidEnrollmentInfo("Year");
      }

      if (semester.length() == 0)
      {
        throw new InvalidEnrollmentInfo("Semester");
      }

      // If the user want to edit grade only. 
      if (sid == enrollmentInfo.getSid() &&
          cid == enrollmentInfo.getCid() &&
          year == enrollmentInfo.getYear() &&
          semester.equals(enrollmentInfo.getSemester())) 
      {
        isEditGrade = true;
      }

      // Calls GetEnrollment method with id to check to see if the enrollment with 
      // the student ID, Course ID, year, and semester exist 
      // in the enrollment ArrayList.
      enrollmentInfo = getEnrollmentInfo(sid, cid, year, semester);

      // If user want to edit grade only, or no found, 
      // writes the whole arraylist back to the file and returns true.
      if (isEditGrade == true || enrollmentInfo == null)
      {
        FileWriter fwriter     = new FileWriter(enrollmentFileName, false);

        PrintWriter outputFile = new PrintWriter(fwriter);

        for (int index = 0; index < enrollmentList.size(); index++)
        {
          enrollmentInfo = enrollmentList.get(index);

          if (eid == enrollmentInfo.getEid()) {
            // call the mutator methods of Enrollment Object 
            // to set the enrollment infomation.
            enrollmentInfo.setSid(sid);
            enrollmentInfo.setCid(cid);
            enrollmentInfo.setYear(year);
            enrollmentInfo.setSemester(semester);
            enrollmentInfo.setGrade(grade);

            // Set a Enrollment object in the ArrayList.
            enrollmentList.set(index, enrollmentInfo);        	
          }

          outputFile.print(enrollmentInfo.getEid() + ",");
          outputFile.print(enrollmentInfo.getSid() + ",");
          outputFile.print(enrollmentInfo.getCid() + ",");
          outputFile.print(enrollmentInfo.getYear() + ",");
          outputFile.print(enrollmentInfo.getSemester() + ",");
          outputFile.println(enrollmentInfo.getGrade());
        }      			
        outputFile.close();
      }
      else
      {
        // If not found, throw an InvalidStudentID exception.
        throw new InvalidStudentIDExist(eid);
        // If found, displays error message and returns false.
        //System.out.println("ERROR! The enrollment with the student ID, " +
        //                   "course ID, year, and semester already exist.");
      }
    }
    else
    {
      // If not found, throw an InvalidStudentID exception.
      throw new InvalidStudentID(eid);
      // If no found, displays error message and returns false.
      //System.out.println("ERROR! The enrollment with the ID does not exist.");
    }
  }

  /**
   * getEnrollmentSid method
   * @param eid
   * @return the sid field
   */
  public int getEnrollmentSid(int eid) throws Exception
  {
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);

    if (enrollmentInfo != null)
    {
      return enrollmentInfo.getSid();
    }
    else
    {
      throw new InvalidEnrollmentID(eid);
    }
  }

  /**
   * getEnrollmentCid method
   * @param eid
   * @return the cid field
   */
  public int getEnrollmentCid(int eid) throws Exception
  {
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);

    if (enrollmentInfo != null)
    {
      return enrollmentInfo.getCid();
    }
    else
    {
      throw new InvalidEnrollmentID(eid);
    }
  }

  /**
   * getEnrollmentYear method
   * @param eid
   * @return the year field
   */
  public int getEnrollmentYear(int eid) throws Exception
  {
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);

    if (enrollmentInfo != null)
    {
      return enrollmentInfo.getYear();
    }
    else
    {
      throw new InvalidEnrollmentID(eid);
    }
  }

  /**
   * getEnrollmentSemester method
   * @param eid
   * @return the semester field
   */
  public String getEnrollmentSemester(int eid) throws Exception
  {
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);

    if (enrollmentInfo != null)
    {
      return enrollmentInfo.getSemester();
    }
    else
    {
      throw new InvalidEnrollmentID(eid);
    }
  }

  /**
   * getEnrollmentGrade method
   * @param eid
   * @return the grade field
   */
  public char getEnrollmentGrade(int eid) throws Exception
  {
    Enrollment enrollmentInfo = getEnrollmentInfo(eid);

    if (enrollmentInfo != null)
    {
      return enrollmentInfo.getGrade();
    }
    else
    {
      throw new InvalidEnrollmentID(eid);
    }
  }

  /**
   * getEnrollmentId method
   * Checks to see if the Emrollment with the cid exist in the ArrayList.
   * If yes, then read the emrollment id into resultID array and returns it.
   * If the Course with ID does not exist, throw an InvalidEnrollment exception.
   * @param cid The Course ID.
   * @return
   */
  public int[] getEnrollmentId(int cid) throws Exception
  {
    Enrollment enrollmentInfo = null;

    if (enrollmentList.size() == 0)
    {
      throw new InvalidEnrollment();
    }

    int[] resultEid = new int[enrollmentList.size()];;
    for (int i = 0; i < resultEid.length; i++)
    {
      resultEid[i] = -1;
    }

    // Checks to see if the Course with that id exist in the ArrayList.
    for (int index = 0; index < enrollmentList.size(); index++)
    {
      if (cid == enrollmentList.get(index).getCid())
      {
        resultEid[index] = enrollmentList.get(index).getEid();
      }
    }    

    /*
    if (resultEid[0] == -1)
    {
      throw new InvalidEnrollment();
    }
     */
    return resultEid;
  }

  /**
   * getEnrollmentNumber method
   * Checks to see if the Emrollment with the cid exist in the ArrayList.
   * If yes, then read the emrollment number with the course id returns it.
   * If the enrollment does not exist, throw an InvalidEnrollment exception. 
   * @param cid The Course ID.
   * @return
   */
  public int getEnrollmentNumber(int cid, String Semester, int year) throws Exception
  {
    Enrollment enrollmentInfo = null;
    int resultNumber = 0;       

    // Checks to see if the Course with that id exist in the ArrayList.
    for (int index = 0; index < enrollmentList.size(); index++)
    {
      if (cid == enrollmentList.get(index).getCid() &&
          enrollmentList.get(index).getSemester().equals(Semester) &&
          year == enrollmentList.get(index).getYear())
      {
        resultNumber++;
      }
    }    

    return resultNumber;
  }
}

/**
 * Student Class
 */
class Student
{
  private int id;
  private String fname;
  private String lname;
  private String address;
  private String city;
  private String state;
  private String zip;

  /**
   * Constructor
   * @param i The student ID.
   * @param f The student first name.
   * @param l The student last name.
   * @param a The address.
   * @param c The city.
   * @param s The state.
   * @param z The zip code.
   */
  Student(int i, String f, String l, String a, String c, String s, String z)
  {
    id      = i;
    fname   = f;
    lname   = l;
    address = a;
    city    = c;
    state   = s;
    zip     = z;
  }

  /**
   * The getId method returns student ID.
   * @return The value in the id field.
   */
  public int getId()
  {
    return id;
  }

  /**
   * The getFName method returns student first name.
   * @return The value in the fname field.
   */
  public String getFName()
  {
    return fname;
  }

  /**
   * The getLName method returns student last name.
   * @return The value in the lname field.
   */
  public String getLName()
  {
    return lname;
  }

  /**
   * The getAddress method returns address.
   * @return The value in the address field.
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * The getCity method returns city.
   * @return The value in the city field.
   */
  public String getCity()
  {
    return city;
  }

  /**
   * The getState method returns state.
   * @return The value in the state field.
   */	
  public String getState()
  {
    return state;
  }

  /**
   * The getZip method returns zip code.
   * @return The value in the zip field.
   */	
  public String getZip()
  {
    return zip;
  }

  /**
   * The setId method sets the id field.
   * @param i The student ID.
   */
  public void setId(int i)
  {
    id = i;
  }

  /**
   * The setFName method sets the fname field.
   * @param n The student first name.
   */
  public void setFName(String f)
  {
    fname = f;
  }

  /**
   * The setLName method sets the lname field.
   * @param n The student last name.
   */
  public void setLName(String l)
  {
    lname = l;
  }

  /**
   * The setAddress method sets the address field.
   * @param a The address.
   */
  public void setAddress(String a)
  {
    address = a;
  }

  /**
   * The setCity method sets the city field.
   * @param c The city.
   */
  public void setCity(String c)
  {
    city = c;
  }

  /**
   * The setState method sets the state field.
   * @param s The state.
   */
  public void setState(String s)
  {
    state = s;
  }

  /**
   * The setZip method sets the zip field.
   * @param z The zip code.
   */
  public void setZip(String z)
  {
    zip = z;
  }
}

/**
 * Course Class
 */
class Course
{
  private int cid;
  private String courseName;
  private String courseDesc;
  private int courseNumber;
  private String courseInstructor;

  /**
   * Constructor
   * @param c The Course ID.
   * @param n The Course name.
   * @param d The Course description.
   */	
  Course(int c, String n, String d, int num, String i)
  {
    cid        = c;
    courseName = n;
    courseDesc = d;
    courseInstructor = i;
    courseNumber = num;
  }

  /**
   * The getCId method returns Course ID.
   * @return The value in the cid field.
   */
  public int getCid()
  {
    return cid;
  }

  /**
   * The getCourseName method returns Course name.
   * @return The value in the courseName field.
   */
  public String getCourseName()
  {
    return courseName;
  }

  /**
   * The getCourseDesc method returns Course description.
   * @return The value in the courseDesc field.
   */
  public String getCourseDesc()
  {
    return courseDesc;
  }

  /**
   * The getCourseNumber method returns Course number.
   * @return The value in the courseNumber field.
   */
  public int getCourseNumber()
  {
    return courseNumber;
  }

  /**
   * The getCourseInstructor method returns Course instructor name.
   * @return The value in the courseInstructor field.
   */
  public String getCourseInstructor()
  {
    return courseInstructor;
  }

  /**
   * The setCId method sets the cid field.
   * @param c The course ID.
   */
  public void setCId(int c)
  {
    cid = c;
  }

  /**
   * The setId method sets the courseName field.
   * @param n The Course name.
   */
  public void setCourseName(String n)
  {
    courseName = n;
  }

  /**
   * The setId method sets the courseDesc field.
   * @param d The Course description.
   */
  public void setCourseDesc(String d)
  {
    courseDesc = d;
  }

  /**
   * The setCourseName method sets the courseNumber field.
   * @param c The course number.
   */
  public void setCourseNumber(int num)
  {
    courseNumber = num;
  }

  /**
   * The setCourseInstructor method sets the courseInstructor field.
   * @param d The Course instructor.
   */
  public void setCourseInstructor(String i)
  {
    courseInstructor = i;
  }
}

/**
 * Course Enrollment
 */
class Enrollment
{
  private int eid;
  private int sid;
  private int cid;
  private int year;
  private String semester;
  private char grade;

  /**
   * Constructor
   * @param e The enrollment ID.
   * @param s The student ID.
   * @param c The Course ID.
   * @param y The semester.
   * @param sem The year.
   * @param i The instructor.
   * @param g The grade.
   */	

  Enrollment(int e, int s, int c, int y, String sem, char g)
  {
    eid      = e;
    sid      = s;
    cid 		 = c;
    year     = y;
    semester = sem;
    grade    = g;
  }

  /**
   * The getEid method returns enrollment ID.
   * @return The value in the eid field.
   */
  public int getEid()
  {
    return eid;
  }

  /**
   * The getSid method returns student ID.
   * @return The value in the sid field.
   */
  public int getSid()
  {
    return sid;
  }

  /**
   * The getCid method returns Course ID.
   * @return The value in the cid field.
   */
  public int getCid()
  {
    return cid;
  }

  /**
   * The getYear method returns year.
   * @return The value in the year field.
   */
  public int getYear()
  {
    return year;
  }

  /**
   * The getSemester method returns semester.
   * @return The value in the semester field.
   */
  public String getSemester()
  {
    return semester;
  }

  /**
   * The getGrade method returns grade.
   * @return The value in the grade field.
   */
  public char getGrade()
  {
    return grade;
  }

  /**
   * The setEId method sets the eid field.
   * @param e The enrollment ID.
   */
  public void setEid(int e)
  {
    eid = e;
  }

  /**
   * The setSId method sets the sid field.
   * @param s The student ID.
   */
  public void setSid(int s)
  {
    sid = s;
  }

  /**
   * The setCId method sets the cid field.
   * @param c The course ID.
   */
  public void setCid(int c)
  {
    cid = c;
  }

  /**
   * The setYear method sets the year field.
   * @param y The year.
   */
  public void setYear(int y)
  {
    year = y;
  }

  /**
   * The setSemester method sets the semester field.
   * @param sem The semester.
   */
  public void setSemester(String sem)
  {
    semester = sem;
  }

  /**
   * The setGrade method sets the grade field.
   * @param g The grade.
   */
  public void setGrade(char g)
  {
    grade = g;
  }
}

/**
 * InvalidStudentID exceptions are thrown by the StudentFileManager class 
 * when a invalid student ID is passed to the constructor.
 */
class InvalidStudentID extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidEmployeeNumber when the number is invalid.
   */
  public InvalidStudentID()
  {
    super("The student with the ID does not exist.");
  }

  /**
   * This constructor specifies the id in the error message.
   * @param student id.
   * @exception InvalidStudentID when the id is invalid.
   */
  public InvalidStudentID(int id)
  {
    super("The student with the ID [" + id + "] does not exist.");
  }
}

/**
 * public class InvalidStudentIDExist extends Exception exceptions 
 * are thrown by the StudentFileManager class 
 * when an exist student ID is added.
 */
class InvalidStudentIDExist extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidStudentIDExist when the student id is exist.
   */
  public InvalidStudentIDExist()
  {
    super("The student with the ID already exists.");
  }

  /**
   * This constructor specifies the id in the error message.
   * @param student id.
   * @exception InvalidStudentIDExist when the student id is exist..
   */
  public InvalidStudentIDExist(int id)
  {
    super("The student with the ID [" + id + "] already exists.");
  }
}

/**
 * public class InvalidStudentIDExist extends Exception exceptions 
 * are thrown by the StudentFileManager class 
 * when an exist student ID is added or updated.
 */
class InvalidStudentInfo extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidStudentInfo when the student info is invalid.
   */
  public InvalidStudentInfo()
  {
    super("The student information is invalid.");
  }

  /**
   * This constructor uses a generic error message.
   * @exception InvalidStudentInfo when the student info is invalid.
   */
  public InvalidStudentInfo(String info)
  {
    super("The " + info + " field is invalid.");
  }
}
/**
 * InvalidCourseID exceptions are thrown by the CourseFileManager class 
 * when a invalid course ID is passed to the constructor.
 */
class InvalidCourseID extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidCourseID when the Course ID is invalid.
   */
  public InvalidCourseID()
  {
    super("The Course with the ID does not exist.");
  }

  /**
   * This constructor specifies the id in the error message.
   * @param course id.
   * @exception InvalidCourseID when the id is invalid.
   */
  public InvalidCourseID(int id)
  {
    super("The Course with the ID [" + id + "] does not exist.");
  }
}


/**
 * public class InvalidCourseIDExist extends Exception exceptions 
 * are thrown by the CourseFileManager class 
 * when an exist Course ID is added.
 */
class InvalidCourseIDExist extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidCourseIDExist when the Course id is exist.
   */
  public InvalidCourseIDExist()
  {
    super("The Course with the ID already exists.");
  }

  /**
   * This constructor specifies the id in the error message.
   * @param Course id.
   * @exception InvalidCourseIDExist when the Course id is exist..
   */
  public InvalidCourseIDExist(int id)
  {
    super("The Course with the ID [" + id + "] already exists.");
  }
}

/**
 * public class InvalidCourseInfo extends Exception exceptions 
 * are thrown by the InvalidCourseInfo class 
 * when an exist student ID is added or updated.
 */
class InvalidCourseInfo extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidStudentInfo when the Course info is invalid.
   */
  public InvalidCourseInfo()
  {
    super("The Course information is invalid.");
  }

  /**
   * This constructor uses a generic error message.
   * @exception InvalidCourseInfo when the Course info is invalid.
   */
  public InvalidCourseInfo(String info)
  {
    super("The " + info + " field is invalid.");
  }
}

/**
 * InvalidCourseID exceptions are thrown by the CourseFileManager class 
 * when a invalid course ID is passed to the constructor.
 */
class InvalidCourseName extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidCourseID when the Course ID is invalid.
   */
  public InvalidCourseName()
  {
    super("The Course with the name does not exist.");
  }
}

/**
 * InvalidEnrollmentID exceptions are thrown by the EnrollmentFileManager class 
 * when a invalid enrollment ID is passed to the constructor.
 */
class InvalidEnrollmentID extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidEnrollmentID when the enrollment ID is invalid.
   */
  public InvalidEnrollmentID()
  {
    super("The Enrollment with the ID does not exist.");
  }

  /**
   * This constructor specifies the id in the error message.
   * @param course id.
   * @exception InvalidCourseID when the id is invalid.
   */
  public InvalidEnrollmentID(int id)
  {
    super("The Enrollment with the ID [" + id + "] does not exist.");
  }
}

/**
 * public class InvalidEnrollmentIDExist extends Exception exceptions 
 * are thrown by the EnrollmentFileManager class 
 * when an exist enrollment ID is added.
 */
class InvalidEnrollmentIDExist extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidEnrollmentIDExist when the Enrollment id is exist.
   */
  public InvalidEnrollmentIDExist()
  {
    super("The Enrollment with the SID, CID, Year and Semester already exists.");
  }
}

/**
 * public class InvalidEnrollmentInfo extends Exception exceptions 
 * are thrown by the enrollmentFileManager class 
 * when an exist student ID is added or updated.
 */
class InvalidEnrollmentInfo extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidEnrollmentInfo when the enrollment info is invalid.
   */
  public InvalidEnrollmentInfo()
  {
    super("The Enrollment information is invalid.");
  }

  /**
   * This constructor uses a generic error message.
   * @exception InvalidEnrollmentInfo when the enrollment info is invalid.
   */
  public InvalidEnrollmentInfo(String info)
  {
    super("The " + info + " field is invalid.");
  }
}

/**
 * InvalidEnrollmentID exceptions are thrown by the EnrollmentFileManager class 
 * when a invalid enrollment ID is passed to the constructor.
 */
class InvalidEnrollment extends Exception
{
  /**
   * This constructor uses a generic error message.
   * @exception InvalidEnrollmentID when the enrollment ID is invalid.
   */
  public InvalidEnrollment()
  {
    super("The Enrollment with the Course ID does not exist.");
  }
}