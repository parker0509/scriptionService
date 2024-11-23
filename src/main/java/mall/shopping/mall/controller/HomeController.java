package mall.shopping.mall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Camp Reserve", description = "Let's go Camping")
@RestController
public class HomeController {

    @Operation(summary = "Home Check", description = "ON/OFF Check")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "text/plain",
            examples = {@ExampleObject("OK")}))

    @GetMapping("/")
    public String TestHome() {
        return "OK";
    }
}
