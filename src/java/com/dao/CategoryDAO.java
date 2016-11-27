/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.RssBean;
import com.common.ConfigUtil;
import com.common.Constants;
import com.common.DBConnection;
import com.common.HttpDispatchClient;
import com.common.Utilities;
import static com.dao.UserDAO.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author chhavikumar.b
 */
public class CategoryDAO {

    DBConnection dbconnection = null;
    String url = "";
    public static final double oneSquareMeter = Double.valueOf(ConfigUtil.getProperty("one.square.meter", "0.092903"));

    public CategoryDAO() {
        dbconnection = DBConnection.getInstance();
        url = ConfigUtil.getProperty("url", "http://54.169.135.247:8080/menubook");
//        url = "http://localhost:8080/menubook";
        HttpDispatchClient.getInstance();
    }
    static Log logger = LogFactory.getLog(UserDAO.class);

    public int addCategory(String category) throws SQLException, Exception {
        String insertQuery = ConfigUtil.getProperty("store.category.data.query", "INSERT INTO `adminbook`.`category`(`category`) VALUES (?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, category);
                status = pstmt.executeUpdate();
            } else {
                logger.error("addUserDetails(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("addUserDetails() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("addUserDetails() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public JSONArray categorylist(String strTid, int fromIndex, int endIndex) throws SQLException, Exception {
        String userdetailsquery = ConfigUtil.getProperty("cat.query", "SELECT * FROM category ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(userdetailsquery);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("Category", Utilities.nullToEmpty(rs.getString("Category")));
                    propertyArray.put(property);
                }

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while neighborhoodlist" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while neighborhoodlist" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int categorylistCount(String strTid) throws SQLException, Exception {
        String userdetailsquery = ConfigUtil.getProperty("cat.count.query", "SELECT count(*) as count FROM category");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(userdetailsquery);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while mortagesettinglistCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while mortagesettinglistCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }

    public JSONObject getCategory_details(String id) {
        JSONObject neighborhoodObj = new JSONObject();
        String neighborhood_details = ConfigUtil.getProperty("category_details", "SELECT * FROM category where id=" + id);
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(neighborhood_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {

                    neighborhoodObj.put(Constants.name, Utilities.nullToEmpty(rs.getString("Category")));

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

    public JSONObject get_sub_category_details(String id) {
        JSONObject sub_categoryObj = new JSONObject();
        String get_sub_category_details = ConfigUtil.getProperty("get_sub_category_details", "SELECT * FROM sub_category where id=" + id);
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(get_sub_category_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    sub_categoryObj.put(Constants.name, Utilities.nullToEmpty(rs.getString("sub_category")));
                    sub_categoryObj.put(Constants.id, Utilities.nullToEmpty(rs.getString("category_id")));
                }
            }
        } catch (Exception e) {
            logger.error(" Got get_sub_category_details" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return sub_categoryObj;
    }

    public int categoryupdate(String name, String id) {
        String insertQuery = ConfigUtil.getProperty("update.categoryupdate.to.user", "UPDATE `category` SET `Category`=? WHERE `id`=?;");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery);
                pstmt.setString(1, name);
                pstmt.setString(2, id);
                int nRes = pstmt.executeUpdate();
                return nRes;
            }

        } catch (SQLException sqle) {
            logger.error("addCredits() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("addCredits() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }

    public JSONArray sub_category_list(String strTid, int fromIndex, int endIndex) throws SQLException, Exception {
        String userdetailsquery = ConfigUtil.getProperty("cat.query", "SELECT * FROM sub_category ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(userdetailsquery);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("sub_category", Utilities.nullToEmpty(rs.getString("sub_category")));
                    propertyArray.put(property);
                }

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while sub_category_list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while sub_category_list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int sub_category_listCount(String strTid) throws SQLException, Exception {
        String userdetailsquery = ConfigUtil.getProperty("cat.count.query", "SELECT count(*) as count FROM sub_category");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(userdetailsquery);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while sub_category_listCount" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while sub_category_listCount" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }

    public int sub_category_insert(String sub_category, String category_id) throws SQLException, Exception {
        String insertQuery = ConfigUtil.getProperty("sub_category.query", "INSERT INTO `adminbook`.`sub_category`(`sub_category`,`category_id`) VALUES (?,?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, sub_category);
                pstmt.setString(2, category_id);
                status = pstmt.executeUpdate();
            } else {
                logger.error("sub_category(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("sub_category() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("sub_category() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public int sub_category_update(String name, String id, String category_id) {
        String insertQuery = ConfigUtil.getProperty("sub_category_update", "UPDATE `sub_category` SET `sub_category`=?, `category_id`=? WHERE `id`=?;");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery);
                pstmt.setString(1, name);
                pstmt.setString(2, category_id);
                pstmt.setString(3, id);
                int nRes = pstmt.executeUpdate();
                return nRes;

            }

        } catch (SQLException sqle) {
            logger.error("sub_category_update() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("sub_category_update() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }

        return -1;
    }

    public int delete_category(String id) throws SQLException, Exception {
        String delete_category = ConfigUtil.getProperty("delete_category", "DELETE FROM `category` WHERE id=?");
        String delete_sub_category = ConfigUtil.getProperty("delete_sub_category", "DELETE FROM `sub_category` WHERE category_id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_category);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                pstmt = objConn.prepareStatement(delete_sub_category);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();
                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_category(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_category() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_category() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public int delete_sub_category(String id) throws SQLException, Exception {
        String delete_sub_category = ConfigUtil.getProperty("delete_sub_category", "DELETE FROM `sub_category` WHERE id=?");

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_sub_category);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_sub_category(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_sub_category() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_sub_category() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public String getFeed(String id) {
        String selectfeed1 = ConfigUtil.getProperty("select.feed.query1", "SELECT * FROM category WHERE id=" + id + ";");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();
        StringBuffer ob = new StringBuffer();

        try {
            String date = Utilities.currentDate();
            String day = Utilities.currentDay();
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(selectfeed1);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    ob.append("<rss version=\"2.0\"><channel><link/><description/><copyright/>");
                    String title = rs.getString("Category");
                    String selectfeed = ConfigUtil.getProperty("select.feed.query", "SELECT * FROM rss_feed WHERE category_id=" + id + " and created_datetime like '" + date + "%' ORDER BY id asc LIMIT 1;");

                    pstmt = objConn.prepareStatement(selectfeed);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        ob.append(getXml(ob, rs, title));
                    } else {
                        //check for today date also 
                        selectfeed = ConfigUtil.getProperty("select.feed.query", "SELECT * FROM rss_feed WHERE STATUS=1 and pdate='" + day + "' and category_id=" + id + "  ORDER BY id asc LIMIT 1;");
                        pstmt = objConn.prepareStatement(selectfeed);
                        rs = pstmt.executeQuery();
                        if (rs.next()) {
                            ob.append(getXml(ob, rs, title));
                        } else {

                            selectfeed = ConfigUtil.getProperty("select.feed.query", "SELECT * FROM rss_feed WHERE STATUS='0' and  category_id=" + id + "  ORDER BY id asc LIMIT 1;");
                            pstmt = objConn.prepareStatement(selectfeed);
                            rs = pstmt.executeQuery();
                            if (rs.next()) {
                                String rowid = rs.getString("id");
                                ob.append(getXml(ob, rs, title));
                                //update remaning all to 0 
                                pstmt = objConn.prepareStatement("update rss_feed set STATUS='0' where category_id=" + id);
                                pstmt.executeUpdate();
                                pstmt = objConn.prepareStatement("update rss_feed set STATUS='1',pdate='" + day + "' where id=" + rowid);
                                pstmt.executeUpdate();
                                //update to status =1 and today date

                            } else {
                                selectfeed = ConfigUtil.getProperty("select.feed.query", "SELECT * FROM rss_feed WHERE STATUS=1 and  category_id=" + id + "  ORDER BY id asc LIMIT 1;");
                                pstmt = objConn.prepareStatement(selectfeed);
                                if (rs.next()) {
                                    String rowid = rs.getString("id");
                                    ob.append(getXml(ob, rs, title));
                                    pstmt = objConn.prepareStatement("update rss_feed set STATUS='1',pdate='" + day + "'  where id=" + rowid);
                                    pstmt.executeUpdate();
                                    //update to status =1 and today date
                                }
                            }
                        }
                    }
                    ob.append("</channel></rss>");
                }
            } else {
                ob.append("<response>no category found</response>");
            }
        } catch (SQLException sqle) {
            ob.append("<response>no category found</response>");
            logger.error(" Got SQLException while sub_category_list" + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            ob.append("<response>no category found</response>");
            logger.error(" Got Exception while sub_category_list" + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return ob.toString();
    }

    public static StringBuffer getXml(StringBuffer sb, ResultSet rs, String title) throws SQLException {
        StringBuffer ob = new StringBuffer();
        String description = rs.getString("description");
        ob.append("<item>");
        ob.append("<title>");
        ob.append(title);
        ob.append("</title>");
        ob.append("<description>");
        ob.append(description);
        ob.append("</description>");
        ob.append("</item>");
        return ob;
    }

    public int delete_rss_feed(String id) throws SQLException, Exception {
        String delete_sub_category = ConfigUtil.getProperty("delete_rss_feed", "DELETE FROM `rss_feed` WHERE id=?");

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_sub_category);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_sub_category(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_sub_category() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_sub_category() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }

    public int rss_feed_update(RssBean rss) throws SQLException, Exception {
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;

        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                String insertQuery = "update rss_feed set description=?,category_id=? where id=?";

                pstmt = objConn.prepareStatement(insertQuery);
                pstmt.setString(1, rss.getDescription());
                pstmt.setString(2, rss.getCategory_id());
                pstmt.setString(3, rss.getId());
                status = pstmt.executeUpdate();

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
        }
        return status;
    }
    
    public JSONArray faqlist(String strTid, int fromIndex, int endIndex) throws SQLException, Exception {
        String userdetailsquery = ConfigUtil.getProperty("faq.query", "SELECT * FROM faq ");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        JSONArray propertyArray = new JSONArray();

        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(userdetailsquery);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    JSONObject property = new JSONObject();
                    property.put(Constants.id, rs.getString(Constants.id));
                    property.put("question", Utilities.nullToEmpty(rs.getString("question")));
                    property.put("answer", Utilities.nullToEmpty(rs.getString("answer")));
                    propertyArray.put(property);
                }

            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while faq list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while faq list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return propertyArray;
    }

    public int faqlistCount(String strTid) throws SQLException, Exception {
        String userdetailsquery = ConfigUtil.getProperty("cat.count.query", "SELECT count(*) as count FROM faq");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        int count = 0;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(userdetailsquery);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException sqle) {
            logger.error(" Got SQLException while faq list" + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle);
        } catch (Exception e) {
            logger.error(" Got Exception while faq list" + Utilities.getStackTrace(e));
            throw new Exception(e);
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return count;
    }
    
    public int faqsave(String question, String answer) throws SQLException, Exception {
        String insertQuery = ConfigUtil.getProperty("store.faq.query", "INSERT INTO `adminbook`.`faq`(`question`,`answer`) VALUES (?,?)");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, question);
                pstmt.setString(2, answer);
                status = pstmt.executeUpdate();
            } else {
                logger.error("add faq(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("add faq() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("add faq() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }
    
    public int delete_faq(String id) throws SQLException, Exception {
        String delete_faq = ConfigUtil.getProperty("delete_category", "DELETE FROM `faq` WHERE id=?");
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        Connection objConn = null;
        int status = 0;
        try {
            objConn = dbconnection.getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(delete_faq);
                pstmt.setString(1, id);
                status = pstmt.executeUpdate();

                logger.debug("no of res deletetd is :" + status);

            } else {
                logger.error("delete_faq(): connection object is null");
            }
        } catch (SQLException sqle) {
            logger.error("delete_faq() : Got SQLException " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("delete_faq() Got Exception : " + Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return status;
    }
    
    public JSONObject getFAQ_details(String id) {
        JSONObject neighborhoodObj = new JSONObject();
        String neighborhood_details = ConfigUtil.getProperty("faq_details", "SELECT * FROM faq where id=" + id);
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {

            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(neighborhood_details);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    neighborhoodObj.put("question", Utilities.nullToEmpty(rs.getString("question")));
                    neighborhoodObj.put("answer", Utilities.nullToEmpty(rs.getString("answer")));
                }
            }

        } catch (Exception e) {
            logger.error(" Got getFAQ_details" + Utilities.getStackTrace(e));
        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return neighborhoodObj;
    }
    
    public int faqupdate(String question, String answer, String id) {
        String insertQuery = ConfigUtil.getProperty("update.faq", "UPDATE `faq` SET `question`=?, `answer`=? WHERE `id`=?;");

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection objConn = null;
        try {
            objConn = DBConnection.getInstance().getConnection();
            if (objConn != null) {
                pstmt = objConn.prepareStatement(insertQuery);
                pstmt.setString(1, question);
                pstmt.setString(2, answer);
                pstmt.setString(3, id);
                int nRes = pstmt.executeUpdate();
                return nRes;
            }

        } catch (SQLException sqle) {
            logger.error("faqupdate() : Got SQLException " + Utilities.getStackTrace(sqle));

        } catch (Exception e) {
            logger.error("faqupdate() : Got SQLException " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(rs, pstmt, objConn);
            }
        }
        return -1;
    }

}
