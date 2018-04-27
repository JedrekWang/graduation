package com.jedrek.graduation.web;


import com.jedrek.graduation.entity.Message;
import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.TopicMember;
import com.jedrek.graduation.service.MessageService;
import com.jedrek.graduation.service.TopicMemberService;
import com.jedrek.graduation.service.TopicService;
import com.jedrek.graduation.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class TopicController {

    private TopicService topicService;
    private MessageService messageService;
    private TopicMemberService topicMemberService;

    @Autowired
    public TopicController(TopicService topicService, MessageService messageService, TopicMemberService topicMemberService) {
        this.topicService = topicService;
        this.messageService = messageService;
        this.topicMemberService = topicMemberService;
    }

    @RequestMapping("topic")
    public String showTopicPage() {
        return "topic";
    }

    @ResponseBody
    @RequestMapping("topics/{account}")
    public Object getAllTopic(@PathVariable String account) {
//        List<Topic> topics = topicService.queryTopicByAccount(account);
//        return topics;
        List<TopicMember> topicMembers = topicMemberService.queryAllTopicByAccount(account);
        List<Topic> topicList = new ArrayList<>();
        for (TopicMember topicMember : topicMembers) {
            Integer topicId = topicMember.getTopicId();
            Topic topic = topicService.queryTopicById(topicId);
            topicList.add(topic);
        }
        return topicList;
    }


    @ResponseBody
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
        // 讨论的第一条信息
        Message message = new Message();
        message.setSendAccount(currentUser);
        message.setContent(currentUser+"发起了话题讨论");
        message.setTopicId(insertTopicId);

        messageService.addMessage(message);
        return "success";

    }

    @ResponseBody
    @RequestMapping("topicDetail/{topicId}")
    public Object showTopicDetail(@PathVariable Integer topicId) {
        //获取该话题的讨论内容
        List<Message> messages = topicService.getTopicAllContent(topicId);
        return messages;
    }

    @ResponseBody
    @RequestMapping("justTopic/{topicId}")
    public Object getTopic(@PathVariable Integer topicId) {
        Topic topic = topicService.queryTopicById(topicId);
        return topic;
    }

    @ResponseBody
    @RequestMapping(value = "message", method = RequestMethod.POST)
    public String submitTopicMessage(@RequestBody Map map) {
        System.out.println("hello");
        Message message = new Message();
        message.setContent((String)map.get("message"));
        message.setSendAccount((String)map.get("sendAccount"));
        message.setTopicId((Integer)map.get("topicId"));
        message.setMode(0);
        messageService.addMessage(message);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "message/uploadPath", method = RequestMethod.POST)
    public Object getUploadPathMessages(@RequestBody Map map) {
        System.out.println("hello");
        List<Map<String,Object>> paths = new ArrayList<>();
        List messages = (ArrayList)map.get("messages");
        for (Object m : messages) {
            Map message = (Map)m;
            System.out.println(message.get("mode"));
            if (Objects.equals(message.get("mode"), 1)) {
                String uploadFilePath = messageService.getMessageUploadFilePath((Integer) message.get("messageId"));
                Map<String,Object> temp = new HashMap<>();
                temp.put("uploadPath", uploadFilePath);
                temp.put("messageId", message.get("messageId"));
                paths.add(temp);

            }
        }
        return paths;
    }
}
