package com.deliver.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by 91574 on 2017/6/6.
 */
public class TransformControllerTest extends BaseControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void transform()throws Exception{
        RequestBuilder requestBuilder=post("/transform")
                .param("isCup","false").param("id","100").param("layer","1").param("column","2")
                .param("tStorageId","1004");
        mockMvc.perform(requestBuilder).andDo(print());
    }

    @Test
    public void transformById()throws Exception{
        RequestBuilder requestBuilder=post("/transform/id")
                .param("fStorageId","1002")
                .param("tStorageId","2002");
        mockMvc.perform(requestBuilder).andDo(print());
    }
}
