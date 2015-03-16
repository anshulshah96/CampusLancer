package com.aka.campuslancer;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by anshul on 15/3/15.
 */
 @ParseClassName("HireData")
   public class HirePost extends ParseObject {
    public String getUsername() {
        return getString("username");
    }

    public void setUsername() {
        put("username", ParseUser.getCurrentUser().getUsername());
    }
    public String getTopic() {
        return getString("topic");
    }

    public void setTopic(String value) {
        put("topic", value);
    }
    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String value) {
        put("description", value);
    }

        public int getBid() {
          return getInt("initialbid");
        //return getString("initialbid");
    }

    public void setBid(int value) {
        put("initialbid", value);
    }

    public int getEnrol() {
        return getInt("enrollment");
    }

    public void setEnrol(int value) {
        put("enrollment", value);
    }


    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }


    public static ParseQuery<HirePost> getQuery() {
        return ParseQuery.getQuery(HirePost.class);
    }
}