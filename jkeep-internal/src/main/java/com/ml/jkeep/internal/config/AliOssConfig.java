package com.ml.jkeep.internal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云对象存储配置
 *
 * @author 谭良忠
 * @date 2019/9/24 19:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "jkeep.oss")
public class AliOssConfig {


    /**
     * 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
     */
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * 需要扮演的角色ID
     */
    private String roleArn;

    /**
     * 用来标识临时凭证的名称，建议使用不同的应用程序用户来区分
     */
    private String roleSessionName;

    /**
     * Endpoint以杭州为例 http://oss-cn-hangzhou.aliyuncs.com, 其它Region请按实际情况填写
     */
    private String endpoint;

    /**
     * 存储空间名称
     */
    private String bucket;

    /**
     * 文件上传的目录
     */
    private String catalog;

}
