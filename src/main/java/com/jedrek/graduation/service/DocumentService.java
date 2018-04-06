package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Document;
import com.jedrek.graduation.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentService {

    private DocumentMapper documentMapper;

    @Autowired
    public DocumentService(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    public int addDocument(Document document) {
        return documentMapper.addDocument(document);
    }

    public int deleteDocument(Integer documentId) {
        return documentMapper.deleteDocument(documentId);
    }

    public int modifyDocument(Integer documentId, Document newDocument) {
        return documentMapper.modifyDocument(documentId, newDocument);
    }

    public Document queryDocument(Integer documentId) {
        return documentMapper.queryDocument(documentId);
    }

    public Document queryDocumentByTitle(String title) {
        return documentMapper.queryDocumentByTitle(title);
    }

    public List<Document> queryDocumentByUser(Integer createdUserId) {
        return documentMapper.queryDocumentByUser(createdUserId);
    }

    public List<Document> queryDocumentByUserName(String userName) {
        return documentMapper.queryDocumentByUserName(userName);
    }

    public List<Document> queryDocumentByModifyUser(Integer modifyUserId) {
        return documentMapper.queryDocumentByModifyUser(modifyUserId);
    }

    public List<Document> queryAllDocument() {
        return documentMapper.queryAllDocument();
    }
}
