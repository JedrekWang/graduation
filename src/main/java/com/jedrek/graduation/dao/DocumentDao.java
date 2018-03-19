package com.jedrek.graduation.dao;

import com.jedrek.graduation.entity.Document;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocumentDao {

    int addDocuemnt(Document document);

    int deleteDocument(Integer documentId);

    int modifyDocument(Document oldDocument, Document newDocument);

    Document queryDocument(Integer documentId);

    Document queryDocumentByTitle(String title);

    List<Document> queryDocumentByUser(Integer createdUserId);

    List<Document> queryDocumentByModifyUser(Integer modifyUserId);

    List<Document> queryAllDocument();

}
