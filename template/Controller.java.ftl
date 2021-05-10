package ${packageName}.web.controller;

import com.ichings.base.Errors;
import com.ichings.base.Result;
import com.ichings.common.biz.BizAssert;
import com.ichings.common.page.PageList;
import com.ichings.common.page.VoList;
import ${packageName}.biz.dto.${tableNameCamelUpperFirst}Dto;
import ${packageName}.biz.service.${tableNameCamelUpperFirst}Service;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;
import ${packageName}.web.vo.${tableNameCamelUpperFirst}CreateRequest;
import ${packageName}.web.vo.${tableNameCamelUpperFirst}CriteriaRequest;
import ${packageName}.web.vo.${tableNameCamelUpperFirst}UpdateRequest;
import ${packageName}.web.vo.${tableNameCamelUpperFirst}Vo;
import ${packageName}.web.vo.${tableNameCamelUpperFirst}VoMapper;
import com.ichings.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LOGGER;
import org.slf4j.LOGGERFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ${authorName}
 */
@Api(value = "${tableNameCamelUpperFirst}Controller", tags = "${classTitle}")
@RequestMapping("${tableNameHyphenLower}")
@Validated
@RestController
public class ${tableNameCamelUpperFirst}Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(${tableNameCamelUpperFirst}Controller.class);

    @Autowired
    private ${tableNameCamelUpperFirst}Service service;

    @Autowired
    private ${tableNameCamelUpperFirst}VoMapper beanMapper;

    @ApiOperation(value = "查询列表", produces = "application/json")
    @GetMapping(value = "/list")
    @ResponseBody
    public Result<List<${tableNameCamelUpperFirst}Vo>> list(${tableNameCamelUpperFirst}CriteriaRequest request) {
        LOGGER.info("list trace, request: {}", request);

        BizAssert.nonNull(request, Errors.NO_ARGS);
        ${tableNameCamelUpperFirst}Criteria criteria = beanMapper.voToDto(request);

        List<${tableNameCamelUpperFirst}Dto> dtoList = service.findList(criteria);
        BizAssert.nonNull(dtoList, Errors.NO_RESULT);

        List<${tableNameCamelUpperFirst}Vo> voList = beanMapper.dtoToVo(dtoList);
        return ResultUtils.toSuccess(voList);
    }

    @ApiOperation(value = "分页查询", produces = "application/json")
    @GetMapping(value = "/page")
    @ResponseBody
    public Result<VoList<${tableNameCamelUpperFirst}Vo>> page(${tableNameCamelUpperFirst}CriteriaRequest request) {
        LOGGER.info("page trace, request: {}", request);

        BizAssert.nonNull(request, Errors.NO_ARGS);
        ${tableNameCamelUpperFirst}Criteria criteria = beanMapper.voToDto(request);

        PageList<${tableNameCamelUpperFirst}Dto> dtoPage = service.findPage(criteria);
        BizAssert.nonNull(dtoPage, Errors.NO_RESULT);

        List<${tableNameCamelUpperFirst}Dto> dtoList = dtoPage.getData();
        List<${tableNameCamelUpperFirst}Vo> voList = beanMapper.dtoToVo(dtoList);

        VoList<${tableNameCamelUpperFirst}Vo> voPage = new VoList<>(dtoPage, voList, request);
        return ResultUtils.toSuccess(voPage);
    }

    @ApiOperation(value = "通过主键，获取一条记录", produces = "application/json")
    @GetMapping(value = "/view")
    @ResponseBody
    public Result<${tableNameCamelUpperFirst}Vo> view(@NotNull @RequestParam("id") Long id) {
        LOGGER.info("view trace, id: {}", id);

        ${tableNameCamelUpperFirst}Dto dtoRecord = service.find(id);
        ${tableNameCamelUpperFirst}Vo voRecord = beanMapper.dtoToVo(dtoRecord);

        return ResultUtils.toSuccess(voRecord);
    }

    ${uniqueController}

    @ApiOperation(value = "新增一条记录", produces = "application/json")
    @PostMapping(value = "/create")
    @ResponseBody
    public Result<Boolean> create(@Valid @RequestBody ${tableNameCamelUpperFirst}CreateRequest request) {
        LOGGER.info("create trace, request: {}", request);

        ${tableNameCamelUpperFirst}Dto record = beanMapper.voToDto(request);
        boolean r = service.insert(record);

        return ResultUtils.toSuccess(r);
    }

    @ApiOperation(value = "通过主键，修改一条记录", produces = "application/json")
    @PostMapping(value = "/update")
    @ResponseBody
    public Result<Boolean> update(@Valid @RequestBody ${tableNameCamelUpperFirst}UpdateRequest request) {
        LOGGER.info("update trace, request: {}", request);

        ${tableNameCamelUpperFirst}Dto record = beanMapper.voToDto(request);
        boolean r = service.update(record);

        return ResultUtils.toSuccess(r);
    }

    @ApiOperation(value = "通过主键，修改多个排序", produces = "application/json")
    @PostMapping(value = "/update-sort")
    @ResponseBody
    public Result<Boolean> updateSort(@Valid @RequestBody List<UpdateSortRequest> request) {
        LOGGER.info("updateSort trace, request: {}", request);

        List<UpdateSortDto> list = beanMapper.voToDto(request);
        boolean r = service.updateSortList(list);

        return ResultUtils.toSuccess(r);
    }

    @ApiOperation(value = "通过主键，删除一条记录", produces = "application/json")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public Result<Boolean> delete(@NotNull @RequestParam("id") Long id) {
        LOGGER.info("delete trace, id: {}", id);

        boolean r = service.delete(id);

        return ResultUtils.toSuccess(r);
    }

}
