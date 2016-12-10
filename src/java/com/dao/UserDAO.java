/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.Addzonedish;
import com.beans.Content;
import com.beans.Coupon;
import com.beans.DealBean;
import com.beans.User;
import com.common.AESAlgo;
import com.common.ConfigUtil;
import com.common.Constants;
import com.common.DBConnection;
import com.common.HttpDispatchClient;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_USER_DETAILS;
import static com.common.ResponseCodes.ServiceErrorCodes.SUCCESS;
import static com.common.ResponseCodes.ServiceErrorCodes.USERID_NOT_EXISTS;
import static com.common.ResponseCodes.ServiceErrorCodes.WRONG_PASSWORD;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_DEAL_CODE;
import static com.common.ResponseCodes.ServiceErrorCodes.SUFFICIENT_CREDITS;
import static com.common.ResponseCodes.ServiceErrorCodes.GENERIC_ERROR;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_COUPON;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_USER_DETAILS;
import static com.common.ResponseCodes.ServiceErrorCodes.SUCCESS;
import static com.common.ResponseCodes.ServiceErrorCodes.USERID_NOT_EXISTS;
import static com.common.ResponseCodes.ServiceErrorCodes.WRONG_PASSWORD;
import com.common.RestBean;
import com.common.RestImages;

import com.common.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author chhavikumar.b
 */
public class UserDAO {

    DBConnection dbconnection = null;
    String url = "";
    public static final double oneSquareMeter = Double.valueOf(ConfigUtil.getProperty("one.square.meter", "0.092903"));

    public UserDAO() {
        dbconnection = DBConnection.getInstance();
        url = ConfigUtil.getProperty("url", "http://35.154.44.42:8080/bookmanagement");
//        url = "http://localhost:8080/menubook";
        HttpDispatchClient.getInstance();
    }
    static Log logger = LogFactory.getLog(UserDAO.class);

    public int addcontent(Content objContent) {
        String insertQuery = ConfigUtil.getProperty("store.book.query", "INSERT INTO `adminbook`.`book`(`title`,`published_date`,`author_name`,`file_path`,`book_type`,`book_url`,`type`) VALUES (?,?,?,?,?,?,?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getBook_title());
                pstmt.setString(2, objContent.getPublished_date());
                pstmt.setString(3, objContent.getAuthor_name());
                pstmt.setString(4, objContent.getFilePath());
                pstmt.setString(5, objContent.getBook_type());
                pstmt.setString(6, objContent.getContent_file());
                pstmt.setString(7, objContent.getType());
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("add book() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("add book() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }

    public int edit_content(Content objContent) {
        String updateQuery = ConfigUtil.getProperty("store.book.query", "UPDATE `adminbook`.`book` SET `title`=?,`published_date`=?,`author_name`=?,`book_type`=? where id=? ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                if (StringUtils.isBlank(objContent.getFilePath()) && StringUtils.isBlank(objContent.getContent_file())) {
                    pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                }else if (StringUtils.isNotBlank(objContent.getFilePath()) && StringUtils.isBlank(objContent.getContent_file())) {
                    updateQuery = ConfigUtil.getProperty("store.book.query", "UPDATE `adminbook`.`book` SET `title`=?,`published_date`=?,`author_name`=?,`book_type`=?,`file_path`=? where id=? ");
                    pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                }else if (StringUtils.isBlank(objContent.getFilePath()) && StringUtils.isNotBlank(objContent.getContent_file())) {
                    updateQuery = ConfigUtil.getProperty("store.book.query", "UPDATE `adminbook`.`book` SET `title`=?,`published_date`=?,`author_name`=?,`book_type`=?,`book_url`=? where id=? ");
                    pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                }else {
                    updateQuery = ConfigUtil.getProperty("store.book.query", "UPDATE `adminbook`.`book` SET `title`=?,`published_date`=?,`author_name`=?,`book_type`=?,`file_path`=?,`book_url`=? where id=? ");
                    pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                }

                pstmt.setString(1, objContent.getBook_title());
                pstmt.setString(2, objContent.getPublished_date());
                pstmt.setString(3, objContent.getAuthor_name());
                pstmt.setString(4, objContent.getBook_type());
                
                if (StringUtils.isBlank(objContent.getFilePath()) && StringUtils.isBlank(objContent.getContent_file())) {
                    pstmt.setString(5, objContent.getId());
                }else if (StringUtils.isNotBlank(objContent.getFilePath()) && StringUtils.isBlank(objContent.getContent_file())) {
                    pstmt.setString(5, objContent.getFilePath());
                    pstmt.setString(6, objContent.getId());
                }else if (StringUtils.isBlank(objContent.getFilePath()) && StringUtils.isNotBlank(objContent.getContent_file())) {
                    pstmt.setString(5, objContent.getContent_file());
                    pstmt.setString(6, objContent.getId());
                }else {
                    pstmt.setString(5, objContent.getFilePath());
                    pstmt.setString(6, objContent.getContent_file());
                    pstmt.setString(7, objContent.getId());
                }
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("edit book() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("edit book() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }
    
    public int addphoto(Content objContent) {
        String insertQuery = ConfigUtil.getProperty("addphoto", "INSERT INTO `adminbook`.`photos`(`image`) VALUES (?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getFilePath());
                
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("addphoto() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("addphoto() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }

    public JSONObject edit_rss_content(String id) {
        JSONObject neighborhoodObj = new JSONObject();
        String single_details = ConfigUtil.getProperty("single_details", "SELECT * FROM rss_feed where id=" + id);
        ResultSet rs = null;

        PreparedStatement pstmt = null;

        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(single_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    neighborhoodObj.put("category_id", Utilities.nullToEmpty(rs.getString("category_id")));
                    neighborhoodObj.put("description", Utilities.nullToEmpty(rs.getString("description")));
                }

            }

        } catch (Exception e) {
            logger.error(" Got neighborhoodObj" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return neighborhoodObj;
    }

    public JSONArray get_single_upload_list(String strTid, int fromIndex, int endIndex, String type) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("query", "SELECT * FROM book where type = " + type + " order by published_date asc LIMIT " + fromIndex + "," + endIndex + "");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("title", Utilities.nullToEmpty(rs.getString("title")));
                    property.put("author_name", Utilities.nullToEmpty(rs.getString("author_name")));
                    property.put("published_date", Utilities.nullToEmpty(rs.getString("published_date")));
                    property.put("file_path", url + Utilities.nullToEmpty(rs.getString("file_path")));
                    property.put("book_url", url + Utilities.nullToEmpty(rs.getString("book_url")));
                    property.put(Constants.createdOn, rs.getString("created_datetime"));
                    propertyArray.put(property);
                }

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_single_upload_list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while get_single_upload_list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int get_single_upload_listCount(String strTid,String type) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("count.query", "SELECT count(*) as count FROM book where type="+type);

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_single_upload_listCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while mortagget_single_upload_listCountesettinglistCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }

    public int delete_single_upload(String id) throws SQLException, Exception {
        String delete_single_upload = ConfigUtil.getProperty("delete_single_upload", "DELETE FROM `book` WHERE id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_single_upload);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_single_upload(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_single_upload() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_single_upload() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }
    public int delete_photo(String id) throws SQLException, Exception {
        String delete_single_upload = ConfigUtil.getProperty("delete_photo", "DELETE FROM `photos` WHERE id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_single_upload);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_photo(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_photo() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_photo() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public String getcategory(String strTid) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("getcategory.query", "SELECT * FROM category");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONObject objFinalResponse = new JSONObject();
        try {

            JSONObject objRequest = new JSONObject();
            objRequest.put("code", "1000");
            objRequest.put("transid", strTid);
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                //pstmt.setString(1, nCategory);
                rs = pstmt.executeQuery();
                JSONArray propertyArray = new JSONArray();
                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getInt("id"));
                    property.put(Constants.name, Utilities.nullToEmpty(rs.getString("Category")));
                    propertyArray.put(property);
                }
                objRequest.put("propertyTypes", propertyArray);
                objFinalResponse.put("response", objRequest);

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while getcategory" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while getcategory" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return objFinalResponse.toString();
    }

    public String get_sub_category(String nCategory, String strTid) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("get_sub_category.details.query", "SELECT * FROM sub_category where category_id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONObject objFinalResponse = new JSONObject();
        try {

            JSONObject objRequest = new JSONObject();
            objRequest.put("code", "1000");
            objRequest.put("transid", strTid);
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                pstmt.setString(1, nCategory);
                rs = pstmt.executeQuery();
                JSONArray propertyArray = new JSONArray();
                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getInt("id"));
                    property.put(Constants.name, Utilities.nullToEmpty(rs.getString("sub_category")));
                    propertyArray.put(property);
                }
                objRequest.put("propertyTypes", propertyArray);
                objFinalResponse.put("response", objRequest);

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_sub_category" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while get_sub_category" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return objFinalResponse.toString();
    }

    public int add_bulk_content(Content objContent) {
        String insertQuery = ConfigUtil.getProperty("store.content.query", "INSERT INTO `cms`.`contents`(`content_name`,`content_type_id`,`code`,`category_id`,`sub_category_id`,`provider_code`,`keyword`,`description`,`expiry_date`) VALUES (?,?,?,?,?,?,?,?,?)");
        String insertQuery2 = ConfigUtil.getProperty("store.content.query", "INSERT INTO `cms`.`content_msg`(`content`,`contents_id`) VALUES (?,?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getContentname());
                pstmt.setString(2, objContent.getContent_type_id());
                pstmt.setString(3, objContent.getCode());
                pstmt.setString(4, objContent.getCategory_id());
                pstmt.setString(5, objContent.getSub_category_id());
                pstmt.setString(6, objContent.getProvider_code());
                pstmt.setString(7, objContent.getKeyword());
                pstmt.setString(8, objContent.getDescription());
                pstmt.setString(9, objContent.getExpiry_date());

                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                pstmt = objConn.prepareStatement(insertQuery2, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getContent());
                pstmt.setInt(2, nRes);
                nRes = pstmt.executeUpdate();
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("add_bulk_content() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("add_bulk_content() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }

    public int edit_bulk_content(Content objContent) {
        String updateQuery = ConfigUtil.getProperty("store.content.query", "UPDATE `cms`.`contents` SET `content_name`=?,`content_type_id`=?,`code`=?,`category_id`=?,`sub_category_id`=?,`provider_code`=?,`keyword`=?,`description`=?,`expiry_date`=? where id=? ");
        String updateQuery2 = ConfigUtil.getProperty("store.content.query", "UPDATE `cms`.`content_msg` SET `content`=? where contents_id=? ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getContentname());
                pstmt.setString(2, objContent.getContent_type_id());
                pstmt.setString(3, objContent.getCode());
                pstmt.setString(4, objContent.getCategory_id());
                pstmt.setString(5, objContent.getSub_category_id());
                pstmt.setString(6, objContent.getProvider_code());
                pstmt.setString(7, objContent.getKeyword());
                pstmt.setString(8, objContent.getDescription());
                pstmt.setString(9, objContent.getExpiry_date());
                pstmt.setString(10, objContent.getId());

                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                pstmt = objConn.prepareStatement(updateQuery2, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getContent());
                pstmt.setString(2, objContent.getId());
                nRes = pstmt.executeUpdate();
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("edit_bulk_content() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("edit_bulk_content() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }

    public JSONObject edit_bulk_upload(String id) {
        JSONObject neighborhoodObj = new JSONObject();
        String single_details = ConfigUtil.getProperty("single_details", "SELECT * FROM contents where id=" + id);
        String single_details2 = ConfigUtil.getProperty("single_details2", "SELECT * FROM content_msg where contents_id=" + id);
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(single_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    neighborhoodObj.put("content_name", Utilities.nullToEmpty(rs.getString("content_name")));
                    neighborhoodObj.put("content_type_id", Utilities.nullToEmpty(rs.getString("content_type_id")));
                    neighborhoodObj.put("code", Utilities.nullToEmpty(rs.getString("code")));
                    neighborhoodObj.put("category_id", Utilities.nullToEmpty(rs.getString("category_id")));
                    neighborhoodObj.put("sub_category_id", Utilities.nullToEmpty(rs.getString("sub_category_id")));
                    neighborhoodObj.put("provider_code", Utilities.nullToEmpty(rs.getString("provider_code")));
                    neighborhoodObj.put("keyword", Utilities.nullToEmpty(rs.getString("keyword")));
                    neighborhoodObj.put("description", Utilities.nullToEmpty(rs.getString("description")));
                    neighborhoodObj.put("expiry_date", Utilities.nullToEmpty(rs.getString("expiry_date")));
                }

                pstmt1 = objConn.prepareStatement(single_details2);

                rs1 = pstmt1.executeQuery();

                if (rs1.next()) {
                    neighborhoodObj.put("content", Utilities.nullToEmpty(rs1.getString("content")));
                }
            }

        } catch (Exception e) {
            logger.error(" Got edit_bulk_upload" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
                dbconnection.closeConnection(rs1, pstmt1, objConn);
            }
        }
        return neighborhoodObj;
    }

    public JSONArray get_bulk_upload_list(String strTid, int fromIndex, int endIndex) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("query", "SELECT * FROM contents order by content_name asc LIMIT " + fromIndex + "," + endIndex + "");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("content_name", Utilities.nullToEmpty(rs.getString("content_name")));
                    property.put("code", Utilities.nullToEmpty(rs.getString("code")));
                    property.put("provider_code", Utilities.nullToEmpty(rs.getString("provider_code")));
                    property.put("description", Utilities.nullToEmpty(rs.getString("description")));
                    property.put("expiry_date", Utilities.nullToEmpty(rs.getString("expiry_date")));
                    property.put(Constants.createdOn, rs.getString("created_datetime"));
                    propertyArray.put(property);
                }

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_bulk_upload_list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while get_bulk_upload_list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int get_bulk_upload_listCount(String strTid) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("count.query", "SELECT count(*) as count FROM contents");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_bulk_upload_listCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while get_bulk_upload_listCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }

    public int delete_bulk_upload(String id) throws SQLException, Exception {
        String delete_bulk_upload = ConfigUtil.getProperty("delete_bulk_upload", "DELETE FROM `contents` WHERE id=?");
        String delete_bulk_upload2 = ConfigUtil.getProperty("delete_bulk_upload2", "DELETE FROM `content_msg` WHERE contents_id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_bulk_upload);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                pstmt = objConn.prepareStatement(delete_bulk_upload2);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();
                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_bulk_upload(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_bulk_upload() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_bulk_upload() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public JSONArray get_list_rss_feed(String catid, String strTid, int fromIndex, int endIndex) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("query", "SELECT rf.id AS id, rf.description AS description, rf.status AS STATUS,\n"
                + " rf.created_datetime AS created_datetime, sb.category AS Category FROM rss_feed AS rf, category AS sb\n"
                + " WHERE sb.id = rf.category_id ");
        if (StringUtils.isNotBlank(catid)) {
            query = query + " and rf.category_id=" + catid + " ORDER BY rf.id asc LIMIT " + fromIndex + "," + endIndex;
        } else {
            query = query + "ORDER BY rf.id asc LIMIT " + fromIndex + "," + endIndex;
        }
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("description", Utilities.nullToEmpty(rs.getString("description")));
                    property.put("status", Utilities.nullToEmpty(rs.getString("status")));
                    property.put("category", Utilities.nullToEmpty(rs.getString("Category")));
                    property.put(Constants.createdOn, rs.getString("created_datetime"));
                    propertyArray.put(property);
                }

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_list_rss_feed" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while get_list_rss_feed" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int get_list_rss_feedCount(String catid, String strTid) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("count.query", "SELECT count(*) as count FROM rss_feed");
        if (StringUtils.isNotBlank(catid)) {
            query = query + " where category_id=" + catid;
        }
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while get_list_rss_feedCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while get_list_rss_feedCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }

    public String getsubcategory(String strTid) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("getsubcategory.query", "SELECT * FROM category");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONObject objFinalResponse = new JSONObject();
        try {

            JSONObject objRequest = new JSONObject();
            objRequest.put("code", "1000");
            objRequest.put("transid", strTid);
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                //pstmt.setString(1, nCategory);
                rs = pstmt.executeQuery();
                JSONArray propertyArray = new JSONArray();
                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getInt("id"));
                    property.put(Constants.name, Utilities.nullToEmpty(rs.getString("Category")));
                    propertyArray.put(property);
                }
                objRequest.put("propertyTypes", propertyArray);
                objFinalResponse.put("response", objRequest);

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while getsubcategory" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while getsubcategory" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return objFinalResponse.toString();
    }

    public int rss_feed_insert(InputStream Filename, String sub_category_id, String description) throws SQLException, Exception {
        String insertQuery = ConfigUtil.getProperty("rss_feed_insert.query", "INSERT INTO `cms`.`rss_feed`(`category_id`,`description`) VALUES (?,?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        BufferedReader br = null;

        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, sub_category_id);
                if (StringUtils.isNotBlank(description)) {
                    pstmt.setString(2, description);
                    status = pstmt.executeUpdate();
                } else {
                    Utilities.writeexcel(Filename, pstmt);
//                    String sCurrentLine;
//                    br = new BufferedReader(new InputStreamReader(Filename));
//
//                    while ((sCurrentLine = br.readLine()) != null) {
//                        pstmt.setString(2, sCurrentLine);
//                        status = pstmt.executeUpdate();
//                    }

                }

            } else {
                logger.error("rss_feed_insert(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("rss_feed_insert() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("rss_feed_insert() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
            if (br != null) {
                br.close();
            }
        }
        return status;
    }

    public User adminLoginDetails(User loginRequestBean, String strTransId) throws SQLException, Exception {
        String selectQuery = ConfigUtil.getProperty("user.login.query", "select * from users where user_id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        User user = new User();
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(selectQuery);
                String password = AESAlgo.encrypt(loginRequestBean.getPassword());
                pstmt.setString(1, loginRequestBean.getUser_id());

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    String pwd = rs.getString("password");
                    if (!pwd.equals(password)) {
                        user.setErrorMessage(Utilities.prepareReponse(WRONG_PASSWORD.getCode(), WRONG_PASSWORD.DESC(), strTransId));
                        return user;
                    }
                    user.setErrorMessage(Utilities.prepareReponsetoken(SUCCESS.getCode(), SUCCESS.DESC(), strTransId));

                    return user;
                } else {
                    user.setErrorMessage(Utilities.prepareReponse(USERID_NOT_EXISTS.getCode(), USERID_NOT_EXISTS.DESC(), strTransId));
                    return user;
                }
            }

        } catch (SQLException sqle) {
            logger.error("addUserDetails() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("addUserDetails() : Got SQLException " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        user.setErrorMessage(Utilities.prepareReponse(INVALID_USER_DETAILS.getCode(), INVALID_USER_DETAILS.DESC(), ""));
        return user;
    }

    public JSONObject edit_book(String id) {
        JSONObject neighborhoodObj = new JSONObject();
        String single_details = ConfigUtil.getProperty("book_details", "SELECT * FROM book where id=" + id);
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(single_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    neighborhoodObj.put("book_title", Utilities.nullToEmpty(rs.getString("title")));
                    neighborhoodObj.put("book_type", Utilities.nullToEmpty(rs.getString("book_type")));
                    neighborhoodObj.put("published_date", Utilities.nullToEmpty(rs.getString("published_date")));
                    neighborhoodObj.put("author_name", Utilities.nullToEmpty(rs.getString("author_name")));
                    neighborhoodObj.put("file_path", url + Utilities.nullToEmpty(rs.getString("file_path")));
                    neighborhoodObj.put("book_url", url + Utilities.nullToEmpty(rs.getString("book_url")));
                }
            }
        } catch (Exception e) {
            logger.error(" Got book" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
                dbconnection.closeConnection(rs1, pstmt1, objConn);
            }
        }
        return neighborhoodObj;
    }
    
    public int addcontenttype(Content objContent) {
        String insertQuery = ConfigUtil.getProperty("query", "INSERT INTO `adminbook`.`content_type`(`title`,`type`) VALUES (?,?)");
        String insertQuery2 = ConfigUtil.getProperty("query", "INSERT INTO `adminbook`.`content_type_image`(`image`,`content_id`) VALUES (?,?)");
        
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getTitle());
                pstmt.setString(2, objContent.getType());
               
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                pstmt = objConn.prepareStatement(insertQuery2, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getFilePath());
                pstmt.setInt(2, nRes);
                nRes = pstmt.executeUpdate();
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("add content type() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("add content type() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }
    
    public int addaudiovideotype(Content objContent) {
        String insertQuery = ConfigUtil.getProperty("query", "INSERT INTO `adminbook`.`content_type`(`title`,`type`,`link`,`lang_type`) VALUES (?,?,?,?)");
        
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getTitle());
                pstmt.setString(2, objContent.getType());
                pstmt.setString(3, objContent.getLink());
                pstmt.setString(4, objContent.getBook_type());
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("addaudiovideotype() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("addaudiovideotype() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }
    
    public JSONArray audio_video_list(String strTid, int fromIndex, int endIndex, String type) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("query", "SELECT * from content_type where type = "+type+" order by title asc LIMIT " + fromIndex + "," + endIndex + "");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("title", Utilities.nullToEmpty(rs.getString("title")));
                    property.put("link", Utilities.nullToEmpty(rs.getString("link")));
                    propertyArray.put(property);
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while audio_video_list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while audio_video_list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }
    
    public JSONArray content_type_list(String strTid, int fromIndex, int endIndex, String type) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("query", "SELECT ct.id as id, cti.id as ctid, ct.title as title, cti.image as image FROM content_type as ct, content_type_image as cti where ct.id = cti.content_id and ct.type = "+type+" order by ct.title asc LIMIT " + fromIndex + "," + endIndex + "");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("title", Utilities.nullToEmpty(rs.getString("title")));
                    property.put("image", url + Utilities.nullToEmpty(rs.getString("image")));
                    property.put("ctid", Utilities.nullToEmpty(rs.getString("ctid")));
                    propertyArray.put(property);
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while content_type_list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while content_type_list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int content_type_listCount(String strTid,String type) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("count.query", "SELECT count(*) as count FROM content_type where type="+type);

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while content_type_listCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while content_type_listCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }
    
    public int delete_content_type(String id, String ctid) throws SQLException, Exception {
        
        String delete_content_type = ConfigUtil.getProperty("delete_content_type", "DELETE FROM `content_type` WHERE id=?");
        String delete_content_type2 = ConfigUtil.getProperty("delete_content_type", "DELETE FROM `content_type_image` WHERE id=?");
        
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_content_type);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();
                
                pstmt = objConn.prepareStatement(delete_content_type2);
                pstmt.setString(1, ctid);
                status = pstmt.executeUpdate();

                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_content_type(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_content_type() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_content_type() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }
    
    public int delete_audio_video(String id) throws SQLException, Exception {
        
        String delete_audio_video = ConfigUtil.getProperty("delete_audio_video", "DELETE FROM `content_type` WHERE id=?");
        
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_audio_video);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();
                
                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_audio_video(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_content_type() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_audio_video() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }
    
    public JSONObject edit_magazine(String id, String ctid) {
        JSONObject neighborhoodObj = new JSONObject();
        String single_details = ConfigUtil.getProperty("details", "SELECT ct.id as id, cti.id as ctid, ct.title as title, cti.image as image FROM content_type as ct, content_type_image as cti where ct.id = " + id + " AND cti.id = " + ctid);
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(single_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    neighborhoodObj.put("id", Utilities.nullToEmpty(rs.getString("id")));
                    neighborhoodObj.put("ctid", Utilities.nullToEmpty(rs.getString("ctid")));
                    neighborhoodObj.put("title", Utilities.nullToEmpty(rs.getString("title")));
                    neighborhoodObj.put("image", url + Utilities.nullToEmpty(rs.getString("image")));
                }
            }
        } catch (Exception e) {
            logger.error(" Got book" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
                dbconnection.closeConnection(rs1, pstmt1, objConn);
            }
        }
        return neighborhoodObj;
    }
    
    public JSONObject edit_audio_video_view(String id) {
        JSONObject neighborhoodObj = new JSONObject();
        String single_details = ConfigUtil.getProperty("details", "SELECT * from content_type where id = " + id);
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(single_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    neighborhoodObj.put("id", Utilities.nullToEmpty(rs.getString("id")));
                    neighborhoodObj.put("title", Utilities.nullToEmpty(rs.getString("title")));
                    neighborhoodObj.put("link", Utilities.nullToEmpty(rs.getString("link")));
                    neighborhoodObj.put("book_type", Utilities.nullToEmpty(rs.getString("lang_type")));
                }
            }
        } catch (Exception e) {
            logger.error(" Got book" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
                dbconnection.closeConnection(rs1, pstmt1, objConn);
            }
        }
        return neighborhoodObj;
    }
    
    public int editcontenttype(Content objContent) {
        String updateQuery = ConfigUtil.getProperty("query", "UPDATE `adminbook`.`content_type` SET `title`=? where id=? ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                if (StringUtils.isBlank(objContent.getFilePath())) {
                    pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                } else {
                    updateQuery = ConfigUtil.getProperty("query", "UPDATE `adminbook`.`content_type` SET `title`=? where id=? ");
                    pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                   
                }
                pstmt.setString(1, objContent.getTitle());
                pstmt.setString(2, objContent.getCid());
              
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                if (StringUtils.isNotBlank(objContent.getFilePath())) {
                    pstmt1.setString(1, objContent.getFilePath());
                    pstmt1.setString(2, objContent.getCtid());

                    String updateQuery2 = ConfigUtil.getProperty("query", "UPDATE `adminbook`.`content_type_image` SET `image`=? where id=? ");
                    pstmt1 = objConn.prepareStatement(updateQuery2, Statement.RETURN_GENERATED_KEYS);
                    nRes = pstmt1.executeUpdate();
                }
                
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("edit editcontenttype() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("edit editcontenttype() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }
    
    public int editaudiovideo(Content objContent) {
        String updateQuery = ConfigUtil.getProperty("query", "UPDATE `adminbook`.`content_type` SET `title`=?, link=?, lang_type=? where id=? ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
                
                pstmt.setString(1, objContent.getTitle());
                pstmt.setString(2, objContent.getLink());
                pstmt.setString(3, objContent.getBook_type());
                pstmt.setString(4, objContent.getCid());
              
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }                
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("edit editaudiovideo() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("edit editaudiovideo() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }
    
    public int addthoughts(Content objContent) {
        String insertQuery = ConfigUtil.getProperty("query", "INSERT INTO `adminbook`.`content_type`(`title`,`type`) VALUES (?,?)");
        String insertQuery2 = ConfigUtil.getProperty("query", "INSERT INTO `adminbook`.`content_type_image`(`image`,`content_id`) VALUES (?,?)");
        
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {

                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getTitle());
                pstmt.setString(2, objContent.getType());
               
                int nRes = pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    nRes = rs.getInt(1);
                }
                pstmt = objConn.prepareStatement(insertQuery2, Statement.RETURN_GENERATED_KEYS);

                pstmt.setString(1, objContent.getFilePath());
                pstmt.setInt(2, nRes);
                nRes = pstmt.executeUpdate();
                return nRes;
            }
        } catch (SQLException sqle) {
            logger.error("add content type() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("add content type() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }
    
       public JSONArray photolist_list(String strTid, int fromIndex, int endIndex) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("query", "SELECT * FROM photos order by id desc LIMIT " + fromIndex + "," + endIndex + "");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("image", url + Utilities.nullToEmpty(rs.getString("image")));
                    propertyArray.put(property);
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while content_type_list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while content_type_list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int photolist_listCount(String strTid) throws SQLException, Exception {
        String query = ConfigUtil.getProperty("count.query", "SELECT count(*) as count FROM photos");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while content_type_listCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while content_type_listCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }

}
