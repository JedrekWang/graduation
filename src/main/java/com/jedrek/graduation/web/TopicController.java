package com.jedrek.graduation.web;


import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.TopicMember;
import com.jedrek.graduation.service.TopicMemberService;
import com.jedrek.graduation.service.TopicService;
import com.jedrek.graduation.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class TopicController {

    private TopicService topicService;
    private TopicMemberService topicMemberService;

    @Autowired
    public TopicController(TopicService topicService, TopicMemberService topicMemberService) {
        this.topicService = topicService;
        this.topicMemberService = topicMemberService;
    }



    @RequestMapping(value = "topic",method = RequestMethod.POST)
    public String addTopic(
            HttpServletRequest request,
            @RequestBody Map map) {
        List<String> selectUsers = (List)map.get("selectMembers");
        String fileId = (String)map.get("fileId");
        String currentUser = CookieUtil.getCookieValue(request, "currentUser");
        Topic topic = new Topic();
        topic.setTopicStartAccount(currentUser);
        topic.setFileInfoId(Integer.parseInt(fileId));
        // 新增一个话题讨论
        int insertTopicId = topicService.addTopic(topic);
        //将点击的用户加入该话题
        for(String selectUser: selectUsers) {
            TopicMember topicMember = new TopicMember();
            topicMember.setAccount(selectUser);
            topicMember.setTopicId(insertTopicId);
            topicMemberService.addTopicMember(topicMember);
        }

        return "success";

    }
}
