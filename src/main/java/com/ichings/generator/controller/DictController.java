package com.ichings.generator.controller;

import com.ichings.generator.dto.DictDto;
import com.ichings.generator.service.DictService;
import com.ichings.generator.util.Result;
import com.ichings.generator.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 宋欢
 */
@Api(value = "DictController", tags = "数据字典")
@RequestMapping("dict")
@Validated
@RestController
public class DictController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private DictService service;

    @ApiOperation(value = "执行操作", produces = "application/json")
    @PostMapping(value = "/execute")
    @ResponseBody
    public Result<Boolean> execute(@RequestBody DictDto request) {
        LOGGER.info("execute trace, request: {}", request);

        try {
            service.execute(request);
            return ResultUtils.toSuccess(true);
        } catch (Throwable tr) {
            LOGGER.error("execute failed, request: {}", request, tr);
            return ResultUtils.fromThrowable(tr);
        }
    }

}
