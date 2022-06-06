package ajbc.webservice.rest.api_demo.exceptions;

import ajbc.webservice.rest.api_demo.models.ErrorMessage;
import ajbc.webservice.rest.api_demo.models.InternalErrorCode;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MissingDataExceptionMapper implements ExceptionMapper<MissingDataException>
{


	@Override
	public Response toResponse(MissingDataException exception) 
	{
		ErrorMessage errorMsg = new ErrorMessage(exception.getMessage(), "www.ask.manager.com", InternalErrorCode.INVALID_ID);
		return Response.status(Status.NOT_FOUND)
		.entity(errorMsg)
		.build();
	}

}
