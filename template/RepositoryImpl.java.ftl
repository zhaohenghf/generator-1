package ${packageName}.biz.repository.impl;

import com.ichings.base.Check;
import com.ichings.base.Errors;
import com.ichings.common.attribute.AttributeDatabase;
import com.ichings.common.attribute.AttributeMapper;
import com.ichings.common.biz.BizException;
import ${packageName}.biz.repository.${tableNameCamelUpperFirst}Repository;
import ${packageName}.infrastructure.cache.${tableNameCamelUpperFirst}Cache;
import ${packageName}.infrastructure.database.${tableNameCamelUpperFirst}Database;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Attribute;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}AttributeMapper;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Mapper;
import ${packageName}.infrastructure.util.TableName;
import com.ichings.util.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author ${authorName}
 */
@Repository
public class ${tableNameCamelUpperFirst}RepositoryImpl implements ${tableNameCamelUpperFirst}Repository {

    private static final Logger LOGGER = LoggerFactory.getLogger(${tableNameCamelUpperFirst}RepositoryImpl.class);

    @Autowired
    private ${tableNameCamelUpperFirst}Database database;

    @Autowired
    private ${tableNameCamelUpperFirst}Cache cache;

    @Autowired
    private ${tableNameCamelUpperFirst}Mapper beanMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public List<${tableNameCamelUpperFirst}> findList(${tableNameCamelUpperFirst}Criteria criteria) {
        try {
            AssertUtils.nonNull(criteria, "criteria");

            return database.findList(criteria);
        } catch (Throwable tr) {
            LOGGER.error("findList failed, criteria: {}, throwable: ", criteria, tr);
            throw new BizException(Errors.FIND_ERR);
        }
    }

    @Override
    public long getTotalNum(${tableNameCamelUpperFirst}Criteria criteria) {
        try {
            AssertUtils.nonNull(criteria, "criteria");

            return database.getTotalNum(criteria);
        } catch (Throwable tr) {
            LOGGER.error("getTotalNum failed, criteria: {}, throwable: ", criteria, tr);
            throw new BizException(Errors.FIND_ERR);
        }
    }

    @Override
    public ${tableNameCamelUpperFirst} find(Long id) {
        try {
            AssertUtils.isPositive(id, "id");

            ${tableNameCamelUpperFirst} record = cache.get(id);
            if (Check.nonNull(record)) {
                return record;
            }

            record = database.find(id);
            if (Check.nonNull(record)) {
                cache.set(id, record);
            }

            return record;
        } catch (Throwable tr) {
            LOGGER.error("find failed, id: {}, throwable: ", id, tr);
            throw new BizException(Errors.FIND_ERR);
        }
    }

    ${uniqueRepositoryImpl}

    @Override
    public boolean insertList(List<${tableNameCamelUpperFirst}> rawList) {
        try {
            AssertUtils.nonEmpty(rawList, "rawList");

            List<${tableNameCamelUpperFirst}> list = new ArrayList<>();
            for (${tableNameCamelUpperFirst} rawRecord : rawList) {
                if (Check.isNull(rawRecord)) {
                    continue;
                }

                ${tableNameCamelUpperFirst} record = checkAndInitializeInsert(rawRecord);
                list.add(record);
            }

            if (Check.isEmpty(list)) {
                return false;
            }

            long affectedRows = database.insertList(list);
            if (affectedRows <= 0) {
                LOGGER.error("insertList failed, affected's rows is 0");
                return false;
            }

            return true;
        } catch (Throwable tr) {
            LOGGER.error("insertList failed, throwable: ", tr);
            throw new BizException(Errors.INSERT_ERR);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean insert(${tableNameCamelUpperFirst} rawRecord, ${tableNameCamelUpperFirst}Attribute attribute) {
        try {
            ${tableNameCamelUpperFirst} record = checkAndInitializeInsert(rawRecord);
            AssertUtils.nonNull(record, "record");

            Long id = record.getId();
            AssertUtils.isPositive(id, "record.id");

            int affectedRows = database.insert(record);
            if (affectedRows <= 0) {
                LOGGER.error("insert failed, affected's rows is 0, rawRecord: {}, attribute: {}", rawRecord, attribute);
                return false;
            }

            if (Check.isNull(attribute) || setAttribute(id, attribute)) {
                return true;
            }

            LOGGER.error("insert failed, set attribute failed, rawRecord: {}, attribute: {}", rawRecord, attribute);
            throw new BizException(Errors.ATTRIBUTE_INSERT_ERR);
        } catch (Throwable tr) {
            LOGGER.error("insert failed, rawRecord: {}, attribute: {}, throwable: ", rawRecord, attribute, tr);
            throw new BizException(Errors.INSERT_ERR);
        }
    }

    @Override
    public boolean updateList(List<${tableNameCamelUpperFirst}> rawList) {
        try {
            AssertUtils.nonEmpty(rawList, "rawList");

            List<${tableNameCamelUpperFirst}> list = new ArrayList<>();
            List<Long> idList = new ArrayList<>();
            for (${tableNameCamelUpperFirst} record : rawList) {
                if (Check.isNull(record)) {
                    continue;
                }

                Long id = record.getId();
                AssertUtils.isPositive(id, "record.id");

                list.add(record);
                idList.add(id);
            }

            if (Check.isEmpty(list)) {
                return false;
            }

            long affectedRows = database.updateList(list);
            if (affectedRows <= 0) {
                LOGGER.error("updateList failed, affected's rows is 0, idList: {}", idList);
                return false;
            }

            cache.deleteList(idList);
            return true;
        } catch (Throwable tr) {
            LOGGER.error("updateList failed, throwable: ", tr);
            throw new BizException(Errors.UPDATE_ERR);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean update(${tableNameCamelUpperFirst} record, ${tableNameCamelUpperFirst}Attribute attribute) {
        try {
            AssertUtils.nonNull(record, "record");

            Long id = record.getId();
            AssertUtils.isPositive(id, "record.id");

            int affectedRows = database.update(record);
            if (affectedRows <= 0) {
                LOGGER.error("update failed, affected's rows is 0, record: {}, attribute: {}", record, attribute);
                return false;
            }

            cache.delete(id);

            if (Check.isNull(attribute) || setAttribute(id, attribute)) {
                return true;
            }

            LOGGER.error("update failed, set attribute failed, record: {}, attribute: {}", record, attribute);
            throw new BizException(Errors.ATTRIBUTE_UPDATE_ERR);
        } catch (Throwable tr) {
            LOGGER.error("update failed, record: {}, attribute: {}, throwable: ", record, attribute, tr);
            throw new BizException(Errors.UPDATE_ERR);
        }
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        try {
            AssertUtils.nonEmpty(ids, "ids");

            List<${tableNameCamelUpperFirst}> list = new ArrayList<>();
            List<Long> idList = new ArrayList<>();
            for (Long id : ids) {
                AssertUtils.isPositive(id, "id");

                ${tableNameCamelUpperFirst} record = new ${tableNameCamelUpperFirst}();
                record.setId(id);
                record.setDeletedId(id);
                record.setDeletedAt(new Date());

                list.add(record);
                idList.add(id);
            }

            long affectedRows = database.updateList(list);
            if (affectedRows <= 0) {
                LOGGER.error("deleteList failed, affected's rows is 0, ids: {}", ids);
                return false;
            }

            cache.deleteList(idList);
            return true;
        } catch (Throwable tr) {
            LOGGER.error("deleteList failed, ids: {}, throwable: ", ids, tr);
            throw new BizException(Errors.DELETE_ERR);
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            AssertUtils.isPositive(id, "id");

            ${tableNameCamelUpperFirst} record = new ${tableNameCamelUpperFirst}();
            record.setId(id);
            record.setDeletedId(id);
            record.setDeletedAt(new Date());

            int affectedRows = database.update(record);
            if (affectedRows <= 0) {
                LOGGER.error("delete failed, affected's rows is 0, id: {}", id);
                return false;
            }

            cache.delete(id);
            return true;
        } catch (Throwable tr) {
            LOGGER.error("delete failed, id: {}, throwable: ", id, tr);
            throw new BizException(Errors.DELETE_ERR);
        }
    }

    @Override
    public ${tableNameCamelUpperFirst}Attribute getAttribute(Long baseId) {
        try {
            AssertUtils.isPositive(baseId, "baseId");

            ${tableNameCamelUpperFirst}Attribute record = cache.getAttribute(baseId);
            if (Check.nonNull(record)) {
                return record;
            }

            Map<Integer, String> attributeMap = AttributeDatabase.getPairs(attributeMapper, TableName.${tableNameUnderscoreUpper}_ATTRIBUTE, baseId);
            record = ${tableNameCamelUpperFirst}AttributeMapper.fromMap(attributeMap);
            if (Check.nonNull(record)) {
                cache.setAttribute(baseId, record);
            }

            return record;
        } catch (Throwable tr) {
            LOGGER.error("getAttribute failed, baseId: {}, throwable: ", baseId, tr);
            throw new BizException(Errors.ATTRIBUTE_FIND_ERR);
        }
    }

    @Override
    public boolean setAttribute(Long baseId, ${tableNameCamelUpperFirst}Attribute record) {
        try {
            Map<Integer, String> attributeMap = ${tableNameCamelUpperFirst}AttributeMapper.toMap(record);
            if (Check.isEmpty(attributeMap)) {
                return true;
            }

            int affectedRows = AttributeDatabase.batchSave(attributeMapper, TableName.${tableNameUnderscoreUpper}_ATTRIBUTE, baseId, attributeMap);
            if (affectedRows <= 0) {
                // 不必修改，新老数据相同
                LOGGER.warn("setAttribute failed, affected's rows is 0, baseId: {}, record: {}", baseId, record);
                return true;
            }

            cache.deleteAttribute(baseId);
            return true;
        } catch (Throwable tr) {
            LOGGER.error("setAttribute failed, baseId: {}, record: {}, throwable: ", baseId, record, tr);
            throw new BizException(Errors.ATTRIBUTE_UPDATE_ERR);
        }
    }

    @Override
    public String getValue(Long baseId, Integer attributeId) {
        return AttributeDatabase.getValue(attributeMapper, TableName.${tableNameUnderscoreUpper}_ATTRIBUTE, baseId, attributeId);
    }

    private ${tableNameCamelUpperFirst} checkAndInitializeInsert(${tableNameCamelUpperFirst} record) {
        ${tableNameCamelUpperFirst} r = beanMapper.insert(record);

        AssertUtils.nonNull(r, "record");
        ${repositoryCheckAndInitializeInsert}

        return r;
    }

}
