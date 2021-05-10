package ${packageName}.biz.repository;

import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Attribute;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;

import java.util.List;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
public interface ${tableNameCamelUpperFirst}Repository {
    /**
     * 查询列表
     * 无缓存
     *
     * @param criteria 查询条件
     * @return list
     */
    List<${tableNameCamelUpperFirst}> findList(${tableNameCamelUpperFirst}Criteria criteria);

    /**
     * 查询总数，Count
     * 无缓存
     *
     * @param criteria 查询条件
     * @return long
     */
    long getTotalNum(${tableNameCamelUpperFirst}Criteria criteria);

    /**
     * 通过主键，获取一条记录
     * 有缓存
     *
     * @param id 主键
     * @return ${tableNameCamelUpperFirst}
     */
    ${tableNameCamelUpperFirst} find(Long id);

    ${uniqueRepository}

    /**
     * 新增多条记录
     * 事务
     *
     * @param list ${tableNameCamelUpperFirst}
     * @return 成功？
     */
    boolean insertList(List<${tableNameCamelUpperFirst}> list);

    /**
     * 新增一条记录
     * 事务
     *
     * @param record    ${tableNameCamelUpperFirst}
     * @param attribute ${tableNameCamelUpperFirst}Attribute
     * @return 成功？
     */
    boolean insert(${tableNameCamelUpperFirst} record, ${tableNameCamelUpperFirst}Attribute attribute);

    /**
     * 通过主键，修改多条记录
     * 事务
     *
     * @param list ${tableNameCamelUpperFirst}
     * @return 成功？
     */
    boolean updateList(List<${tableNameCamelUpperFirst}> list);

    /**
     * 通过主键，修改一条记录
     * 事务
     *
     * @param record    ${tableNameCamelUpperFirst}
     * @param attribute ${tableNameCamelUpperFirst}Attribute
     * @return 成功？
     */
    boolean update(${tableNameCamelUpperFirst} record, ${tableNameCamelUpperFirst}Attribute attribute);

    /**
     * 通过主键，删除多条记录
     * 事务
     *
     * @param ids 主键列表
     * @return 成功？
     */
    boolean deleteList(List<Long> ids);

    /**
     * 通过主键，删除一条记录
     *
     * @param id 主键
     * @return 成功？
     */
    boolean delete(Long id);

    /**
     * 通过“主表id”，获取扩展属性
     * 有缓存
     *
     * @param baseId 主表id
     * @return ${tableNameCamelUpperFirst}Attribute
     */
    ${tableNameCamelUpperFirst}Attribute getAttribute(Long baseId);

    /**
     * 通过“主表id”，保存扩展属性
     *
     * @param baseId 主表id
     * @param record ${tableNameCamelUpperFirst}Attribute
     * @return 成功？
     */
    boolean setAttribute(Long baseId, ${tableNameCamelUpperFirst}Attribute record);

    /**
     * 通过“主表id”和“属性id”，获取属性值
     * 无缓存
     *
     * @param baseId 主表id
     * @param attributeId 属性id
     * @return attributeValue
     */
    String getValue(Long baseId, Integer attributeId);

}
