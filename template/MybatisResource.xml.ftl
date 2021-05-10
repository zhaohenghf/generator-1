xmlName=${tableNameCamelUpperFirst}Mapper
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.infrastructure.database.${tableNameCamelUpperFirst}Database">
    <resultMap id="BaseResultMap" type="${packageName}.infrastructure.model.${tableNameCamelUpperFirst}">
        ${mybatisResourceBaseResultMap}
    </resultMap>
    <sql id="Base_Column_List">
        ${mybatisResourceBaseColumnList}
    </sql>
    <select id="findList" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName}
        <where>
            ${mybatisResourceCriteria}
            AND deleted_id = 0
        </where>
        ORDER BY sort ASC
        <if test="pageSize > 0">
            LIMIT ${r'${startRow}'}, ${r'${pageSize}'}
        </if>
    </select>
    <select id="getTotalNum" resultType="long">
        SELECT COUNT(*) AS aggregate
        FROM ${tableName}
        <where>
            ${mybatisResourceCriteria}
            AND deleted_id = 0
        </where>
    </select>
    <select id="find" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName}
        <where>
            AND id = ${r'#{id,jdbcType=BIGINT}'}
            AND deleted_id = 0
        </where>
    </select>
    ${uniqueMybatisResource}
    <insert id="insertList" parameterType="java.util.List">
        INSERT INTO ${tableName}
        (${mybatisResourceInsertListColumnList})
        VALUES
        <foreach collection="list" item="record" index="index" separator=",">
            <if test="record != null">
                (${mybatisResourceInsertListValueList})
            </if>
        </foreach>
    </insert>
    <insert id="insert" parameterType="${packageName}.infrastructure.model.${tableNameCamelUpperFirst}">
        INSERT INTO ${tableName}
        (${mybatisResourceInsertColumnList})
        VALUES (${mybatisResourceInsertValueList})
    </insert>
    <update id="updateList" parameterType="java.util.List">
        <foreach collection="list" item="record" index="index" open="" close="" separator=";">
            <if test="record != null">
                UPDATE ${tableName}
                <set>
                    ${mybatisResourceUpdateListFieldList}
                </set>
                <where>
                    AND id = ${r'${record.id}'}
                </where>
            </if>
        </foreach>
    </update>
    <update id="update" parameterType="${packageName}.infrastructure.model.${tableNameCamelUpperFirst}">
        UPDATE ${tableName}
        <set>
            ${mybatisResourceUpdateFieldList}
        </set>
        <where>
            AND id = ${r'#{id,jdbcType=BIGINT}'}
        </where>
    </update>
</mapper>