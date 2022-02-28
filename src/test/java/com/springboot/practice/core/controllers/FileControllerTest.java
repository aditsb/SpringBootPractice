package com.springboot.practice.core.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate testTemplate;

    @Test
    public void testUploadFile() {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("file",new ClassPathResource("Spring.jpeg"));
        HttpEntity<MultiValueMap<String,Object>>httpEntity=new HttpEntity<>(multiValueMap,headers);
       ResponseEntity<Boolean>response= testTemplate.postForEntity("http://localhost:"+randomServerPort+"/studentsapi/upload",httpEntity,Boolean.class);
        Assert.assertTrue(response.getBody());
    }

    @Test
    public void testDownloadFile() {
    }
}