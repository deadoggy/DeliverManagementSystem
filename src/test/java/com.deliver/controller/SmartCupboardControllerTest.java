package com.deliver.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by 91574 on 2017/5/29.
 */
public class SmartCupboardControllerTest extends BaseControllerTest{
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void add()throws Exception{
        RequestBuilder requestBuilder=post("/smartcupboard")
                .param("id","3").param("columnSum","3").param("layerSum","2");
        mockMvc.perform(requestBuilder).andDo(print());
    }

    @Test
    public void getPosition()throws Exception{
        RequestBuilder requestBuilder = get("/smartcupboard/position");
        mockMvc.perform(requestBuilder).andDo(print());
    }
}
