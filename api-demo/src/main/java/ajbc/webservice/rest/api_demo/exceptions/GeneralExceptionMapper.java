package ajbc.webservice.rest.api_demo.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class GeneralExceptionMapper implements ExceptionMapper<Throwable>
{
	//Throwable - any error, (MissingDataException/...)

	@Override
	public Response toResponse(Throwable exception) {
		return Response.status(1234).entity(exception.getMessage()).build();
	}
	
	

}
