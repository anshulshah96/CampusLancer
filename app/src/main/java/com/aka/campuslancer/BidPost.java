package com.aka.campuslancer;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by anshul on 23/3/15.
 */
@ParseClassName("Bids")
public class BidPost extends ParseObject {

    public int getBid() {
        return getInt("bid");
    }

    public void setBid(int value) {
        put("bid", value);
    }

    public int getMobileNo() {
        return getInt("mobile_no");
    }

    public void setMobileNo(int value) {
        put("mobile_no", value);
    }

    public ParseUser getUser() {
        return getParseUser("bidder_user");
    }

    public void setUser(ParseUser value) {
        put("bidder_user", value);
    }

    public String  getProjectId(){
        return getString("project_id");
    }
    public void setBidderUsername(String username) {
        put("bidder_username", username);
    }

    public String  getBidderUsername(){
        return getString("bidder_username");
    }

    public void setProjectId(String id){
        put("project_id",ParseObject.createWithoutData("HireData",id));
    }


    public static ParseQuery<BidPost> getQuery() {
        return ParseQuery.getQuery(BidPost.class);
    }
}