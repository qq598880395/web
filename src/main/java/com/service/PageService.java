package com.service;

import com.dao.PageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    private PageDAO pageDAO;
}
