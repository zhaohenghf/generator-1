package ${packageName}.infrastructure.database;

import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
@Repository
public interface ${tableNameCamelUpperFirst}Database {
    /**
     * 查询列表
     *
     * @param criteria 查询条件
     * @return list
     */
    List<${tableNameCamelUpperFirst}> findList(${tableNameCamelUpperFirst}Criteria criteria);

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
     * @return ${tableNameCamelUpperFirst}
     */
    ${tableNameCamelUpperFirst} find(@Param("id") long id);

    ${uniqueDatabase}

    /**
     * 新增多条记录
     *
     * @param list ${tableNameCamelUpperFirst}
     * @return 影响行数
     */
    long insertList(@Param("list") List<${tableNameCamelUpperFirst}> list);

    /**
     * 新增一条记录
     *
     * @param record ${tableNameCamelUpperFirst}
     * @return 影响行数
     */
    int insert(${tableNameCamelUpperFirst} record);

    /**
     * 通过主键，修改多条记录
     *
     * @param list ${tableNameCamelUpperFirst}
     * @return 影响行数
     */
    long updateList(@Param("list") List<${tableNameCamelUpperFirst}> list);

    /**
     * 通过主键，修改一条记录
     *
     * @param record ${tableNameCamelUpperFirst}
     * @return 影响行数
     */
    int update(${tableNameCamelUpperFirst} record);

}
