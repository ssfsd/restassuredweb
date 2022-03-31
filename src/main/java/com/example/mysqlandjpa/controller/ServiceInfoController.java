package com.example.mysqlandjpa.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mysqlandjpa.assured.AssuredUtils;
import com.example.mysqlandjpa.entity.ServiceEntity;
import com.example.mysqlandjpa.service.ServiceEntityService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@Controller
public class ServiceInfoController {
    @Autowired
    public Environment environment;
    @Autowired
    public ServiceEntityService serviceEntityService;
//    public  RequestSpecification requestSpecification;
//    public AssuredUtils assuredUtils=AssuredUtils.getInstance();
@RequestMapping(value="/")
    public String index(){
    return  "redirect:/list";
}

@RequestMapping(value="/list")
public String  hello(Model model,@RequestParam(value = "pageNum", defaultValue = "0") int pageNum){
//    List<ServiceEntity> all=serviceEntityService.findAll();
//    model.addAttribute("all",all);
    Page<ServiceEntity> serviceEntitys=serviceEntityService.getServiceListWithPage(pageNum,10);
    model.addAttribute("serviceEntitys",serviceEntitys);
return "index";
}
@RequestMapping("toAdd")
public String toAdd(){
return "add";
}
@RequestMapping("/add")
public String add(ServiceEntity serviceEntity){
    serviceEntityService.saveOne(serviceEntity);
    return "redirect:/list";
}
@RequestMapping("del/{serviceId}")
public String del(@PathVariable String serviceId)
{
    serviceEntityService.delById(serviceId);
    return "redirect:/list";
}
@RequestMapping("edit/{serviceId}")
public String edit(@PathVariable String serviceId,Model model)
{
   ServiceEntity serviceEntity= serviceEntityService.findOne(serviceId);
   model.addAttribute("serviceEntity",serviceEntity);
return "edit";
}
@RequestMapping("/seach")
public String search(){
    return "seach";
}
@RequestMapping("/seachAll")
public String seachAll(ServiceEntity serviceEntity,Model model)
{
    String url=serviceEntity.getUrl();
    String body=serviceEntity.getBody();
    List<ServiceEntity> serviceEntityList=serviceEntityService.findByUrlAndBody(url,body);
    model.addAttribute("seachAll",serviceEntityList);
return "seach";
}
@RequestMapping("/update")
public String update(ServiceEntity serviceEntity){
    String serviceId=serviceEntity.getServiceId();
    ServiceEntity serviceEntityFind= serviceEntityService.findOne(serviceId);
    serviceEntityFind.setUrl(serviceEntity.getUrl());
    serviceEntityFind.setBody(serviceEntity.getBody());
    serviceEntityFind.setType(serviceEntity.getType());
    serviceEntityService.saveOne(serviceEntityFind);
    return "redirect:/list";
}
@RequestMapping("/listByPage")
public String listWithPage(Model model,@RequestParam(value = "pageNum", defaultValue = "0") int pageNum)
{
    Page<ServiceEntity> serviceEntitys=serviceEntityService.getServiceListWithPage(pageNum,environment.getProperty("service.entity.pagesize",Integer.class));
    model.addAttribute("serviceEntitys",serviceEntitys);
    return "listByPage";
}
@RequestMapping("test/{serviceId}")
public String testCase(@PathVariable String serviceId){
//    requestSpecification=AssuredUtils.getInstance().setUrlAndHeaders("http://localhost","",8080);
    String result=null;
    ServiceEntity serviceEntityFind= serviceEntityService.findOne(serviceId);
    if(serviceEntityFind==null){
        serviceEntityFind.setBody("数据库查询不到数据");
        serviceEntityFind.setResult("Failed");
        serviceEntityService.saveOne(serviceEntityFind);
        return "redirect:/list";
    }
    String type=serviceEntityFind.getType();
    String url=serviceEntityFind.getUrl();
    String body=serviceEntityFind.getBody();
    String responseBody=null;
    Response response=null;
    ResponseBody responseBody1=null;
    if("post".equalsIgnoreCase(type))
    {
        response=given().contentType(environment.getProperty("service.entity.contenttype")).baseUri(environment.getProperty("service.entity.baseurl")).port(environment.getProperty("service.entity.port",Integer.class)).body(body).when().log().all().post(url.trim());
//        response=given().spec(requestSpecification).contentType(environment.getProperty("service.entity.contenttype")).body(body).when().log().all().post(url.trim());
    }
    else if("get".equalsIgnoreCase(type))
    {
        response=given().contentType(environment.getProperty("service.entity.contenttype")).get(url.trim());
    }
    else if("delete".equalsIgnoreCase(type))
    {
        response=given().contentType(environment.getProperty("service.entity.contenttype")).delete(url.trim());
    }
    if(response.getStatusCode()==200){
        responseBody=response.getBody().prettyPrint();
        result="Success";
    }
    else{
        result="Failed";
    }
    serviceEntityFind.setResult(result);
    serviceEntityFind.setRespBody(responseBody);
    serviceEntityService.saveOne(serviceEntityFind);
return "redirect:/list";
}
}
