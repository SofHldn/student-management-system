package se.iths.rest;
import se.iths.entity.Student;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;


    @Path("")
    @POST
    public Response createStudent(Student student){
        if(student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()){
            String responseMessage = "{\"Firstname, lastname and email can not be empty!\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseMessage).build());
        }
        studentService.createStudent(student);
        return Response.status(201).entity(student).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {

        Student foundStudent = studentService.findStudentById(id);

        if(foundStudent == null){
            String responseMessage = responseMessage(id);
            throw new StudentNotFoundException(responseMessage);
        }
        return Response.status(302).entity(foundStudent).build();
    }

    @Path("/subjects/{id}")
    @GET
    public Response getStudentSubjects(@PathParam("id") Long id) {

        Student foundStudent = studentService.findStudentById(id);

        if(foundStudent == null){
            String responseMessage = responseMessage(id);
            throw new StudentNotFoundException(responseMessage);
        }

        List<String> subjectList = studentService.getSubjectTitlesPerStudent(id);
        return Response.status(302).entity("{ \"Student with id " + id + " is listed on following classes: " + subjectList +"\"}").build() ;
    }

    @Path("lastname")
    @GET
    public Response getStudentByLastname(@QueryParam("lastname") String lastname){
        List<Student> foundStudents = studentService.findStudentByLastName(lastname);
        if(foundStudents.isEmpty()){
            String responseMessage = "{ \"No students with lastname " + lastname + " were found in database.\"}";
            throw new StudentNotFoundException(responseMessage);
        }
        return Response.status(302).entity(foundStudents).build();
    }

    @Path("")
    @GET
    public Response listAllStudents() {
        List <Student> foundStudents = studentService.listAllStudents();

        if(foundStudents.isEmpty()){
            String responseMessage = "{ \"There are no Students registered in the database.\"}";
            throw new StudentNotFoundException(responseMessage);
        }

        return Response.status(302).entity(foundStudents).build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student){

        if(student.getId()==null){
            String responseMessage = "{ \"You need to specify ID number on Student to be updated \"}";
            throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity(responseMessage).build());
        }else if(studentService.findStudentById(student.getId()) == null){
            String responseMessage = responseMessage(student.getId());
            throw new StudentNotFoundException(responseMessage);
        }
            studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, JsonObject lastName) {

        if(studentService.findStudentById(id) == null){
            String responseMessage = responseMessage(id);
            throw new StudentNotFoundException(responseMessage);
        }
        Student updatedStudent = studentService.updateLastName(id, lastName.getString("lastName"));
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){

        String responseMessage;
        if(studentService.findStudentById(id) == null){
            responseMessage = responseMessage(id);
            throw new StudentNotFoundException(responseMessage);
        }
        studentService.deleteStudent(id);
        responseMessage = "{\"Student with ID " + id + " was successfully removed.\"}";
        return Response.status(301).entity(responseMessage).build();


    }

    public String responseMessage(Long id){
        return "{ \"There is no Student with id " + id + " in database. \"}";
    }


}
