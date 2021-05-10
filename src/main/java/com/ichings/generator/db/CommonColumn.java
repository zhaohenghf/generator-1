package com.ichings.generator.db;

/**
 * 共用的列名
 *
 * @author 宋欢
 */
public enum CommonColumn {
    /**
     * 列名 => 备注
     */
    ID("id", "主键"),
    SORT("sort", "排序"),
    REMARK("remark", "备注，公开"),
    NOTE("note", "内部备注，不公开"),
    DELETED_ID("deleted_id", "删除？id-是、0-否"),
    CREATED_AT("created_at", "创建时间"),
    UPDATED_AT("updated_at", "更新时间"),
    DELETED_AT("deleted_at", "删除时间"),
    ;

    /**
     * 列名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    CommonColumn(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public static boolean isId(String name) {
        return ID.getName().equals(name);
    }

    public static boolean isSort(String name) {
        return SORT.getName().equals(name);
    }

    public static boolean isRemark(String name) {
        return REMARK.getName().equals(name);
    }

    public static boolean isNote(String name) {
        return NOTE.getName().equals(name);
    }

    public static boolean isDeletedId(String name) {
        return DELETED_ID.getName().equals(name);
    }

    public static boolean isCreatedAt(String name) {
        return CREATED_AT.getName().equals(name);
    }

    public static boolean isUpdatedAt(String name) {
        return UPDATED_AT.getName().equals(name);
    }

    public static boolean isDeletedAt(String name) {
        return DELETED_AT.getName().equals(name);
    }

}
