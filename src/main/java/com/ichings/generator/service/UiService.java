package com.ichings.generator.service;

import org.springframework.ui.Model;

/**
 * 用户界面
 *
 * @author 宋欢
 */
public interface UiService {
    /**
     * code.html
     * <p>
     * ${packageName}
     * ${authorName}
     * ${templateDir}
     * ${targetDir}
     *
     * @param m Model
     */
    void renderCode(Model m);

    /**
     * dict.html
     * <p>
     * ${targetDir}
     * ${openDir}
     * ${suffix}
     * ${description}
     * ${version}
     *
     * @param m Model
     */
    void renderDict(Model m);

    /**
     * code.html
     * dict.html
     * <p>
     * ${dbUrl}
     * ${tableNames}
     *
     * @param m Model
     */
    void renderDb(Model m);

}
