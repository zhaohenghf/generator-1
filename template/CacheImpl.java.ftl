package ${packageName}.infrastructure.cache.impl;

import ${packageName}.infrastructure.cache.${tableNameCamelUpperFirst}Cache;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Attribute;
import ${packageName}.infrastructure.util.TableName;
import com.ichings.util.Cache;
import com.ichings.util.RandUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ${authorName}
 */
@Repository
public class ${tableNameCamelUpperFirst}CacheImpl implements ${tableNameCamelUpperFirst}Cache {
    /**
     * 最小缓存时间，单位：秒
     */
    private static final int TIMEOUT_MIN = 24 * 3600;

    /**
     * 最大缓存时间，单位：秒
     */
    private static final int TIMEOUT_MAX = TIMEOUT_MIN + 3600;

    @Autowired
    private Cache cache;

    @Override
    public ${tableNameCamelUpperFirst} get(Long id) {
        return cache.get(TableName.${tableNameUnderscoreUpper}, id, ${tableNameCamelUpperFirst}.class, false);
    }

    @Override
    public void set(Long id, ${tableNameCamelUpperFirst} record) {
        cache.set(TableName.${tableNameUnderscoreUpper}, id, record, getTimeout(), false);
    }

    @Override
    public void delete(Long id) {
        cache.delete(TableName.${tableNameUnderscoreUpper}, id, false);
    }

    @Override
    public void deleteList(List<Long> ids) {
        cache.delete(TableName.${tableNameUnderscoreUpper}, ids, false);
    }

    @Override
    public ${tableNameCamelUpperFirst}Attribute getAttribute(Long baseId) {
        return cache.get(TableName.${tableNameUnderscoreUpper}_ATTRIBUTE, baseId, ${tableNameCamelUpperFirst}Attribute.class, false);
    }

    @Override
    public void setAttribute(Long baseId, ${tableNameCamelUpperFirst}Attribute record) {
        cache.set(TableName.${tableNameUnderscoreUpper}_ATTRIBUTE, baseId, record, getTimeout(), false);
    }

    @Override
    public void deleteAttribute(Long baseId) {
        cache.delete(TableName.${tableNameUnderscoreUpper}_ATTRIBUTE, baseId, false);
    }

    @Override
    public Cache getCache() {
        return cache;
    }

    @Override
    public int getTimeout() {
        return RandUtils.betweenInt(TIMEOUT_MIN, TIMEOUT_MAX);
    }

}
