package com.ichings.generator.tag;

import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;

/**
 * Unique 标签
 *
 * @author 宋欢
 */
public final class TagUnique {
    /**
     * 设置标签值
     *
     * @param tagValue    TagValue
     * @param tableSchema TableSchema
     */
    public static void render(TagValue tagValue, TableSchema tableSchema) {
        AssertUtils.nonNull(tagValue, "tagValue");
        AssertUtils.nonNull(tableSchema, "tableSchema");

        setDatabase(tagValue, tableSchema);
        setMybatisResource(tagValue, tableSchema);
        setRepository(tagValue, tableSchema);
        setRepositoryImpl(tagValue, tableSchema);
        setService(tagValue, tableSchema);
        setServiceImpl(tagValue, tableSchema);
        setServiceImplInsert(tagValue, tableSchema);
        setServiceImplUpdate(tagValue, tableSchema);
        setController(tagValue, tableSchema);
    }

    /**
     * ${uniqueDatabase}
     */
    private static void setDatabase(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.DATABASE, "\t");
        tagValue.setUniqueDatabase(code);
    }

    /**
     * ${uniqueMybatisResource}
     */
    private static void setMybatisResource(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.MYBATIS_RESOURCE, "\t");
        tagValue.setUniqueMybatisResource(code);
    }

    /**
     * ${uniqueRepository}
     */
    private static void setRepository(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.REPOSITORY, "\t");
        tagValue.setUniqueRepository(code);
    }

    /**
     * ${uniqueRepositoryImpl}
     */
    private static void setRepositoryImpl(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.REPOSITORY_IMPL, "\t");
        tagValue.setUniqueRepositoryImpl(code);
    }

    /**
     * ${uniqueService}
     */
    private static void setService(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.SERVICE, "\t");
        tagValue.setUniqueService(code);
    }

    /**
     * ${uniqueServiceImpl}
     */
    private static void setServiceImpl(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.SERVICE_IMPL, "\t");
        tagValue.setUniqueServiceImpl(code);
    }

    /**
     * ${uniqueServiceImplInsert}
     */
    private static void setServiceImplInsert(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.SERVICE_IMPL_INSERT, "\t\t");
        tagValue.setUniqueServiceImplInsert(code);
    }

    /**
     * ${uniqueServiceImplUpdate}
     */
    private static void setServiceImplUpdate(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.SERVICE_IMPL_UPDATE, "\t\t");
        tagValue.setUniqueServiceImplUpdate(code);
    }

    /**
     * ${uniqueController}
     */
    private static void setController(TagValue tagValue, TableSchema tableSchema) {
        String code = UniqueUtils.getCodes(tableSchema, UniqueTemplate.CONTROLLER, "\t");
        tagValue.setUniqueController(code);
    }

}
