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
public class ShelfControllerTest extends BaseControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void add()throws Exception{
        RequestBuilder requestBuilder = post("/shelf").
                param("id","4").param("columnSum","2").param("layerSum","3");
        mockMvc.perform(requestBuilder).andDo(print());
    }

//    @Test
//    public void getPosition()throws Exception{
//        RequestBuilder requestBuilder = get("/shelf/position");
//        mockMvc.perform(requestBuilder).andDo(print());
//    }

    @Test
    public void getShelf()throws Exception{
        RequestBuilder requestBuilder = get("/shelf/2");
        mockMvc.perform(requestBuilder).andDo(print());
    }

    @Test
    public void getAll()throws Exception{
        RequestBuilder requestBuilder = get("/shelf/all");
        mockMvc.perform(requestBuilder).andDo(print());
    }
}
