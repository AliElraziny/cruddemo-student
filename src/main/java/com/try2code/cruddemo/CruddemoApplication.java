package com.try2code.cruddemo;

import com.try2code.cruddemo.dao.StudentDAO;
import com.try2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}



	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){


		return runner -> {

			// createStudent(studentDAO);
			//createMultibleStudent(studentDAO);
			// readStudent(studentDAO);
			//queryForStudent(studentDAO);
			//queryForStudentByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			deleteAllStudent(studentDAO);
		};

	}



	private void createStudent(StudentDAO studentDAO){
		//create the student object
		System.out.println("creating student ....");
		Student tempStudent = new Student("Omar" , "Ahmed" , "oma@gmail.com");
		//save the student object

		System.out.println("saving student ...");
		studentDAO.save(tempStudent);

		//display id of saved student
		System.out.println("id of student ..." + tempStudent.getId());
	}


	private void createMultibleStudent(StudentDAO studentDAO){
		//create the student object
		System.out.println("creating student ....");
		Student tempStudent1 = new Student("Omar" , "Ahmed" , "oma@gmail.com");
		Student tempStudent2 = new Student("Adel" , "Ibrahem" , "hao@gmail.com");
		Student tempStudent3 = new Student("Wael" , "Sanad" , "kao@gmail.com");
		//save the student object

		System.out.println("saving student ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		//display id of saved student
		System.out.println("id of student 1 ..." + tempStudent1.getId() + "id of student 2 ..." + tempStudent2.getId());
	}
	private void readStudent (StudentDAO studentDAO){
		//create the student object
		System.out.println("creating student ....");
		Student tempStudent = new Student("Omar" , "Ahmed" , "oma@gmail.com");
		//save the student object

		System.out.println("saving student ...");
		studentDAO.save(tempStudent);

		int theId = tempStudent.getId();
		//display id of saved student
		System.out.println("id of student ..." + theId);

		//retrieve Student
		System.out.println("retrieve Student ...");
		Student myStudent =studentDAO.findById(theId);
		System.out.println("found student..:"+ myStudent);

	}

	private void queryForStudent(StudentDAO studentDAO){

		//get list off Students
		List<Student> theStudents = studentDAO.findAll();

		//display the list
		for (int i =0 ; i<theStudents.size();i++){
			System.out.println("Student :  " + theStudents.get(i).getId() + theStudents.get(i));
		}
		//another way to display
		for(Student tempStudent : theStudents){

			System.out.println(tempStudent);
		}
	}
	private void queryForStudentByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Ahmed");

		for(Student tempStudent : theStudents){

			System.out.println(tempStudent);
		}

	}
	private void updateStudent(StudentDAO studentDAO) {

		//retrieve Student
			int studentId = 1;
			System.out.println("retrieve Student 1");
			Student tempStudent = studentDAO.findById(studentId);
		//change firstName
			System.out.println("updating ...");
			tempStudent.setFirstName("Saad");
		//update
			studentDAO.update(tempStudent);
		//display
			System.out.println("display updated student"  + tempStudent);


	}


	private void deleteStudent(StudentDAO studentDAO) {

		int studentId = 2;
		System.out.println("delete Student");
		studentDAO.delete(studentId);
	}

	private void deleteAllStudent(StudentDAO studentDAO) {

		System.out.println("num of deleting rows ");
		int numOfStudentDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row "+numOfStudentDeleted);
	}



}
