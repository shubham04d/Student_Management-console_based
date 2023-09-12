package student_related;

public class Student {

		String stud_name;
		String classroom;
		int age;
		
		public Student(String stud_name, String classroom, int age) 
		{
			this.stud_name = stud_name;
			this.classroom = classroom;
			this.age = age;
		}

		public String stud_name() {
			return stud_name;
		}

		public String classroom() {
			return classroom;
		}

		public int age() {
			return age;
		}
	
}
