package com.standard.Controller;

import com.standard.Jpa.StandardJpa;
import com.standard.Service.Imp.DocumentService;
import com.standard.entity.RequestParameters;
import com.standard.entity.Standard;
import com.standard.entity.checkResult;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

@Controller
@RestController
public class FileUploadController {
    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private DocumentService documentService;

    @Autowired
    private StandardJpa standardJpa;

    @PostMapping("/upload")
    public checkResult uploadFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("industry") String industry,
                                  @RequestParam("category") String standardCategory) {
        checkResult result = new checkResult();

        if (file.isEmpty()) {
            result.setSuccess(false);
            result.setErrCode(1);
            result.setMsg("请选择一个文档文件上传");
            return result;
        }

        try {
            // 处理文档并保存内容到数据库
            documentService.processDocument(file, industry, standardCategory);
            result.setSuccess(true);
            result.setErrCode(0);
            result.setMsg("文件上传成功");

        } catch (IOException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setErrCode(2);
            result.setMsg("文件上传失败");
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setErrCode(3);
            result.setMsg(e.getMessage());
        }

        return result;
    }

    @GetMapping("/showTable")
    public List<Standard> getStandards() {
        return documentService.getStandards();
    }

    @PostMapping("/getContent")
    public ResponseEntity<Set<Standard>> getContent(
            @RequestBody(required = false) RequestParameters params) {
        System.out.println("id: "+params.getId()+"\n"+"documentName: "+params.getDocumentName()+"\n"+"industry: "+params.getIndustry()+"\n");
        int flag = 0;
        Set<Standard> sta = new HashSet<Standard>();
        if(params.getId() != null && !params.getId().equals("")){
            flag = 1;
            sta.addAll(standardJpa.findAllById(Collections.singleton(Long.valueOf(params.getId()))));
        }
        if(params.getContent() != null){
            flag = 1;
            sta.addAll(standardJpa.getStandardsByContent(params.getContent()));
        }
        if(params.getStandardCategory() != null){
            flag = 1;
            sta.addAll(standardJpa.getStandardsByStandardCategory(params.getStandardCategory()));
        }
        if(params.getIndustry() != null){
            flag = 1;
            sta.addAll(standardJpa.getStandardsByIndustry(params.getIndustry()));
        }
        if(params.getDocumentName() != null){
            flag = 1;
            sta.addAll(standardJpa.getStandardsByDocumentName(params.getDocumentName()));
        }

        if (sta.isEmpty()&&flag == 0){
            sta.addAll(documentService.getStandards());
        }
        return ResponseEntity.ok(sta);
    }
}

