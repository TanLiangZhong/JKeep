package com.ml.jkeep.internal.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.controller.BaseController;
import com.ml.jkeep.common.vo.RestVo;
import com.ml.jkeep.jpa.system.entity.sys.SysLog;
import com.ml.jkeep.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:07
 */
@Controller
@RequestMapping("system/log")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService logService;

    @ResponseBody
    @GetMapping("find/{id}")
    public RestVo findById(@PathVariable Long id) {
        return RestVo.SUCCESS(logService.findById(id));
    }

    @ResponseBody
    @PostMapping("find/page")
    public RestVo page(@RequestBody PageBo<SysLog> pageBo) {
        return RestVo.SUCCESS(logService.findSimplePage(pageBo));
    }

    @GetMapping("page.html")
    public String html() {
        return "views/system/log";
    }

}
