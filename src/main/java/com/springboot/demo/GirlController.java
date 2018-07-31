package com.springboot.demo;

import com.springboot.domain.Girl;
import com.springboot.domain.Result;
import com.springboot.repository.GirlRepository;
import com.springboot.service.GirlService;
import com.springboot.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController  {
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;
    @RequestMapping("/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }
    @RequestMapping("**/saveGirl")
    public Result<Girl> postGirl(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
//            return null;
//            Result result=new Result();
//            result.setCode("1");
//            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtils.failure("0",bindingResult.getFieldError().getDefaultMessage());
        }else{
            return ResultUtils.success("1",girl);
        }

    }
    @RequestMapping("**/insertTwo")
    public void insertTwo(){
        girlService.insertTwoGirl();
    }
    @RequestMapping("/getAge/{id}")
    public void getAge(@PathVariable("id")Integer id) throws Exception {
        girlService.getOneGirl(id);
    }
}
