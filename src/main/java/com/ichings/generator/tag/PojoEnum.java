package com.ichings.generator.tag;

import com.ichings.generator.util.Check;

/**
 * 枚举
 *
 * @author 宋欢
 */
public enum PojoEnum {
    /**
     * 类型 => 描述
     */
    PO(10, "Persistent Object"),
    PO_CRITERIA(11, "Persistent Object Criteria"),
    DTO(20, "Data Transfer Object"),
    VO(30, "View Object"),
    VO_CREATE(31, "View Object Create Request"),
    VO_UPDATE(32, "View Object Update Request"),
    VO_CRITERIA(33, "View Object Criteria Request"),
    ;

    /**
     * 类型
     */
    private int type;

    /**
     * 描述
     */
    private String message;

    PojoEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Persistent Object ?
     */
    public static boolean isPo(PojoEnum value) {
        if (Check.nonNull(value)) {
            return PO.getType() == value.getType();
        } else {
            return false;
        }
    }

    /**
     * Persistent Object Criteria ?
     */
    public static boolean isPoCriteria(PojoEnum value) {
        if (Check.nonNull(value)) {
            return PO_CRITERIA.getType() == value.getType();
        } else {
            return false;
        }
    }

    /**
     * Data Transfer Object ?
     */
    public static boolean isDto(PojoEnum value) {
        if (Check.nonNull(value)) {
            return DTO.getType() == value.getType();
        } else {
            return false;
        }
    }

    /**
     * View Object ?
     */
    public static boolean isVo(PojoEnum value) {
        if (Check.nonNull(value)) {
            return VO.getType() == value.getType();
        } else {
            return false;
        }
    }

    /**
     * View Object Create Request ?
     */
    public static boolean isVoCreate(PojoEnum value) {
        if (Check.nonNull(value)) {
            return VO_CREATE.getType() == value.getType();
        } else {
            return false;
        }
    }

    /**
     * View Object Update Request ?
     */
    public static boolean isVoUpdate(PojoEnum value) {
        if (Check.nonNull(value)) {
            return VO_UPDATE.getType() == value.getType();
        } else {
            return false;
        }
    }

    /**
     * View Object Criteria Request ?
     */
    public static boolean isVoCriteria(PojoEnum value) {
        if (Check.nonNull(value)) {
            return VO_CRITERIA.getType() == value.getType();
        } else {
            return false;
        }
    }

}
