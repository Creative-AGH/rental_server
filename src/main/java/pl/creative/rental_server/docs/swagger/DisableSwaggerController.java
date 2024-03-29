package pl.creative.rental_server.docs.swagger;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.core.global.exception.notFound.NotFoundException;

import javax.servlet.http.HttpServletResponse;

@Profile("prod")
@RestController
public class DisableSwaggerController {

    @RequestMapping(value = "swagger-ui.html", method = RequestMethod.GET)
    public void getSwagger(HttpServletResponse httpResponse) throws NotFoundException {
        httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
