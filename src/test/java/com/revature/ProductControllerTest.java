package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ECommerceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ProductControllerTest {

    private final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository pr;

    @BeforeEach
    public void resetDB() {
        pr.deleteAll();
    }

    @Test
    @Transactional
    public void updateProductTest() throws Exception {
        Product p = new Product();
        Product pt = new Product(6, 1, 1.0, "a", "b", "c", "d", false, false);
        User u = new User();
        p.setId(0);
        p.setQuantity(2);
        p.setDescription("e");
        p.setPrice(2.0);
        p.setImage("f");
        p.setDiscontinued(true);
        p.setFeatured(true);
        p.setName("g");
        p.setCategory("h");
        pr.save(p);

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);
        mockMvc.perform(put("/api/product").sessionAttrs(sessionAttr).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(pt))
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(1)).andExpect(jsonPath("$.price").value(1.0))
                .andExpect(jsonPath("$.description").value("a")).andExpect(jsonPath("$.image").value("b"))
                .andExpect(jsonPath("$.name").value("c"))
                .andExpect(jsonPath("$.category").value("d"))
                .andExpect(jsonPath("$.discontinued").value(false))
                .andExpect(jsonPath("$.featured").value(false));
    }

    @Test
    @Transactional
    public void getInventoryTest() throws Exception {

        Product p = new Product(0, 1, 1.0, "a", "b", "c", "d", false, false);
        User u = new User();
        pr.save(p);
        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);
        mockMvc.perform(get("/api/product").sessionAttrs(sessionAttr)
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].quantity").value(1)).andExpect(jsonPath("$.[0].price").value(1.0))
                .andExpect(jsonPath("$.[0].description").value("a")).andExpect(jsonPath("$.[0].image").value("b"))
                .andExpect(jsonPath("$.[0].name").value("c"))
                .andExpect(jsonPath("$.[0].category").value("d"))
                .andExpect(jsonPath("$.[0].discontinued").value(false))
                .andExpect(jsonPath("$.[0].featured").value(false));
    }

    @Test
    @Transactional
    public void getItemByIdFailedTest() throws Exception {
        User u = new User();

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);
        mockMvc.perform(get("/api/product/1").sessionAttrs(sessionAttr)
        ).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void purchaseFailedTest() throws Exception {
        List<ProductInfo> lp = new ArrayList<ProductInfo>();
        ProductInfo p = new ProductInfo();
        lp.add(p);
        User u = new User();

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);
        mockMvc.perform(patch("/api/product/").sessionAttrs(sessionAttr).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(lp))
        ).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void purchaseBadRequestTest() throws Exception {
        List<ProductInfo> lp = new ArrayList<ProductInfo>();
        ProductInfo p = new ProductInfo(1, 5);
        Product dbItem = new Product(1, 1, 1.0, "a", "b", "c", "d", false, false);
        lp.add(p);

        pr.save(dbItem);
        User u = new User();

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);

        mockMvc.perform(patch("/api/product/").sessionAttrs(sessionAttr).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(lp)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void purchaseTest() throws Exception {
        List<ProductInfo> lp = new ArrayList<ProductInfo>();
        ProductInfo p = new ProductInfo(1, 1);
        Product dbItem = new Product(1, 1, 1.0, "a", "b", "c", "d", false, false);
        lp.add(p);

        pr.save(dbItem);
        User u = new User();

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);

        mockMvc.perform(patch("/api/product/").sessionAttrs(sessionAttr).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(lp)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void deleteFailedTest() throws Exception {
        User u = new User();

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);
        mockMvc.perform(delete("/api/product/1").sessionAttrs(sessionAttr)
        ).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void deletePassTest() throws Exception {
        Product dbItem = new Product(1, 1, 1.0, "a", "b", "c", "d", false, false);
        User u = new User();

        pr.save(dbItem);

        HashMap<String, Object> sessionAttr = new HashMap<String, Object>();
        sessionAttr.put("user", u);
        mockMvc.perform(delete("/api/product/1").sessionAttrs(sessionAttr)
        ).andDo(print()).andExpect(status().isOk());
    }
}