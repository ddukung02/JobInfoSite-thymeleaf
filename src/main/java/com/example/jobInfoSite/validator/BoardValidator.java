package com.example.jobInfoSite.validator;

import com.example.jobInfoSite.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
//        return Board.class.isAssignableFrom(clazz);
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        log.info("@# validate()");

        Board b= (Board) obj;
        log.info("@# b=>"+b);

        if (b.getContent().equals("")){
            e.rejectValue("content","key","내용을 입력하세요.");
        }
    }
}
