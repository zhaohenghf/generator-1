package com.ichings.generator.tag;

/**
 * Unique 模板
 *
 * @author 宋欢
 */
public final class UniqueTemplate {
    /**
     * Database Interface
     */
    public static final String DATABASE = "" +
            "{leftTab}/**\n" +
            "{leftTab} * 通过“{columnRemark}”，获取“主键”\n" +
            "{leftTab} *\n" +
            "{leftTab} * @param {columnNameCamelLowerFirst} {columnRemark}\n" +
            "{leftTab} * @return 主键\n" +
            "{leftTab} */\n" +
            "{leftTab}Long getBy{columnNameCamelUpperFirst}(@Param(\"{columnNameCamelLowerFirst}\") String {columnNameCamelLowerFirst});";

    /**
     * Mybatis Resource
     */
    public static final String MYBATIS_RESOURCE = "" +
            "{leftTab}<select id=\"getBy{columnNameCamelUpperFirst}\" resultType=\"java.lang.Long\">\n" +
            "{leftTab}    SELECT id\n" +
            "{leftTab}    FROM {tableNameUnderscoreLower}\n" +
            "{leftTab}    <where>\n" +
            "{leftTab}        AND {columnName} = #{{columnNameCamelLowerFirst},jdbcType=VARCHAR}\n" +
            "{leftTab}    </where>\n" +
            "{leftTab}    LIMIT 1\n" +
            "{leftTab}</select>";

    /**
     * Repository Interface
     */
    public static final String REPOSITORY = "" +
            "{leftTab}/**\n" +
            "{leftTab} * 通过“{columnRemark}”，获取“主键”\n" +
            "{leftTab} * 无缓存\n" +
            "{leftTab} *\n" +
            "{leftTab} * @param {columnNameCamelLowerFirst} {columnRemark}\n" +
            "{leftTab} * @return 主键\n" +
            "{leftTab} */\n" +
            "{leftTab}Long getBy{columnNameCamelUpperFirst}(String {columnNameCamelLowerFirst});";

    /**
     * Repository Implements
     */
    public static final String REPOSITORY_IMPL = "" +
            "{leftTab}@Override\n" +
            "{leftTab}public Long getBy{columnNameCamelUpperFirst}(String {columnNameCamelLowerFirst}) {\n" +
            "{leftTab}    try {\n" +
            "{leftTab}        AssertUtils.nonEmpty({columnNameCamelLowerFirst}, \"{columnNameCamelLowerFirst}\");\n\n" +
            "{leftTab}        return database.getBy{columnNameCamelUpperFirst}({columnNameCamelLowerFirst});\n" +
            "{leftTab}    } catch (Throwable tr) {\n" +
            "{leftTab}        LOGGER.error(\"getBy{columnNameCamelUpperFirst} failed, {columnNameCamelLowerFirst}: {}, throwable: \", {columnNameCamelLowerFirst}, tr);\n" +
            "{leftTab}        throw new BizException(Errors.FIND_ERR);\n" +
            "{leftTab}    }\n" +
            "{leftTab}}";

    /**
     * Service Interface
     */
    public static final String SERVICE = "" +
            "{leftTab}/**\n" +
            "{leftTab} * 通过“{columnRemark}”，获取“主键”\n" +
            "{leftTab} *\n" +
            "{leftTab} * @param {columnNameCamelLowerFirst} {columnRemark}\n" +
            "{leftTab} * @return 主键\n" +
            "{leftTab} */\n" +
            "{leftTab}Long getBy{columnNameCamelUpperFirst}(String {columnNameCamelLowerFirst});";

    /**
     * Service Implements
     */
    public static final String SERVICE_IMPL = "" +
            "{leftTab}@Override\n" +
            "{leftTab}public Long getBy{columnNameCamelUpperFirst}(String {columnNameCamelLowerFirst}) {\n" +
            "{leftTab}    return repository.getBy{columnNameCamelUpperFirst}({columnNameCamelLowerFirst});\n" +
            "{leftTab}}";

    /**
     * Service Implements Insert
     */
    public static final String SERVICE_IMPL_INSERT = "" +
            "{leftTab}String {columnNameCamelLowerFirst} = dto.get{columnNameCamelUpperFirst}();\n" +
            "{leftTab}BizAssert.nonEmpty({columnNameCamelLowerFirst}, BizError.{columnNameUnderscoreUpper}_REQUIRED);\n\n" +
            "{leftTab}Long oldId1 = repository.getBy{columnNameCamelUpperFirst}({columnNameCamelLowerFirst});\n" +
            "{leftTab}BizAssert.isNull(oldId1, BizError.{columnNameUnderscoreUpper}_CONFLICT);\n\n" +
            "{leftTab}dto.set{columnNameCamelUpperFirst}({columnNameCamelLowerFirst});";

    /**
     * Service Implements Update
     */
    public static final String SERVICE_IMPL_UPDATE = "" +
            "{leftTab}String {columnNameCamelLowerFirst} = dto.get{columnNameCamelUpperFirst}();\n" +
            "{leftTab}if (Check.nonEmpty({columnNameCamelLowerFirst})) {\n" +
            "{leftTab}    Long oldId = repository.getBy{columnNameCamelUpperFirst}({columnNameCamelLowerFirst});\n" +
            "{leftTab}    if (Check.nonNull(oldId)) {\n" +
            "{leftTab}        BizAssert.isTrue(id.equals(oldId), BizError.{columnNameUnderscoreUpper}_CONFLICT);\n" +
            "{leftTab}    }\n" +
            "{leftTab}} else {\n" +
            "{leftTab}    dto.set{columnNameCamelUpperFirst}(null);\n" +
            "{leftTab}}";

    /**
     * Controller
     */
    public static final String CONTROLLER = "" +
            "{leftTab}@ApiOperation(value = \"通过{columnRemark}，获取主键\", produces = \"application/json\")\n" +
            "{leftTab}@GetMapping(value = \"/{columnNameHyphenLower}\")\n" +
            "{leftTab}@ResponseBody\n" +
            "{leftTab}public Result<Long> getBy{columnNameCamelUpperFirst}(@NotEmpty @RequestParam(\"{columnNameCamelLowerFirst}\") String {columnNameCamelLowerFirst}) {\n" +
            "{leftTab}    LOGGER.info(\"getBy{columnNameCamelUpperFirst} trace, {columnNameCamelLowerFirst}: {}\", {columnNameCamelLowerFirst});\n\n" +
            "{leftTab}    Long id = service.getBy{columnNameCamelUpperFirst}({columnNameCamelLowerFirst});\n" +
            "{leftTab}    return ResultUtils.toSuccess(id);\n" +
            "{leftTab}}";

}
