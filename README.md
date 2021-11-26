# student-management-system

## endpoints

URL: http://localhost:8080/student-management-system/api/v1

POST: /students
JSONBODY: {
"firstName" : "firstname",
"lastName" : "lastname",
"email" : "email@email.se",
"phoneNumber" : "555-555"
}
* Creates new Student, Student object as a parameter

POST: /teachers
JSONBODY: {
"firstName" : "firstname",
"lastName" : "lastname",
"email" : "email@email.se"
}
* Creates new Teacher, Teacher object as a parameter

POST: /subjects
JSONBODY: {
"subjectName" : "math"
}
* Creates new Subject, Subject object as a parameter

GET: /students/subjects/11
* Shows all subjects for student with ID 11, Long (PathParam) as parameter

GET: /students
* Gets all students in database, no parameter

GET: /teachers
* Gets all teachers in database, no parameter

GET: /subjects
* Gets all subjects in database - with list of students taking the class & teacher, no parameter

## Problems
I had some problems with creating the Joint-table for the Many-to-many relation between subjects and students. Discussed problems with Andreas and solved it together.




