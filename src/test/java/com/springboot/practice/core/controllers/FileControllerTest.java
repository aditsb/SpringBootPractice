package com.springboot.practice.core.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest
public class FileControllerTest {

    private RestTemplate testTemplate=new RestTemplate();

    @Test
    public void testUploadFile() {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("file",new ClassPathResource("Spring.jpeg"));
        HttpEntity<MultiValueMap<String,Object>>httpEntity=new HttpEntity<>(multiValueMap,headers);
       ResponseEntity<Boolean>response= testTemplate.postForEntity("http://localhost:8080/studentsapi/upload",httpEntity,Boolean.class);
        System.out.println(response.getBody());
    }

    @Test
    public void testDownloadFile() {
    }
}