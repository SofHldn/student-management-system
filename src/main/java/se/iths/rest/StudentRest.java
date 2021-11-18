package se.iths.rest;


import se.iths.entity.Student;
import se.iths.exception.MyException;
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
        studentService.createStudent(student);
        return Response.status(201).entity(student).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {

        Student foundStudent = studentService.findStudentById(id);
        String responseMessage = "{\"Student with ID " + id + " was not found in database.\"}";

        if(foundStudent == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
        }
        return Response.status(302).entity(foundStudent).build();
    }

    @Path("lastname")
    @GET
    public Response getStudentByLastname(@QueryParam("lastname") String lastname){
        List<Student> foundStudents = studentService.findStudentByLastName(lastname);

        String responseMessage = "{ \"No students with lastname " + lastname + " were found in database.\"}";

        if(foundStudents.isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
        }
        return Response.status(302).entity(foundStudents).build();
    }

    @Path("")
    @GET
    public Response listAllStudents() {
        List <Student> foundStudents = studentService.listAllStudents();

        if(foundStudents.isEmpty()){
            String responseMessage = "{ \"There are no Students registered in the database.\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
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
            String responseMessage = "{ \"There is no Student with id " + student.getId() + " in database. \"}";
            throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity(responseMessage).build());
        }
            studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, JsonObject lastName) {

        if(studentService.findStudentById(id) == null){
            String responseMessage = "{ \"There is no Student with id " + id + " in database. \"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
        }
        Student updatedStudent = studentService.updateLastName(id, lastName.getString("lastName"));
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){

        String responseMessage;
        try{
            responseMessage = "{\"Student with ID " + id + " was successfully removed.\"}";
            studentService.deleteStudent(id);
            return Response.status(301).entity(responseMessage).build();
        }catch (Exception err){
            responseMessage = "Student with ID " + id + " was not found in database.";
            throw new MyException(responseMessage);
        }


//        Student foundStudent = studentService.findStudentById(id);
//        String responseMessage;
//        if(foundStudent == null){
//            responseMessage = "{\"Student with ID " + id + " was not found in database.\"}";
////            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
//            throw new MyException(responseMessage, IllegalArgumentException);
//        }
//        responseMessage = "{\"Student with ID " + id + " was successfully removed.\"}";
//        studentService.deleteStudent(id);

    }


}
