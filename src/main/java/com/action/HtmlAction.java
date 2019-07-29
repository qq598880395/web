package com.action;

import com.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HtmlAction {

    @Autowired
    private ImgService imgService;


}
