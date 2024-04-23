package com.revature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ECommerceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository ur;

    @BeforeEach
    public void resetDB() {
        ur.deleteAll();
    }

    private ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void loginTest()throws Exception{
        LoginRequest loginRequest = new LoginRequest("testuser@gmail.com","password");
        User u = new User(0,"testuser@gmail.com","password","a","b",false);
        ur.save(u);
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(loginRequest))
                ).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.email").value("testuser@gmail.com"))
                .andExpect(jsonPath("$.password").value("")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.admin").value(false));
    }

    @Test
    @Transactional
    public void failedLoginTest()throws Exception{
        LoginRequest loginRequest = new LoginRequest("testuser@gmail.com","word");
        User u = new User(0,"testuser@gmail.com","password","a","b",false);
        ur.save(u);
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(loginRequest))
        ).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void saveUserTest()throws Exception{
        RegisterRequest registerRequest = new RegisterRequest("test","pass","a","b",false);
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerRequest))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.email").value("test"))
                .andExpect(jsonPath("$.password").value("")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.admin").value(false));
    }
    @Test
    @Transactional
    public void failedSaveUserTest()throws Exception{
        RegisterRequest registerRequest = new RegisterRequest("test","pass","a","b",false);
        User u = new User(0,"test","pass","a","b",false);
        ur.save(u);
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerRequest))
        ).andDo(print()).andExpect(status().isConflict());
    }

    //test admin scenario
/*    @Test
    public void checkLoginAdminTest() throws Exception {
        User u = new User(0,"test","pass","a","b",true);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user",u);

        mockMvc.perform(get("/auth/checkLogin").session(session))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$").value(3));

    }*/
/*    @Test
    public void checkLoginUserTest() throws Exception {
        User u = new User(0,"test","pass","a","b",false);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user",u);

        mockMvc.perform(get("/auth/checkLogin").session(session))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$").value(2));

    }*/
/*    @Test
    public void checkLoginGuestTest() throws Exception {
        User u = new User(0,"test","pass","a","b",false);
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(get("/auth/checkLogin").session(session))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$").value(1));
    }*/

/*    @Test
    public void getUserTest() throws Exception {
        User u = new User(0,"test","pass","test_first","test_last",false);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user",u);

        mockMvc.perform(get("/auth/getUser").session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("test_first"));
    }*/

    @Test
    public void removeSessionTest() throws Exception {
        User u = new User(0,"test","pass","a","b",false);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user",u);

        mockMvc.perform(post("/auth/logout").session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

