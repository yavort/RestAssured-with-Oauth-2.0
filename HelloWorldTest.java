import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;

import com.jayway.restassured.authentication.OAuth2Scheme;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldTest {

	protected static Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);
	
	@Test
	public void simpleTest(){
		logger.debug("Running simpleTest");
		assertEquals(HelloWorld.add(2, 2), 4);
	}

	@Test
	public void simpleTest2() {
		logger.debug("Running simple test2");
		assertEquals(HelloWorld.add(3, 4), 7);
//        OAuth2Scheme oauth = new OAuth2Scheme();
//        oauth.authenticate();


        String accessToken =
                "EAACEdEose0cBAJ6lQBfLAOcT9KBtL91oBYZCggzp8vbOzqVFuIE6NmUW2VyK3vqYz2PyJ04HF4KBOm4CIgwF7TnUwqEEO2cAYZCIJ9Wu8A7dMvOuaKRgaOmptb73SN7NUB5AqVxFnWEPhnvvK9db3WMDGVpEenR1tMFp6WpgZDZD";


        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        User me = facebookClient.fetchObject("me", User.class);
        System.out.print(me.getName());

        given().auth().oauth2(accessToken).

        when().
                get("https://graph.facebook.com/v2.7/me?fields=id,name")
 .then().extract().body().asString().contains("Javor Canov");
	}
}
