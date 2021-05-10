package com.ichings.generator.tag;

import com.ichings.generator.db.CommonColumn;
import com.ichings.generator.util.Check;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略字段
 *
 * @author 宋欢
 */
public final class PojoIgnore {
    /**
     * Persistent Object Criteria
     */
    public static final List<String> PO_CRITERIA = new ArrayList<>();

    /**
     * Data Transfer Object
     */
    public static final List<String> DTO = new ArrayList<>();

    /**
     * View Object
     */
    public static final List<String> VO = new ArrayList<>();

    /**
     * View Object Create Request
     */
    public static final List<String> VO_CREATE = new ArrayList<>();

    /**
     * View Object Update Request
     */
    public static final List<String> VO_UPDATE = new ArrayList<>();

    /**
     * View Object Criteria Request
     */
    public static final List<String> VO_CRITERIA = new ArrayList<>();

    static {
        PO_CRITERIA.add(CommonColumn.ID.getName());
        PO_CRITERIA.add(CommonColumn.REMARK.getName());
        PO_CRITERIA.add(CommonColumn.NOTE.getName());
        PO_CRITERIA.add(CommonColumn.CREATED_AT.getName());
        PO_CRITERIA.add(CommonColumn.UPDATED_AT.getName());
        PO_CRITERIA.add(CommonColumn.DELETED_ID.getName());
        PO_CRITERIA.add(CommonColumn.DELETED_AT.getName());

        DTO.add(CommonColumn.DELETED_ID.getName());
        DTO.add(CommonColumn.DELETED_AT.getName());

        VO.add(CommonColumn.NOTE.getName());
        VO.add(CommonColumn.DELETED_ID.getName());
        VO.add(CommonColumn.DELETED_AT.getName());

        VO_CREATE.add(CommonColumn.ID.getName());
        VO_CREATE.add(CommonColumn.NOTE.getName());
        VO_CREATE.add(CommonColumn.CREATED_AT.getName());
        VO_CREATE.add(CommonColumn.UPDATED_AT.getName());
        VO_CREATE.add(CommonColumn.DELETED_ID.getName());
        VO_CREATE.add(CommonColumn.DELETED_AT.getName());

        VO_UPDATE.add(CommonColumn.NOTE.getName());
        VO_UPDATE.add(CommonColumn.CREATED_AT.getName());
        VO_UPDATE.add(CommonColumn.UPDATED_AT.getName());
        VO_UPDATE.add(CommonColumn.DELETED_ID.getName());
        VO_UPDATE.add(CommonColumn.DELETED_AT.getName());

        VO_CRITERIA.add(CommonColumn.ID.getName());
        VO_CRITERIA.add(CommonColumn.REMARK.getName());
        VO_CRITERIA.add(CommonColumn.NOTE.getName());
        VO_CRITERIA.add(CommonColumn.CREATED_AT.getName());
        VO_CRITERIA.add(CommonColumn.UPDATED_AT.getName());
        VO_CRITERIA.add(CommonColumn.DELETED_ID.getName());
        VO_CRITERIA.add(CommonColumn.DELETED_AT.getName());
    }

    /**
     * 忽略字段？
     */
    public static boolean isIgnore(PojoEnum type, String columnName) {
        if (Check.isNull(type)) {
            return false;
        }

        if (Check.isEmpty(columnName)) {
            return false;
        }

        if (PojoEnum.isPoCriteria(type)) {
            return PO_CRITERIA.contains(columnName);
        }

        if (PojoEnum.isDto(type)) {
            return DTO.contains(columnName);
        }

        if (PojoEnum.isVo(type)) {
            return VO.contains(columnName);
        }

        if (PojoEnum.isVoCreate(type)) {
            return VO_CREATE.contains(columnName);
        }

        if (PojoEnum.isVoUpdate(type)) {
            return VO_UPDATE.contains(columnName);
        }

        if (PojoEnum.isVoCriteria(type)) {
            return VO_CRITERIA.contains(columnName);
        }

        return false;
    }

}
