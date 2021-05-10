package com.ichings.generator.util;

import com.ichings.generator.autoconfigure.DocketProperties;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Api文档
 * http://127.0.0.1:8081/swagger-ui/
 *
 * <pre>
 * <dependency>
 *     <groupId>io.springfox</groupId>
 *     <artifactId>springfox-boot-starter</artifactId>
 *     <version>3.0.0</version>
 * </dependency>
 * </pre>
 *
 * @author 宋欢
 */
public final class Swagger3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger3.class);

    /**
     * 文档类型
     */
    private static final DocumentationType DOC_TYPE = DocumentationType.SWAGGER_2;

    /**
     * 启用？
     */
    private final Boolean enable;

    /**
     * 标题
     */
    private final String title;

    /**
     * 描述
     */
    private final String description;

    /**
     * 版本号
     */
    private final String version;

    /**
     * 许可证
     */
    private final String license;

    /**
     * 联系人，姓名
     */
    private final String name;

    /**
     * 联系人，链接
     */
    private final String url;

    /**
     * 联系人，邮箱
     */
    private final String email;

    public Swagger3(DocketProperties properties) {
        AssertUtils.nonNull(properties, "properties");

        DocketProperties.Contact contact = properties.getContact();

        this.enable = properties.getEnable();
        this.title = StringUtils.encoding(properties.getTitle(), null);
        this.description = StringUtils.encoding(properties.getDescription(), null);
        this.version = properties.getVersion();
        this.license = properties.getLicense();
        this.name = StringUtils.encoding(contact.getName(), null);
        this.url = contact.getUrl();
        this.email = contact.getEmail();

        LOGGER.info("enable: {}, title: {}, description: {}, version: {}, license: {}, name: {}, url: {}, email: {}",
                this.enable, this.title, this.description, this.version, this.license, this.name, this.url, this.email);
    }

    public Docket getDocket() {
        List<RequestParameter> parameters = new ArrayList<>();

        return new Docket(DOC_TYPE).
                enable(enable).
                select().
                apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).
                paths(PathSelectors.any()).
                build().
                apiInfo(getInfo()).
                globalRequestParameters(parameters);
    }

    public ApiInfo getInfo() {
        return new ApiInfoBuilder().
                title(title).
                description(description).
                version(version).
                contact(new Contact(name, url, email)).
                license(license).
                build();
    }

    public Boolean getEnable() {
        return enable;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getLicense() {
        return license;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

}
