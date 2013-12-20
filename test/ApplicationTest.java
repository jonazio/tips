import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.results.Tipsrow;

import org.codehaus.jackson.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test 
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }
    
/**    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Antal rätt:");
    }*/
    
    @Test
    public void testCompare() {
    	Tipsrow tipsrow = new Tipsrow("1X2");
    	assertFalse(tipsrow.compare("111"));
    	assertTrue(tipsrow.compare("1X2"));
    }
  
   
}
