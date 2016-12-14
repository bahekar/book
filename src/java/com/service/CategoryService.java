/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.RssBean;
import com.beans.User;
import com.common.ResponseCodes;
import static com.common.ResponseCodes.ServiceErrorCodes.GENERIC_ERROR;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_DATA;
import static com.common.ResponseCodes.ServiceErrorCodes.SUCCESS;
import com.common.Utilities;
import com.dao.CategoryDAO;
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
public class CategoryService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    private static CategoryDAO objUserDAO = null;

    public CategoryService() {
        try {
            if (objUserDAO == null) {
                objUserDAO = new CategoryDAO();
            }

        } catch (Exception e) {
            logger.error("Exception in UserService(),ex:" + e.getMessage(), e);
        }
    }

    public String addCategory(String category, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.addCategory(category);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }
        } catch (Exception e) {
            logger.error("Exception in addUserDetails(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public JSONArray categorylist(String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.categorylist(transId, fromIndex, endIndex);
    }

    public int categorylistCount(String transId) throws SQLException, Exception {
        return objUserDAO.categorylistCount(transId);
    }

    public JSONObject getCategory_details(String strTid, String id) {
        return objUserDAO.getCategory_details(id);
    }

    public JSONObject get_sub_category_details(String strTid, String id) {
        return objUserDAO.get_sub_category_details(id);
    }

    public String categoryupdate(String name, String id) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.categoryupdate(name, id);
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

    public JSONArray sub_category_list(String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.sub_category_list(transId, fromIndex, endIndex);
    }

    public int sub_category_listCount(String transId) throws SQLException, Exception {
        return objUserDAO.sub_category_listCount(transId);
    }

    public String sub_category_insert(String sub_category, String category_id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.sub_category_insert(sub_category, category_id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }
        } catch (Exception e) {
            logger.error("Exception in sub_category_insert(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public String sub_category_update(String name, String id, String category_id) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.sub_category_update(name, id, category_id);
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

    public String delete_category(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_category(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_category(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public String delete_sub_category(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_sub_category(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_sub_category(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public JSONArray getcategory(String strTid) {
        return objUserDAO.getcategory();
    }

    public JSONArray getAuthors() {
        return objUserDAO.getAuthors();
    }

    public JSONArray getsubcategory(String id) {
        return objUserDAO.getsubcategory(id);
    }

    public JSONObject getbooks(String language,String type) {
        return objUserDAO.getbooks(language,type);
    }

    public String delete_rss_feed(String strTid, String id) {

        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_rss_feed(id);;
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_sub_category(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public String rss_feed_update(RssBean rss) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.rss_feed_update(rss);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), "");
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), "");
            }
        } catch (Exception e) {
            logger.error("Exception in rss_feed_insert(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), "");
        }
    }

    public JSONArray faqlist(String transId, int fromIndex, int endIndex) throws SQLException, Exception {
        return objUserDAO.faqlist(transId, fromIndex, endIndex);
    }

    public int faqlistCount(String transId) throws SQLException, Exception {
        return objUserDAO.faqlistCount(transId);
    }

    public String faqsave(String question, String answer, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.faqsave(question, answer);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }
        } catch (Exception e) {
            logger.error("Exception in faqsave(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public String delete_faq(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.delete_faq(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_faq(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    public JSONObject getFAQ_details(String strTid, String id) {
        return objUserDAO.getFAQ_details(id);
    }

    public String faqupdate(String question, String answer, String id) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        String strTid = UUID.randomUUID().toString();
        try {

            isUpdated = objUserDAO.faqupdate(question, answer, id);
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

    public JSONArray getphotos() {
        return objUserDAO.getphotos();
    }

    public JSONArray getContent(String type,String lang_type) {
        return objUserDAO.getContent(type,lang_type);
    }
    
      public String addfaq(User user, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {
            isUpdated = objUserDAO.addfaq(user);
            if (isUpdated > 0) {
                return Utilities.prepareReponse(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }
        } catch (Exception e) {
            logger.error("Exception in addUserDetails(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }
    public JSONArray getFAQ(User user) {
        return objUserDAO.getFAQ(user);
    }
    
      public String deletefaq(String id, String strTid) {
        int isUpdated = 0;
        int nUserID = -1;
        ResponseCodes.ServiceErrorCodes errorCode = null;
        try {

            isUpdated = objUserDAO.deletefaq(id);
            if (isUpdated > 0) {
                return Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTid);
            } else {
                return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
            }

        } catch (Exception e) {
            logger.error("Exception in delete_category(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

}
