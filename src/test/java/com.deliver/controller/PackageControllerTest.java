package com.deliver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by 91574 on 2017/5/24.
 */
public class PackageControllerTest extends BaseControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getPackage() throws Exception {
        RequestBuilder requestBuilder = get("/package/102");
        mockMvc.perform(requestBuilder).andDo(print());
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform((get("/allpackage"))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void add() throws Exception {
        RequestBuilder requestBuilder = post("/package")
                .param("id", "102")
                .param("company", "顺风")
                .param("receiverName", "zhang")
                .param("receiverTele", "123465789")
                .param("cupOrShelf", "false")
                .param("storageId", "1");
        mockMvc.perform(requestBuilder).andDo(print());
    }

    @Test
    public void getPackagesByTele() throws Exception {
        RequestBuilder requestBuilder=get("/package/tele/123465789");
        mockMvc.perform(requestBuilder).andDo(print());
    }
}