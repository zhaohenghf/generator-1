package com.ichings.generator.service.impl;

import com.ichings.generator.db.TableSchema;
import com.ichings.generator.dto.CodeDto;
import com.ichings.generator.service.TagService;
import com.ichings.generator.tag.TagDto;
import com.ichings.generator.tag.TagMybatisResource;
import com.ichings.generator.tag.TagPo;
import com.ichings.generator.tag.TagRepository;
import com.ichings.generator.tag.TagTableName;
import com.ichings.generator.tag.TagUnique;
import com.ichings.generator.tag.TagValue;
import com.ichings.generator.tag.TagVo;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.BizException;
import com.ichings.generator.util.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author 宋欢
 */
@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Override
    public Map<String, String> getData(CodeDto dto, TableSchema schema) {
        TagValue tagValue = getValue(dto, schema);

        try {
            return tagValue.toMap();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error("getData failed, dto: {}, schema: {}, throwable: ", dto, schema, e);
            throw new BizException(Errors.SYSTEM_RUN_ERR);
        }
    }

    @Override
    public TagValue getValue(CodeDto dto, TableSchema schema) {
        AssertUtils.nonNull(dto, "dto");
        AssertUtils.nonNull(schema, "tableSchema");

        String authorName = dto.getAuthorName();
        String packageName = dto.getPackageName();

        AssertUtils.nonEmpty(authorName, "authorName");
        AssertUtils.nonEmpty(packageName, "packageName");

        String tableRemark = schema.getRemark();

        TagValue tagValue = new TagValue();

        tagValue.setAuthorName(authorName);
        tagValue.setPackageName(packageName);
        tagValue.setClassTitle(tableRemark);

        TagTableName.render(tagValue, schema);
        TagPo.render(tagValue, schema);
        TagDto.render(tagValue, schema);
        TagVo.render(tagValue, schema);
        TagRepository.render(tagValue, schema);
        TagUnique.render(tagValue, schema);
        TagMybatisResource.render(tagValue, schema);

        return tagValue;
    }

}
