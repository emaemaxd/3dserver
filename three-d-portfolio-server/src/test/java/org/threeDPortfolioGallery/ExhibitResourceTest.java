package org.threeDPortfolioGallery;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// TODO tests schreiben
@QuarkusTest
public class ExhibitResourceTest {
    /*@GET
    @Path("/{exhibitId}")
    public Response getExhibitById(@PathParam("exhibitId") Long id) {
        return gr.checkIfEmpty(exhibitRepo.findById(id));
    }
     */
    @Test
    public void testNonExistentExhibit() {
        given()
                .when().get("/exhibit/100")
                .then()
                .statusCode(404);
    }
}
