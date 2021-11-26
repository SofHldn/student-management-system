package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject){
        if(subject.getClassName().isEmpty()){
            String responseMessage = "{\"SubjectName can not be empty!\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseMessage).build());
        }
        Subject createdSubject = subjectService.createSubject(subject);
        return Response.status(201).entity(createdSubject).build();
    }

    @Path("")
    @GET
    public Response listAllSubjects() {
        List<Subject> foundSubjects = subjectService.listAllSubjects();

        if(foundSubjects.isEmpty()){
            String responseMessage = "{ \"There are no Subjects registered in the database.\"}";
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(responseMessage).build());
        }
        return Response.status(302).entity(foundSubjects).build();
    }

}
