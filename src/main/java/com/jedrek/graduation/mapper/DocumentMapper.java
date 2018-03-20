package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentMapper {

    int addDocument(Document document);

    int deleteDocument(Integer documentId);

    int modifyDocument(@Param("documentId") Integer documentId, @Param("newDocument") Document newDocument);

    Document queryDocument(Integer documentId);

    Document queryDocumentByTitle(String title);

    List<Document> queryDocumentByUser(Integer createdUserId);

    List<Document> queryDocumentByModifyUser(Integer modifyUserId);

    List<Document> queryAllDocument();

}
