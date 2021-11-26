package se.iths.rest;


import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path ("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher (Teacher teacher){
        if(teacher.getFirstName().isEmpty() || teacher.getLastName().isEmpty() || teacher.getEmail().isEmpty()){
            String responseMessage = "{\"Firstname, lastname and email can not be empty!\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseMessage).build());
        }
        teacherService.createTeacher(teacher);
        return Response.status(201).entity(teacher).build();
    }

    @Path("")
    @GET
    public Response listAllTeachers() {
        List<Teacher> foundTeachers = teacherService.listAllTeachers();

        if(foundTeachers.isEmpty()){
            String responseMessage = "{ \"There are no Teachers registered in the database.\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
        }
        return Response.status(302).entity(foundTeachers).build();
    }


}
