package ${packageName}.infrastructure.cache;

import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Attribute;
import com.ichings.util.Cache;

import java.util.List;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
public interface ${tableNameCamelUpperFirst}Cache {
    /**
     * 通过主键，获取一条记录
     *
     * @param id 主键
     * @return ${tableNameCamelUpperFirst}
     */
    ${tableNameCamelUpperFirst} get(Long id);

    /**
     * 通过主键，保存一条记录
     *
     * @param id     主键
     * @param record ${tableNameCamelUpperFirst}
     */
    void set(Long id, ${tableNameCamelUpperFirst} record);

    /**
     * 通过主键，删除一条记录
     *
     * @param id 主键
     */
    void delete(Long id);

    /**
     * 通过主键，删除多条记录
     *
     * @param ids 主键列表
     */
    void deleteList(List<Long> ids);

    /**
     * 通过“主表id”，获取扩展属性
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
     */
    void setAttribute(Long baseId, ${tableNameCamelUpperFirst}Attribute record);

    /**
     * 通过“主表id”，删除扩展属性
     *
     * @param baseId 主表id
     */
    void deleteAttribute(Long baseId);

    /**
     * 获取Cache类
     *
     * @return Cache
     */
    Cache getCache();

    /**
     * 获取缓存时间，单位：秒
     *
     * @return 随机时间
     */
    int getTimeout();

}
