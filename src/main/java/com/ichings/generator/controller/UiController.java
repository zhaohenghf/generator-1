package com.ichings.generator.controller;

import com.ichings.generator.service.UiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 宋欢
 */
@Controller
public class UiController {
    /**
     * 模板：生成代码
     */
    private static final String TEMPLATE_CODE = "code";

    /**
     * 模板：数据字典
     */
    private static final String TEMPLATE_DICT = "dict";

    @Autowired
    private UiService service;

    @RequestMapping(value = "code")
    public String code(Model m) {
        service.renderCode(m);
        service.renderDb(m);

        return TEMPLATE_CODE;
    }

    @RequestMapping(value = "dict")
    public String dict(Model m) {
        service.renderDict(m);
        service.renderDb(m);

        return TEMPLATE_DICT;
    }

}
