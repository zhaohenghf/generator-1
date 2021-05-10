# generator
## 生成代码和数据字典

### 生成代码
```
http://127.0.0.1:9090/code
```

### 生成数据字典
```
http://127.0.0.1:9090/dict
```

### 接口文档
```
http://127.0.0.1:9090/swagger-ui/
```

### 标签
```
${authorName}
${packageName}
${classTitle}
${tableName}
${tableNameCamelLowerFirst}
${tableNameCamelUpperFirst}
${tableNameHyphenLower}
${tableNameHyphenUpper}
${tableNameUnderscoreLower}
${tableNameUnderscoreUpper}
```

##### ${poFields}
```
private Long id;
private String phone;
...
```

##### ${poMethods}
```
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

...
```

##### ${poCriteriaFields}
```
private String phone;
private String username;
...
```

##### ${poCriteriaMethods}
```
public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

...
```

##### ${poMapperInsert}
```
@Mapping(source = "phone", target = "phone", defaultValue = "")
@Mapping(source = "activeSw", target = "activeSw", defaultValue = "0")
...
```

##### ${dtoFields}
```
private Long id;
private String phone;
...
```

##### ${dtoMethods}
```
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

...
```

##### ${voFields}
```
@ApiModelProperty(value = "用户id", required = false, dataType = "Long", example = "123456789012345678")
private Long id;

@ApiModelProperty(value = "手机号", required = false, dataType = "String", example = "13123456789")
private String phone;

...
```

##### ${voMethods}
```
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

...
```

##### ${voReqCreateFields}
```
@NotBlank(message = "请填写手机号")
@ApiModelProperty(value = "手机号", required = true, dataType = "String", example = "13123456789")
private String phone;

@ApiModelProperty(value = "姓名", required = false, dataType = "String", example = "张三")
private String username;

...
```

##### ${voReqCreateMethods}
```
public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

...
```

##### ${voReqCriteriaFields}
```
@ApiModelProperty(value = "手机号", required = false, dataType = "String", example = "13123456789")
private String phone;

@ApiModelProperty(value = "姓名", required = false, dataType = "String", example = "张三")
private String username;

...
```

##### ${voReqCriteriaMethods}
```
public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

...
```

##### ${voReqUpdateFields}
```
@NotNull(message = "请填写用户id")
@ApiModelProperty(value = "用户id", required = true, dataType = "Long", example = "123456789012345678")
private Long id;

@ApiModelProperty(value = "手机号", required = false, dataType = "String", example = "13123456789")
private String phone;

...
```

##### ${voReqUpdateMethods}
```
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

...
```

##### ${repositoryCheckAndInitializeInsert}
```
AssertUtils.isPositive(r.getId(), "id");
AssertUtils.nonNull(r.getPhone(), "phone");
...
```

##### ${uniqueDatabase}
```
Long getByPhone(@Param("phone") String phone);
...
```

##### ${uniqueMybatisResource}
```
<select id="getByPhone" resultType="java.lang.Long">
    SELECT id
    FROM demo
    <where>
        AND phone = #{phone,jdbcType=VARCHAR}
    </where>
    LIMIT 1
</select>
...
```

##### ${uniqueRepository}
```
Long getByPhone(String phone);
...
```

##### ${uniqueRepositoryImpl}
```
@Override
public Long getByPhone(String phone) {
    try {
        AssertUtils.nonEmpty(phone, "phone");

        return database.getByPhone(phone);
    } catch (Throwable tr) {
        LOGGER.error("getByPhone failed, phone: {}, throwable: ", phone, tr);
        throw new BizException(Errors.FIND_ERR);
    }
}

...
```

##### ${uniqueService}
```
Long getByPhone(String phone);
...
```

##### ${uniqueServiceImpl}
```
@Override
public Long getByPhone(String phone) {
    return repository.getByPhone(phone);
}
...
```

##### ${uniqueServiceImplInsert}
```
String phone = dto.getPhone();
BizAssert.nonEmpty(phone, BizError.PHONE_REQUIRED);

Long oldId1 = repository.getByPhone(phone);
BizAssert.isNull(oldId1, BizError.PHONE_CONFLICT);

dto.setPhone(phone);

...
```

##### ${uniqueServiceImplUpdate}
```
String phone = dto.getPhone();
if (Check.nonEmpty(phone)) {
    Long oldId = repository.getByPhone(phone);
    if (Check.nonNull(oldId)) {
        BizAssert.isTrue(id.equals(oldId), BizError.PHONE_CONFLICT);
    }
} else {
    dto.setPhone(null);
}

...
```

##### ${uniqueController}
```
@ApiOperation(value = "通过手机号，获取主键", produces = "application/json")
@GetMapping(value = "/phone")
@ResponseBody
public Result<Long> getByPhone(@NotEmpty @RequestParam("phone") String phone) {
    LOGGER.info("getByPhone trace, phone: {}", phone);

    Long id = service.getByPhone(phone);
    return ResultUtils.toSuccess(id);
}

...
```

##### ${mybatisResourceBaseColumnList}
```
id,
phone,
username,
...
```

##### ${mybatisResourceBaseResultMap}
```
<id column="id" jdbcType="BIGINT" property="id"/>
<result column="phone" jdbcType="VARCHAR" property="phone"/>
<result column="username" jdbcType="VARCHAR" property="username"/>
...
```

##### ${mybatisResourceCriteria}
```
<if test="phone != null">
    AND phone = #{phone,jdbcType=VARCHAR},
</if>
<if test="username != null">
    AND username = #{username,jdbcType=VARCHAR},
</if>
...
```

##### ${mybatisResourceInsertColumnList}
```
id,
phone,
username,
...
```

##### ${mybatisResourceInsertValueList}
```
#{id,jdbcType=BIGINT},
#{phone,jdbcType=VARCHAR},
#{username,jdbcType=VARCHAR},
...
```

##### ${mybatisResourceInsertListColumnList}
```
id,
phone,
username,
...
```

##### ${mybatisResourceInsertListValueList}
```
#{record.id,jdbcType=BIGINT},
#{record.phone,jdbcType=VARCHAR},
#{record.username,jdbcType=VARCHAR},
...
```

##### ${mybatisResourceUpdateFieldList}
```
<if test="phone != null">
    phone = #{phone,jdbcType=VARCHAR},
</if>
<if test="username != null">
    username = #{username,jdbcType=VARCHAR},
</if>
...
```

##### ${mybatisResourceUpdateListFieldList}
```
<if test="record.phone != null">
    phone = #{record.phone,jdbcType=VARCHAR},
</if>
<if test="record.username != null">
    username = #{record.username,jdbcType=VARCHAR},
</if>
...
```

### 竞品
```
Mybatis Generator

EasyCode
```
