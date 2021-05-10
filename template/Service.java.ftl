package ${packageName}.biz.service;

import com.ichings.common.page.PageList;
import ${packageName}.biz.dto.${tableNameCamelUpperFirst}Dto;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;

import java.util.List;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
public interface ${tableNameCamelUpperFirst}Service {
    /**
     * 分页查询
     *
     * @param criteria 查询条件
     * @return PageList
     */
    PageList<${tableNameCamelUpperFirst}Dto> findPage(${tableNameCamelUpperFirst}Criteria criteria);

    /**
     * 查询列表
     *
     * @param criteria 查询条件
     * @return list
     */
    List<${tableNameCamelUpperFirst}Dto> findList(${tableNameCamelUpperFirst}Criteria criteria);

    /**
     * 查询总数，Count
     *
     * @param criteria 查询条件
     * @return long
     */
    long getTotalNum(${tableNameCamelUpperFirst}Criteria criteria);

    /**
     * 通过主键，获取一条记录
     *
     * @param id 主键
     * @return ${tableNameCamelUpperFirst}Dto
     */
    ${tableNameCamelUpperFirst}Dto find(Long id);

    ${uniqueService}

    /**
     * 新增一条记录
     *
     * @param dto ${tableNameCamelUpperFirst}Dto
     * @return 成功？
     */
    boolean insert(${tableNameCamelUpperFirst}Dto dto);

    /**
     * 通过多个主键，修改多个排序
     *
     * @param list UpdateSortDto
     * @return 成功？
     */
    boolean updateSortList(List<UpdateSortDto> list);

    /**
     * 通过主键，修改一条记录
     *
     * @param dto ${tableNameCamelUpperFirst}Dto
     * @return 成功？
     */
    boolean update(${tableNameCamelUpperFirst}Dto dto);

    /**
     * 通过主键，删除一条记录
     *
     * @param id 主键
     * @return 成功？
     */
    boolean delete(Long id);

    /**
     * 通过“主表id”和“属性id”，获取属性值
     *
     * @param baseId 主表id
     * @param attributeId 属性id
     * @return attributeValue
     */
    String getValue(Long baseId, Integer attributeId);

}
