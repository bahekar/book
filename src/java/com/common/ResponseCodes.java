/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

/**
 *
 * @author chhavikumar.b
 */
public class ResponseCodes {

    public enum ServiceErrorCodes {

        SUCCESS(0, "SUCCESS"),
        GENERIC_ERROR(-1, "Generic Error occured"),
        USERID_ALREADY_EXISTS(100, "UserId Already Exists"),
        INVALID_USER_DETAILS(101, "Generic Error occured"),
        INVALID_DATA(102, "Invalid data."),
        INVALID_TOKEN(103, "Invalid token."),
        INVALID_USERID(104, "Name must not be Empty"),
        INVALID_PASSWORD(105, "Password must not be Empty"),
        INVALID_JSON(106, "Invalid JSON"),
        UMP_INVALID_STATE(107, "Invalid State"),
        USERID_NOT_EXISTS(108, "User not exists"),
        WRONG_PASSWORD(109, "Invalid password"),
        INVALID_LOGIN_TYPEID(110, "Invalid login typeid"),
        INVALID_SMTP_CONNECTION(111, "Invalid smtp connection"),
        INVALID_EMAILID(112, "Invalid emailid"),
        INVALID_PASSWORD_TYPE(113, "Invalid password type"),
        USER_INACTIVE(114, "User is inactive"),
        INVALID_FORGOT_PASSWORD_TOKEN(115, "Invalid forgot password token"),
        PASSWORD_UPDATE_FAILED(116, "Password update failed"),
        OLDPASSWORD_IS_WRONG(117, "Invalid old password"),
        INVALID_PROPERTY_CATEGORY(118, "Invalid property category"),
        INVALID_AGENT_TYPE(119, "Invalid agent type"),
        PROPERTY_ACTIVATION_FAILED(120, "Missing Mandatory parameters to activate property"),
        PRICE_IS_MANDATORY_TO_ACTIVATE_PROPERTY(121, "Price is mandatory to activate property"),
        ADDRESS_IS_MANDATORY_TO_ACTIVATE_PROPERTY(122, "Address is mandatory to activate property"),
        PROPERTY_HOLDER_NAME_IS_MANDATORY_TO_ACTIVATE_PROPERTY(123, "Name is mandatory to activate property"),
        EMAIL_IS_MANDATORY_TO_ACTIVATE_PROPERTY(124, "Email is mandatory to activate property"),
        MOBILE_IS_MANDATORY_TO_ACTIVATE_PROPERTY(125, "Mobile is mandatory to activate property"),
        CONTACT_DEATILS_MANDATORY_TO_ACTIVATE_PROPERTY(126, "Contact details is mandatory to activate property"),
        INVALID_MOBILE_NUMBER(127, "Invalid mobile number"),
        INVALID_Q(128, "Invalid Question"),
        INSERT_FAILED(129, "Insert failed"),
        INVALID_PROPERTY_ID(130, "Invalid propertyId"),
        USER_PROPERTY_SAVE_FAILED(131, "Faild to save user property"),
        NAME_IS_REQUIRED(132, "Name Parameter is required"),
        SEARCH_QUERY_IS_REQUIRED(133, "Search query is required"),
        FAILED_TO_SAVE_SEARCH(134, "Failed to save property search"),
        INVALID_AGENT_ID(135, "Invalid agent id"),
        INVALID_COMMENTS(136, "Invalid comments"),
        AGENT_PROPERTY_ALREADY_EXISTS(137, "Agent Property already exists"),
        LATITUDE_AND_LONGITUDE_IS_MANDATORY(138, "Latitude and Longitude is mandatory"),
        INVALID_RADIUS(139, "Invalid radius value"),
        MORTGAGE_INFO_UPDATE_FAILED(140, "Invalid radius value"),
        FAV_FAILED(141, "restaruant already added in favourites"),
        INFAV_FAILED(148, "restaruant is not in favourites"),
        INVALID_CREDITS(142, "Invalid Credits"),
        INVALID_DEAL_CODE(143, "Invalid Deal Code"),
        SUFFICIENT_CREDITS(144, "User not have sufficient credits."),
        INVALID_PRICE(145, "Invalid Price"),
        INVALID_COUPON(146, "Invalid Coupon"),
        Confirm_PASSWORD(148, "Confirm must not be Empty"),
         Password_Confirm_PASSWORD(150, "Password and Confirm not Matching"),
        INVALID_RESTAURANT(147, "Invalid Restaurant");
        
        private final int CODE;
        private final String DESC;

        ServiceErrorCodes(int aStatus, String desc) {
            this.CODE = aStatus;
            this.DESC = desc;
        }

        public int CODE() {
            return this.CODE;
        }

        public String getCode() {
            return this.CODE + "";
        }

        public String DESC() {
            return this.DESC;
        }

        public String DESC(String param1) {
            if (null != param1 && param1.length() > 0) {
                return this.DESC + " : " + param1;
            }
            return this.DESC;
        }

        public static String getMessage(ServiceErrorCodes errorCode) {
            return errorCode.CODE() + " " + errorCode.DESC();
        }

        public static String getIdBasedmessage(int code) {
            String message = "";
            for (ServiceErrorCodes errCode : ServiceErrorCodes.values()) {
                if (errCode.CODE == code) {
                    message = errCode.DESC();
                    break;
                }
            }
            return message;
        }
    }

    public static void main(String[] args) {
        // Event rpEvent = Event.getRPEvent("MO", 1);
        //  System.out.println("rpEvent = " + rpEvent);

    }
}
