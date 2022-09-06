package com.ofBusiness.chatLog.repository;

import com.ofBusiness.chatLog.model.ChatLogRequest;
import com.ofBusiness.chatLog.model.ChatLogResponse;
import com.ofBusiness.chatLog.model.ChatLogs;
import com.ofBusiness.chatLog.utils.ChatLogExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ChatLogRepoImpl implements ChatLogRepo {

    @Autowired
    private NamedParameterJdbcTemplate nameJdbc;

    @Override
    public void insertChatRepo(ChatLogRequest request, String user, String messageId, Timestamp time) {
        String query = "INSERT INTO catalog " +
                "(user_id, message_id, message, timestamp, is_sent) " +
                "VALUES(:user, :message_id, :message, :timestamp, :is_sent);";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user",user);
        map.addValue("message_id",messageId);
        map.addValue("message",request.getMessage());
        map.addValue("timestamp",time);
        map.addValue("is_sent",request.getIsSent());

        nameJdbc.update(query,map);
    }

    @Override
    public List<ChatLogs> getChatLogs(String user, Integer limit, int id) {
        String topK = "";
        if (limit!=null){
            topK = "TOP " + limit + " ";
        }

        String idStartFind = "";
        if (id>0){
            idStartFind = "AND id <=" + id ;
        }

        String query = "SELECT " + topK  + " * FROM [catalog] c WHERE user_id = :user " + idStartFind + " ORDER by [timestamp] desc, id desc  ;";
        System.out.println(query);

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user",user);

        return nameJdbc.query(query, map, new ChatLogExtractor());
    }

    @Override
    public int getIdForStart(String user, String start) {
        String query = "SELECT id FROM [catalog] c WHERE user_id = :user and message_id = :start ;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user",user);
        map.addValue("start",start);


        return nameJdbc.query(query,map,rs -> {
            while (rs.next()){
                return rs.getInt("id");
            }
            return 0;
        });
    }

    @Override
    public void deleteLogsForUser(String user) {
        String query = "DELETE FROM [catalog] WHERE user_id = :user;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user",user);
        nameJdbc.update(query,map);
    }

    @Override
    public int deleteUserAndMessageId(String user, String messageId) {
        String query = "DELETE FROM [catalog] WHERE user_id = :user AND message_id  = :messageId;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user",user);
        map.addValue("messageId",messageId);

        return nameJdbc.update(query, map);
    }
}
