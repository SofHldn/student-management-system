# student-management-system

## endpoints

POST: /students 
JSONBODY: {
  "firstName" : "firstname",
	"lastName" : "lastname", 
	"email" : "email@email.se",
	"phoneNumber" : "555-555"
}
* Creates new Student, Student object as a parameter

GET: /students/1
* Gets the student with id 1, Long (PathParam) as parameter

GET: /students/lastname
QUERY: lastName : "lastname"
* Gets all the students with lastname lastname, String (QueryParam) as parameter

GET: /students
* Gets all students in database, no parameter

PUT: /students
JSONBODY: {
  "id" : 1,
  "firstName" : "firstname",
	"lastName" : "lastname", 
	"email" : "email@email.se",
	"phoneNumber" : "555-555"
}
* Updates everything in student with id 1, Student object as a parameter

PATCH: /students/1
JSONBODY: {
	"lastName" : "New Lastname"
}
* Updates the lastname of student with id 1 to New Lastname, Long (PathParam) and JsonObject as parameter

DELETE: /students/1
* Removes the student with id 1 from database, Long (PathParam) as parameter


## Problems
I had some problems with PATCH to receive correct input to send through to my updateLastName-method. Also had problems with creating my own exception.
