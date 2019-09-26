package com.ml.jkeep.internal.system;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ml.jkeep.common.vo.RestVo;
import com.ml.jkeep.internal.config.AliOssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Ali Oss - Controller
 *
 * @author 谭良忠
 * @date 2019/9/24 17:54
 */
@Slf4j
@RestController
@RequestMapping("oss")
public class AliOssController {

    private final AliOssConfig aliOssConfig;

    public AliOssController(AliOssConfig aliOssConfig) {
        this.aliOssConfig = aliOssConfig;
    }

    /**
     * Get STS临时授权访问OSS Token
     * <p>
     * https://help.aliyun.com/document_detail/32016.html?spm=a2c4g.11186623.6.804.658a1388lZ2Csy
     * </p>
     *
     * @return oss 访问权限
     */
    @GetMapping("/token")
    public RestVo getToken() {
        Map<String, String> map = new HashMap<>(6);
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", "sts.aliyuncs.com");
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", aliOssConfig.getAccessKeyId(), aliOssConfig.getAccessKeySecret());
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(aliOssConfig.getRoleArn());
            request.setRoleSessionName(aliOssConfig.getRoleSessionName());
            // 若policy为空，则用户将获得该角色下所有权限
            request.setPolicy(null);
            // 设置凭证有效时间
            request.setDurationSeconds(1000L);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            log.info("Expiration: " + response.getCredentials().getExpiration());
            log.info("Access Key Id: " + response.getCredentials().getAccessKeyId());
            log.info("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
            log.info("Security Token: " + response.getCredentials().getSecurityToken());
            log.info("RequestId: " + response.getRequestId());

            map.put("accessKeyId:", response.getCredentials().getAccessKeyId());
            map.put("accessKeySecret", response.getCredentials().getAccessKeySecret());
            map.put("securityToken", response.getCredentials().getSecurityToken());
            map.put("endpoint", aliOssConfig.getEndpoint());
            map.put("bucket", aliOssConfig.getBucket());
            map.put("catalog", aliOssConfig.getCatalog());
        } catch (ClientException e) {
            log.error("Failed：");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());
        }
        return RestVo.SUCCESS(map);
    }


}
