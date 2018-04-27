package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.Version;
import com.jedrek.graduation.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VersionController {
    private VersionService versionService;

    @Autowired
    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @ResponseBody
    @RequestMapping("{documentId}/version")
    public Object getAllVersion(@PathVariable Integer documentId) {
        List<Version> versions = versionService.queryVersionsByRawFileId(documentId);//??????
        return versions;
    }


}
