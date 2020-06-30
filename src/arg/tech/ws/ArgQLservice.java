package arg.tech.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.core.Response;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ArgQLservice {
	 @WebMethod
	 Response executeArgQL(String jsonString);
}
