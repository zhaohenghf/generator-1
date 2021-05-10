package ${packageName}.biz.service.impl;

import com.ichings.base.Check;
import com.ichings.base.Errors;
import com.ichings.common.biz.BizAssert;
import com.ichings.common.page.PageList;
import ${packageName}.biz.dto.${tableNameCamelUpperFirst}Dto;
import ${packageName}.biz.dto.${tableNameCamelUpperFirst}DtoMapper;
import ${packageName}.biz.repository.${tableNameCamelUpperFirst}Repository;
import ${packageName}.biz.service.${tableNameCamelUpperFirst}Service;
import ${packageName}.biz.util.BizError;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Attribute;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;
import com.ichings.util.AssertUtils;
import com.ichings.util.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ${authorName}
 */
@Service
public class ${tableNameCamelUpperFirst}ServiceImpl implements ${tableNameCamelUpperFirst}Service {

    @Autowired
    private ${tableNameCamelUpperFirst}Repository repository;

    @Autowired
    private ${tableNameCamelUpperFirst}DtoMapper beanMapper;

    @Autowired
    private Snowflake snowflake;

    @Override
    public PageList<${tableNameCamelUpperFirst}Dto> findPage(${tableNameCamelUpperFirst}Criteria criteria) {
        List<${tableNameCamelUpperFirst}Dto> list = findList(criteria);
        long totalNum = getTotalNum(criteria);

        PageList<${tableNameCamelUpperFirst}Dto> result = new PageList<>(criteria);
        result.setData(list);
        result.setTotalNum(totalNum);
        return result;
    }

    @Override
    public List<${tableNameCamelUpperFirst}Dto> findList(${tableNameCamelUpperFirst}Criteria criteria) {
        BizAssert.nonNull(criteria, Errors.ARGS_ERR);

        List<${tableNameCamelUpperFirst}Dto> result = new ArrayList<>();

        List<${tableNameCamelUpperFirst}> list = repository.findList(criteria);
        if (Check.nonEmpty(list)) {
            for (${tableNameCamelUpperFirst} record : list) {
                if (Check.nonNull(record)) {
                    Long id = record.getId();
                    ${tableNameCamelUpperFirst}Attribute attribute = repository.getAttribute(id);

                    ${tableNameCamelUpperFirst}Dto dto = beanMapper.poToDto(record, attribute);
                    if (Check.nonNull(dto)) {
                        result.add(dto);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public long getTotalNum(${tableNameCamelUpperFirst}Criteria criteria) {
        BizAssert.nonNull(criteria, Errors.ARGS_ERR);

        return repository.getTotalNum(criteria);
    }

    @Override
    public ${tableNameCamelUpperFirst}Dto find(Long id) {
        BizAssert.isPositive(id, Errors.ID_POSITIVE);

        ${tableNameCamelUpperFirst} record = repository.find(id);
        if (Check.isNull(record)) {
            return null;
        }

        ${tableNameCamelUpperFirst}Attribute attribute = repository.getAttribute(id);
        return beanMapper.poToDto(record, attribute);
    }

    ${uniqueServiceImpl}

    @Override
    public boolean insert(${tableNameCamelUpperFirst}Dto dto) {
        BizAssert.nonNull(dto, Errors.ARGS_ERR);

        ${uniqueServiceImplInsert}

        if (Check.isNull(dto.getId())) {
            dto.setId(snowflake.nextId());
        }

        AssertUtils.isPositive(dto.getId(), "dto.id");

        ${tableNameCamelUpperFirst} record = beanMapper.dtoToPo(dto);
        ${tableNameCamelUpperFirst}Attribute attribute = beanMapper.dtoToAttribute(dto);

        return repository.insert(record, attribute);
    }

    @Override
    public boolean updateSortList(List<UpdateSortDto> list) {
        BizAssert.nonNull(list, Errors.ARGS_ERR);

        List<${tableNameCamelUpperFirst}> data = new ArrayList<>();
        for (UpdateSortDto dto : list) {
            if (Check.isNull(dto)) {
                continue;
            }

            Long id = dto.getId();
            Integer sort = dto.getSort();

            BizAssert.isPositive(id, Errors.ID_POSITIVE);
            BizAssert.isPositive(sort, Errors.SORT_POSITIVE);

            ${tableNameCamelUpperFirst} record = new ${tableNameCamelUpperFirst}();
            record.setId(id);
            record.setSort(sort);

            data.add(record);
        }

        return repository.updateList(data);
    }

    @Override
    public boolean update(${tableNameCamelUpperFirst}Dto dto) {
        BizAssert.nonNull(dto, Errors.ARGS_ERR);

        Long id = dto.getId();
        BizAssert.isPositive(id, Errors.ID_POSITIVE);

        ${uniqueServiceImplUpdate}

        ${tableNameCamelUpperFirst} record = beanMapper.dtoToPo(dto);
        ${tableNameCamelUpperFirst}Attribute attribute = beanMapper.dtoToAttribute(dto);

        return repository.update(record, attribute);
    }

    @Override
    public boolean delete(Long id) {
        BizAssert.isPositive(id, Errors.ID_POSITIVE);

        return repository.delete(id);
    }

    @Override
    public String getValue(Long baseId, Integer attributeId) {
        return repository.getValue(baseId, attributeId);
    }

}
