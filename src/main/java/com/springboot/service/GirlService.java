package com.springboot.service;

import com.springboot.enums.ResultEnum;
import com.springboot.exception.GirlException;
import com.springboot.repository.GirlRepository;
import com.springboot.domain.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    public void insertTwoGirl(){
        Girl girl=new Girl();
        girl.setAge(18);
        girl.setCupSize("F");
        Girl girl_a=new Girl();
        girl_a.setAge(null);
        girl_a.setCupSize("BBBB");
        girlRepository.save(girl);
        girlRepository.save(girl_a);
    }

    /**
     * @param id
     * @throws Exception
     */
    public void getOneGirl(Integer id) throws Exception {
        Girl girl = girlRepository.getOne(id);
        Integer age = girl.getAge();
        if (age<10){
//            返回你还在上小学吧
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age>=10&&age<=16){
//            返回你还在上初中吧
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
//        如果大于16岁 ...
//        ...
    }

    /**
     * 通过id查询一个女生
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
        return girlRepository.getOne(id);
    }
}
