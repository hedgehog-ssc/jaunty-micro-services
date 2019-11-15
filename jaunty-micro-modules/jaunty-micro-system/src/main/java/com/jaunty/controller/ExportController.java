package com.jaunty.controller;

import com.jaunty.service.ExportService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(description = "PDF导出接口")
@RestController
@RequestMapping("/system/export")
public class ExportController {

    @Autowired(required = false)
    private ExportService exportService;

    @RequestMapping(value = "pdf", method= RequestMethod.GET)
    @ResponseBody
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        exportService.exportPdf(request, response);
    }

    @RequestMapping(value = "excel", method= RequestMethod.GET)
    @ResponseBody
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        exportService.exportExcel(request, response);
    }

}
