package wanderer2357.redwire.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "api/v1",
	    produces = "application/json;charset=UTF-8",
	    consumes = "application/json;charset=UTF-8"
	)
public interface RedwireApiBaseController {

}
