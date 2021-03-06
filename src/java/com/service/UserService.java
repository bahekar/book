/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.Addzonedish;
import com.beans.Content;
import com.beans.Coupon;
import com.beans.DealBean;
import com.beans.LoginRequestBean;
import com.beans.User;
import com.beans.UserPasswordBean;
import com.common.ResponseCodes;
import static com.common.ResponseCodes.ServiceErrorCodes.GENERIC_ERROR;
import static com.common.ResponseCodes.ServiceErrorCodes.SUCCESS;
import static com.common.ResponseCodes.ServiceErrorCodes.FAV_FAILED;
import static com.common.ResponseCodes.ServiceErrorCodes.GENERIC_ERROR;
import static com.common.ResponseCodes.ServiceErrorCodes.INFAV_FAILED;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_DATA;
import static com.common.ResponseCodes.ServiceErrorCodes.SUCCESS;
import com.common.RestBean;
import com.common.Utilities;

import com.dao.UserDAO;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author chhavikumar.b
 */
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    private static UserDAO objUserDAO = null;

    public UserService() {
        try {
            if (objUserDAO == null) {
                objUserDAO = new UserDAO();
            }
        } catch (Exception e) {
            logger.error("Exception in UserService(),ex:" + e.getMessage(), e);
        }
    }

    public String addcontent(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.addcontent(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in add book(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
   
    public String edit_content(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.edit_content(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in addPropertyToAgent(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    public String addphoto(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.addphoto(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in add book(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
   
    public JSONObject edit_rss_content(String id) {
        return objUserDAO.edit_rss_content(id);
    }
   
    public int get_single_upload_listCount(String transId,String type) throws SQLException, Exception {
        return objUserDAO.get_single_upload_listCount(transId,type);
    }

    public JSONArray get_single_upload_list(String transId, int fromIndex, int endIndex, String type) throws SQLException, Exception {
        return objUserDAO.get_single_upload_list(transId, fromIndex, endIndex, type);
    }
    
    public String delete_single_upload(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_single_upload(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_single_upload(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    public String delete_photo(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.delete_photo(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }
        } catch (Exception e) {
            logger.error("Exception in delete_photo(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public String getcategory(String transId) throws SQLException, Exception {
        return objUserDAO.getcategory(transId);
    }
    
    public String get_sub_category(String id, String transId) throws SQLException, Exception {
        return objUserDAO.get_sub_category(id, transId);
    }
    
    public String add_bulk_content(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.add_bulk_content(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in add_bulk_content(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
   
    public String edit_bulk_content(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.edit_bulk_content(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in edit_bulk_content(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
   
    public JSONObject edit_bulk_upload(String id) {
        return objUserDAO.edit_bulk_upload(id);
    }
   
    public int get_bulk_upload_listCount(String transId) throws SQLException, Exception {
        return objUserDAO.get_bulk_upload_listCount(transId);
    }

    public JSONArray get_bulk_upload_list(String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.get_bulk_upload_list(transId, fromIndex, endIndex);
    }
    
    public String delete_bulk_upload(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_bulk_upload(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_bulk_upload(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public int get_list_rss_feedCount(String catid,String transId) throws SQLException, Exception {
        return objUserDAO.get_list_rss_feedCount(catid,transId);
    }

    public JSONArray get_list_rss_feed(String catid,String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.get_list_rss_feed(catid,transId, fromIndex, endIndex);
    }
    
    public String getsubcategory(String transId) throws SQLException, Exception {
        return objUserDAO.getsubcategory(transId);
    }
    
    public String rss_feed_insert(InputStream Filename,String sub_category_id, String description, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.rss_feed_insert(Filename,sub_category_id, description);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }
        } catch (Exception e) {
            logger.error("Exception in rss_feed_insert(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    public User adminLoginDetails(User reqBean, String strTid) {
        int isUpdated = 0;
        User user = new User();
        try {
            user = objUserDAO.adminLoginDetails(reqBean, strTid);
        } catch (SQLException sqle) {
            user.setErrorMessage(Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid));
        } catch (Exception e) {
            logger.error("Exception in addUserDetails(),ex:" + e.getMessage(), e);
            user.setErrorMessage(Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid));
        }
        return user;
    }
    
    public JSONObject edit_book(String id) {
        return objUserDAO.edit_book(id);
    }
        
    public String addcontenttype(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.addcontenttype(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in add book(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public String addaudiovideotype(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.addaudiovideotype(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in addaudiovideotype(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public String addthoughts(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.addthoughts(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in add book(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public int content_type_listCount(String transId,String type) throws SQLException, Exception {
        return objUserDAO.content_type_listCount(transId,type);
    }

    public JSONArray content_type_list(String transId, int fromIndex, int endIndex, String type) throws SQLException, Exception {
        return objUserDAO.content_type_list(transId, fromIndex, endIndex, type);
    }
    
    public JSONArray audio_video_list(String transId, int fromIndex, int endIndex, String type) throws SQLException, Exception {
        return objUserDAO.audio_video_list(transId, fromIndex, endIndex, type);
    }
    
    public int photolist_listCount(String transId) throws SQLException, Exception {
        return objUserDAO.photolist_listCount(transId);
    }

    public JSONArray photolist_list(String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.photolist_list(transId, fromIndex, endIndex);
    }
    
    public String delete_content_type(String id, String ctid, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_content_type(id, ctid);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_single_upload(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public String delete_audio_video(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_audio_video(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_single_upload(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public JSONObject edit_magazine(String id, String ctid) {
        return objUserDAO.edit_magazine(id, ctid);
    }
    
    public JSONObject edit_audio_video_view(String id) {
        return objUserDAO.edit_audio_video_view(id);
    }
    
    public String editcontenttype(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.editcontenttype(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in editcontenttype(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    
    public String editaudiovideo(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.editaudiovideo(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in editcontenttype(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
     public String addUserDetails(User user, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.addUserDetails(user);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (SQLException sqle) {
            if (sqle.getMessage().contains("idx_userid_uq")) {
                errorCode = ResponseCodes.ServiceErrorCodes.USERID_ALREADY_EXISTS;
            } else if (sqle.getMessage().contains("idx_loginid_uq")) {
                errorCode = ResponseCodes.ServiceErrorCodes.USERID_ALREADY_EXISTS;
            }
            try {
                nUserID = getUserId(user.getUser_id(), strTid);
            } catch (Exception e) {
            }
            logger.error(" Got SQLException " + Utilities.getStackTrace(sqle));
            return Utilities.prepareReponsetoken(errorCode.getCode() + "", errorCode.DESC(), strTid, nUserID);

        } catch (Exception e) {
            logger.error("Exception in addUserDetails(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public int getUserId(String userId, String transId) throws SQLException, Exception {
        return objUserDAO.getUserID(userId, transId);
    }
    
    public String signIn(LoginRequestBean reqBean, String strTid) {
        int isUpdated = 0;
        try {
            return objUserDAO.loginDetails(reqBean, strTid);
        } catch (SQLException sqle) {
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        } catch (Exception e) {
            logger.error("Exception in addUserDetails(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    public String passwordService(UserPasswordBean userPasswordBean, String transId) throws SQLException, Exception {
        return objUserDAO.passwordService(userPasswordBean, transId);
    }
    
    public JSONArray ask_list(String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.ask_list(transId, fromIndex, endIndex);
    }
    
    public int ask_listCount(String transId) throws SQLException, Exception {
        return objUserDAO.ask_listCount(transId);
    }

    public JSONArray getasklist(String id) {
        return objUserDAO.getasklist(id);
    }
    
    public JSONObject edit_ask(String id) {
        return objUserDAO.edit_ask(id);
    }
    
    public String editask(Content objContent) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.editask(objContent);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(INVALID_DATA.getCode(), INVALID_DATA.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in editask(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
}
