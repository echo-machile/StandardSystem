package com.standard.Service.Imp;

import com.standard.Jpa.StandardJpa;
import com.standard.entity.Standard;
import com.standard.entity.SysRole;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private StandardJpa standardRepository;

    public void processDocument(MultipartFile file, String industry, String standardCategory) throws IOException {
        String documentName = file.getOriginalFilename();
        System.out.println("文件名"+documentName);
        String content;

        try (InputStream inputStream = file.getInputStream()) {
            if (documentName.endsWith(".pdf")) {
                content = readPdf(inputStream);
                System.out.println("内容"+content.strip().trim().getBytes(StandardCharsets.UTF_8));
            } else if (documentName.endsWith(".docx")) {
                content = readDocx(inputStream);
                System.out.println("内容"+content);
            } else {
                throw new IllegalArgumentException("不支持的文件类型");
            }
        }
        standardRepository.Save(content.strip(),documentName,industry,standardCategory);
    }

    private String readPdf(InputStream inputStream) throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setLineSeparator(System.lineSeparator());
            return pdfTextStripper.getText(document);
        }
    }

    private String readDocx(InputStream inputStream) throws IOException {
        try (XWPFDocument document = new XWPFDocument(inputStream)) {
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            return extractor.getText();
        }
    }

    public List<Standard> getStandards() {
        return standardRepository.getAllStandards();
    }
}

