package com.ichings.generator.tag;

import com.ichings.generator.util.Check;
import com.ichings.generator.util.JsonParser;
import com.ichings.generator.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 标签值，替换模板标签，如：${***}
 *
 * @author 宋欢
 */
public final class TagValue implements Serializable {
    /**
     * ${authorName}
     */
    private String authorName;

    /**
     * ${packageName}
     */
    private String packageName;

    /**
     * ${classTitle}
     */
    private String classTitle;

    /**
     * ${tableName}
     */
    private String tableName;

    /**
     * ${tableNameCamelLowerFirst}
     */
    private String tableNameCamelLowerFirst;

    /**
     * ${tableNameCamelUpperFirst}
     */
    private String tableNameCamelUpperFirst;

    /**
     * ${tableNameHyphenLower}
     */
    private String tableNameHyphenLower;

    /**
     * ${tableNameHyphenUpper}
     */
    private String tableNameHyphenUpper;

    /**
     * ${tableNameUnderscoreLower}
     */
    private String tableNameUnderscoreLower;

    /**
     * ${tableNameUnderscoreUpper}
     */
    private String tableNameUnderscoreUpper;

    /**
     * ${poFields}
     * <pre>
     * private Long id;
     * private String phone;
     * ...
     */
    private String poFields;

    /**
     * ${poMethods}
     * <pre>
     * public Long getId() {
     *     return id;
     * }
     * public void setId(Long id) {
     *     this.id = id;
     * }
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * ...
     */
    private String poMethods;

    /**
     * ${poCriteriaFields}
     * <pre>
     * private String phone;
     * private String username;
     * ...
     */
    private String poCriteriaFields;

    /**
     * ${poCriteriaMethods}
     * <pre>
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * public String getUsername() {
     *     return username;
     * }
     * public void setUsername(String username) {
     *     this.username = username;
     * }
     * ...
     */
    private String poCriteriaMethods;

    /**
     * ${poMapperInsert}
     * <pre>
     * Mapping(source = "phone", target = "phone", defaultValue = "")
     * Mapping(source = "activeSw", target = "activeSw", defaultValue = "0")
     * ...
     */
    private String poMapperInsert;

    /**
     * ${dtoFields}
     * <pre>
     * private Long id;
     * private String phone;
     * ...
     */
    private String dtoFields;

    /**
     * ${dtoMethods}
     * <pre>
     * public Long getId() {
     *     return id;
     * }
     * public void setId(Long id) {
     *     this.id = id;
     * }
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * ...
     */
    private String dtoMethods;

    /**
     * ${voFields}
     * <pre>
     * ApiModelProperty(value = "用户id", required = false, dataType = "Long", example = "123456789012345678")
     * private Long id;
     *
     * ApiModelProperty(value = "手机号", required = false, dataType = "String", example = "13123456789")
     * private String phone;
     * ...
     */
    private String voFields;

    /**
     * ${voMethods}
     * <pre>
     * public Long getId() {
     *     return id;
     * }
     * public void setId(Long id) {
     *     this.id = id;
     * }
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * ...
     */
    private String voMethods;

    /**
     * ${voReqCreateFields}
     * <pre>
     * NotBlank(message = "请填写手机号")
     * ApiModelProperty(value = "手机号", required = true, dataType = "String", example = "13123456789")
     * private String phone;
     *
     * ApiModelProperty(value = "姓名", required = false, dataType = "String", example = "张三")
     * private String username;
     * ...
     */
    private String voReqCreateFields;

    /**
     * ${voReqCreateMethods}
     * <pre>
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * public String getUsername() {
     *     return username;
     * }
     * public void setUsername(String username) {
     *     this.username = username;
     * }
     * ...
     */
    private String voReqCreateMethods;

    /**
     * ${voReqCriteriaFields}
     * <pre>
     * ApiModelProperty(value = "手机号", required = false, dataType = "String", example = "13123456789")
     * private String phone;
     *
     * ApiModelProperty(value = "姓名", required = false, dataType = "String", example = "张三")
     * private String username;
     * ...
     */
    private String voReqCriteriaFields;

    /**
     * ${voReqCriteriaMethods}
     * <pre>
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * public String getUsername() {
     *     return username;
     * }
     * public void setUsername(String username) {
     *     this.username = username;
     * }
     * ...
     */
    private String voReqCriteriaMethods;

    /**
     * ${voReqUpdateFields}
     * <pre>
     * NotNull(message = "请填写用户id")
     * ApiModelProperty(value = "用户id", required = true, dataType = "Long", example = "123456789012345678")
     * private Long id;
     *
     * ApiModelProperty(value = "手机号", required = false, dataType = "String", example = "13123456789")
     * private String phone;
     * ...
     */
    private String voReqUpdateFields;

    /**
     * ${voReqUpdateMethods}
     * <pre>
     * public Long getId() {
     *     return id;
     * }
     * public void setId(Long id) {
     *     this.id = id;
     * }
     * public String getPhone() {
     *     return phone;
     * }
     * public void setPhone(String phone) {
     *     this.phone = phone;
     * }
     * ...
     */
    private String voReqUpdateMethods;

    /**
     * ${repositoryCheckAndInitializeInsert}
     * <pre>
     * AssertUtils.isPositive(r.getId(), "id");
     * AssertUtils.nonNull(r.getPhone(), "phone");
     * ...
     */
    private String repositoryCheckAndInitializeInsert;

    /**
     * ${uniqueDatabase}
     * <pre>
     * Long getByPhone(@Param("phone") String phone);
     * ...
     */
    private String uniqueDatabase;

    /**
     * ${uniqueMybatisResource}
     * <pre>
     * <select id="getByPhone" resultType="java.lang.Long">
     * 	   SELECT id
     * 	   FROM demo
     * 	   <where>
     * 	       AND phone = #{phone,jdbcType=VARCHAR}
     * 	   </where>
     * 	   LIMIT 1
     * </select>
     * ...
     */
    private String uniqueMybatisResource;

    /**
     * ${uniqueRepository}
     * <pre>
     * Long getByPhone(String phone);
     * ...
     */
    private String uniqueRepository;

    /**
     * ${uniqueRepositoryImpl}
     * <pre>
     * Override
     * public Long getByPhone(String phone) {
     *     try {
     *         AssertUtils.nonEmpty(phone, "phone");
     *
     *         return database.getByPhone(phone);
     *     } catch (Throwable tr) {
     *         LOGGER.error("getByPhone failed, phone: {}, throwable: ", phone, tr);
     *         throw new BizException(Errors.FIND_ERR);
     *     }
     * }
     * ...
     */
    private String uniqueRepositoryImpl;

    /**
     * ${uniqueService}
     * <pre>
     * Long getByPhone(String phone);
     * ...
     */
    private String uniqueService;

    /**
     * ${uniqueServiceImpl}
     * <pre>
     * Override
     * public Long getByPhone(String phone) {
     *     return repository.getByPhone(phone);
     * }
     * ...
     */
    private String uniqueServiceImpl;

    /**
     * ${uniqueServiceImplInsert}
     * <pre>
     * String phone = dto.getPhone();
     * BizAssert.nonEmpty(phone, BizError.PHONE_REQUIRED);
     *
     * Long oldId1 = repository.getByPhone(phone);
     * BizAssert.isNull(oldId1, BizError.PHONE_CONFLICT);
     *
     * dto.setPhone(phone);
     * ...
     */
    private String uniqueServiceImplInsert;

    /**
     * ${uniqueServiceImplUpdate}
     * <pre>
     * String phone = dto.getPhone();
     * if (Check.nonEmpty(phone)) {
     *     Long oldId = repository.getByPhone(phone);
     *     if (Check.nonNull(oldId)) {
     *         BizAssert.isTrue(id.equals(oldId), BizError.PHONE_CONFLICT);
     *     }
     * } else {
     *     dto.setPhone(null);
     * }
     * ...
     */
    private String uniqueServiceImplUpdate;

    /**
     * ${uniqueController}
     * <pre>
     * ApiOperation(value = "通过手机号，获取主键", produces = "application/json")
     * GetMapping(value = "/phone")
     * ResponseBody
     * public Result<Long> getByPhone(@NotEmpty @RequestParam("phone") String phone) {
     *     LOGGER.info("getByPhone trace, phone: {}", phone);
     *
     *     Long id = service.getByPhone(phone);
     *     return ResultUtils.toSuccess(id);
     * }
     * ...
     */
    private String uniqueController;

    /**
     * ${mybatisResourceBaseColumnList}
     * <pre>
     * id,
     * phone,
     * username,
     * ...
     */
    private String mybatisResourceBaseColumnList;

    /**
     * ${mybatisResourceBaseResultMap}
     * <pre>
     * <id column="id" jdbcType="BIGINT" property="id"/>
     * <result column="phone" jdbcType="VARCHAR" property="phone"/>
     * <result column="username" jdbcType="VARCHAR" property="username"/>
     * ...
     */
    private String mybatisResourceBaseResultMap;

    /**
     * ${mybatisResourceCriteria}
     * <pre>
     * <if test="phone != null">
     *     AND phone = #{phone,jdbcType=VARCHAR},
     * </if>
     * <if test="username != null">
     *     AND username = #{username,jdbcType=VARCHAR},
     * </if>
     * ...
     */
    private String mybatisResourceCriteria;

    /**
     * ${mybatisResourceInsertColumnList}
     * <pre>
     * id,
     * phone,
     * username,
     * ...
     */
    private String mybatisResourceInsertColumnList;

    /**
     * ${mybatisResourceInsertValueList}
     * <pre>
     * #{id,jdbcType=BIGINT},
     * #{phone,jdbcType=VARCHAR},
     * #{username,jdbcType=VARCHAR},
     * ...
     */
    private String mybatisResourceInsertValueList;

    /**
     * ${mybatisResourceInsertListColumnList}
     * <pre>
     * id,
     * phone,
     * username,
     * ...
     */
    private String mybatisResourceInsertListColumnList;

    /**
     * ${mybatisResourceInsertListValueList}
     * <pre>
     * #{record.id,jdbcType=BIGINT},
     * #{record.phone,jdbcType=VARCHAR},
     * #{record.username,jdbcType=VARCHAR},
     * ...
     */
    private String mybatisResourceInsertListValueList;

    /**
     * ${mybatisResourceUpdateFieldList}
     * <pre>
     * <if test="phone != null">
     *     phone = #{phone,jdbcType=VARCHAR},
     * </if>
     * <if test="username != null">
     *     username = #{username,jdbcType=VARCHAR},
     * </if>
     * ...
     */
    private String mybatisResourceUpdateFieldList;

    /**
     * ${mybatisResourceUpdateListFieldList}
     * <pre>
     * <if test="record.phone != null">
     *     phone = #{record.phone,jdbcType=VARCHAR},
     * </if>
     * <if test="record.username != null">
     *     username = #{record.username,jdbcType=VARCHAR},
     * </if>
     * ...
     */
    private String mybatisResourceUpdateListFieldList;

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    /**
     * 获取模板数据
     *
     * @return Map<String, String>，name => code
     */
    public Map<String, String> toMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = TagValue.class.getDeclaredFields();
        Map<String, String> result = new HashMap<>(fields.length);

        for (Field f : fields) {
            String fieldName = f.getName();
            String methodName = "get" + StringUtils.upperFirst(fieldName, false);

            Object code = TagValue.class.getMethod(methodName).invoke(this);
            result.put(fieldName, Check.nonNull(code) ? (String) code : "");
        }

        return result;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNameCamelLowerFirst() {
        return tableNameCamelLowerFirst;
    }

    public void setTableNameCamelLowerFirst(String tableNameCamelLowerFirst) {
        this.tableNameCamelLowerFirst = tableNameCamelLowerFirst;
    }

    public String getTableNameCamelUpperFirst() {
        return tableNameCamelUpperFirst;
    }

    public void setTableNameCamelUpperFirst(String tableNameCamelUpperFirst) {
        this.tableNameCamelUpperFirst = tableNameCamelUpperFirst;
    }

    public String getTableNameHyphenLower() {
        return tableNameHyphenLower;
    }

    public void setTableNameHyphenLower(String tableNameHyphenLower) {
        this.tableNameHyphenLower = tableNameHyphenLower;
    }

    public String getTableNameHyphenUpper() {
        return tableNameHyphenUpper;
    }

    public void setTableNameHyphenUpper(String tableNameHyphenUpper) {
        this.tableNameHyphenUpper = tableNameHyphenUpper;
    }

    public String getTableNameUnderscoreLower() {
        return tableNameUnderscoreLower;
    }

    public void setTableNameUnderscoreLower(String tableNameUnderscoreLower) {
        this.tableNameUnderscoreLower = tableNameUnderscoreLower;
    }

    public String getTableNameUnderscoreUpper() {
        return tableNameUnderscoreUpper;
    }

    public void setTableNameUnderscoreUpper(String tableNameUnderscoreUpper) {
        this.tableNameUnderscoreUpper = tableNameUnderscoreUpper;
    }

    public String getPoFields() {
        return poFields;
    }

    public void setPoFields(String poFields) {
        this.poFields = poFields;
    }

    public String getPoMethods() {
        return poMethods;
    }

    public void setPoMethods(String poMethods) {
        this.poMethods = poMethods;
    }

    public String getPoCriteriaFields() {
        return poCriteriaFields;
    }

    public void setPoCriteriaFields(String poCriteriaFields) {
        this.poCriteriaFields = poCriteriaFields;
    }

    public String getPoCriteriaMethods() {
        return poCriteriaMethods;
    }

    public void setPoCriteriaMethods(String poCriteriaMethods) {
        this.poCriteriaMethods = poCriteriaMethods;
    }

    public String getPoMapperInsert() {
        return poMapperInsert;
    }

    public void setPoMapperInsert(String poMapperInsert) {
        this.poMapperInsert = poMapperInsert;
    }

    public String getDtoFields() {
        return dtoFields;
    }

    public void setDtoFields(String dtoFields) {
        this.dtoFields = dtoFields;
    }

    public String getDtoMethods() {
        return dtoMethods;
    }

    public void setDtoMethods(String dtoMethods) {
        this.dtoMethods = dtoMethods;
    }

    public String getVoFields() {
        return voFields;
    }

    public void setVoFields(String voFields) {
        this.voFields = voFields;
    }

    public String getVoMethods() {
        return voMethods;
    }

    public void setVoMethods(String voMethods) {
        this.voMethods = voMethods;
    }

    public String getVoReqCreateFields() {
        return voReqCreateFields;
    }

    public void setVoReqCreateFields(String voReqCreateFields) {
        this.voReqCreateFields = voReqCreateFields;
    }

    public String getVoReqCreateMethods() {
        return voReqCreateMethods;
    }

    public void setVoReqCreateMethods(String voReqCreateMethods) {
        this.voReqCreateMethods = voReqCreateMethods;
    }

    public String getVoReqCriteriaFields() {
        return voReqCriteriaFields;
    }

    public void setVoReqCriteriaFields(String voReqCriteriaFields) {
        this.voReqCriteriaFields = voReqCriteriaFields;
    }

    public String getVoReqCriteriaMethods() {
        return voReqCriteriaMethods;
    }

    public void setVoReqCriteriaMethods(String voReqCriteriaMethods) {
        this.voReqCriteriaMethods = voReqCriteriaMethods;
    }

    public String getVoReqUpdateFields() {
        return voReqUpdateFields;
    }

    public void setVoReqUpdateFields(String voReqUpdateFields) {
        this.voReqUpdateFields = voReqUpdateFields;
    }

    public String getVoReqUpdateMethods() {
        return voReqUpdateMethods;
    }

    public void setVoReqUpdateMethods(String voReqUpdateMethods) {
        this.voReqUpdateMethods = voReqUpdateMethods;
    }

    public String getRepositoryCheckAndInitializeInsert() {
        return repositoryCheckAndInitializeInsert;
    }

    public void setRepositoryCheckAndInitializeInsert(String repositoryCheckAndInitializeInsert) {
        this.repositoryCheckAndInitializeInsert = repositoryCheckAndInitializeInsert;
    }

    public String getUniqueDatabase() {
        return uniqueDatabase;
    }

    public void setUniqueDatabase(String uniqueDatabase) {
        this.uniqueDatabase = uniqueDatabase;
    }

    public String getUniqueMybatisResource() {
        return uniqueMybatisResource;
    }

    public void setUniqueMybatisResource(String uniqueMybatisResource) {
        this.uniqueMybatisResource = uniqueMybatisResource;
    }

    public String getUniqueRepository() {
        return uniqueRepository;
    }

    public void setUniqueRepository(String uniqueRepository) {
        this.uniqueRepository = uniqueRepository;
    }

    public String getUniqueRepositoryImpl() {
        return uniqueRepositoryImpl;
    }

    public void setUniqueRepositoryImpl(String uniqueRepositoryImpl) {
        this.uniqueRepositoryImpl = uniqueRepositoryImpl;
    }

    public String getUniqueService() {
        return uniqueService;
    }

    public void setUniqueService(String uniqueService) {
        this.uniqueService = uniqueService;
    }

    public String getUniqueServiceImpl() {
        return uniqueServiceImpl;
    }

    public void setUniqueServiceImpl(String uniqueServiceImpl) {
        this.uniqueServiceImpl = uniqueServiceImpl;
    }

    public String getUniqueServiceImplInsert() {
        return uniqueServiceImplInsert;
    }

    public void setUniqueServiceImplInsert(String uniqueServiceImplInsert) {
        this.uniqueServiceImplInsert = uniqueServiceImplInsert;
    }

    public String getUniqueServiceImplUpdate() {
        return uniqueServiceImplUpdate;
    }

    public void setUniqueServiceImplUpdate(String uniqueServiceImplUpdate) {
        this.uniqueServiceImplUpdate = uniqueServiceImplUpdate;
    }

    public String getUniqueController() {
        return uniqueController;
    }

    public void setUniqueController(String uniqueController) {
        this.uniqueController = uniqueController;
    }

    public String getMybatisResourceBaseColumnList() {
        return mybatisResourceBaseColumnList;
    }

    public void setMybatisResourceBaseColumnList(String mybatisResourceBaseColumnList) {
        this.mybatisResourceBaseColumnList = mybatisResourceBaseColumnList;
    }

    public String getMybatisResourceBaseResultMap() {
        return mybatisResourceBaseResultMap;
    }

    public void setMybatisResourceBaseResultMap(String mybatisResourceBaseResultMap) {
        this.mybatisResourceBaseResultMap = mybatisResourceBaseResultMap;
    }

    public String getMybatisResourceCriteria() {
        return mybatisResourceCriteria;
    }

    public void setMybatisResourceCriteria(String mybatisResourceCriteria) {
        this.mybatisResourceCriteria = mybatisResourceCriteria;
    }

    public String getMybatisResourceInsertColumnList() {
        return mybatisResourceInsertColumnList;
    }

    public void setMybatisResourceInsertColumnList(String mybatisResourceInsertColumnList) {
        this.mybatisResourceInsertColumnList = mybatisResourceInsertColumnList;
    }

    public String getMybatisResourceInsertValueList() {
        return mybatisResourceInsertValueList;
    }

    public void setMybatisResourceInsertValueList(String mybatisResourceInsertValueList) {
        this.mybatisResourceInsertValueList = mybatisResourceInsertValueList;
    }

    public String getMybatisResourceInsertListColumnList() {
        return mybatisResourceInsertListColumnList;
    }

    public void setMybatisResourceInsertListColumnList(String mybatisResourceInsertListColumnList) {
        this.mybatisResourceInsertListColumnList = mybatisResourceInsertListColumnList;
    }

    public String getMybatisResourceInsertListValueList() {
        return mybatisResourceInsertListValueList;
    }

    public void setMybatisResourceInsertListValueList(String mybatisResourceInsertListValueList) {
        this.mybatisResourceInsertListValueList = mybatisResourceInsertListValueList;
    }

    public String getMybatisResourceUpdateFieldList() {
        return mybatisResourceUpdateFieldList;
    }

    public void setMybatisResourceUpdateFieldList(String mybatisResourceUpdateFieldList) {
        this.mybatisResourceUpdateFieldList = mybatisResourceUpdateFieldList;
    }

    public String getMybatisResourceUpdateListFieldList() {
        return mybatisResourceUpdateListFieldList;
    }

    public void setMybatisResourceUpdateListFieldList(String mybatisResourceUpdateListFieldList) {
        this.mybatisResourceUpdateListFieldList = mybatisResourceUpdateListFieldList;
    }

}
