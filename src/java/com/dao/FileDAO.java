package com.dao;


import com.common.DBConnection;
import com.common.Utilities;
import static com.dao.UserDAO.logger;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class FileDAO {

    DBConnection dbconnection = null;

    public FileDAO() {
        dbconnection = DBConnection.getInstance();
    }

    public void save(MultipartFile document,String path,String property_id) throws SQLException, IOException {
        System.out.println("dbconnection******" + dbconnection);
        String INSERT_PICTURE = "insert into images(name, contentType,filename,image_link,property_id) values (?,?,?,?,?)";

        PreparedStatement ps = null;
        Connection objConn = null;
        try {
            objConn = dbconnection.getConnection();
            objConn.setAutoCommit(false);
            ps = objConn.prepareStatement(INSERT_PICTURE);
            ps.setString(1, document.getName());
            ps.setString(2, document.getContentType());
            ps.setString(3, document.getOriginalFilename());
            ps.setString(4, path);
            ps.setString(5, property_id);
            ps.executeUpdate();
            objConn.commit();
        } catch (SQLException sqle) {
            logger.error("FileDAO: save " + Utilities.getStackTrace(sqle));
            throw new SQLException(sqle.getMessage());
        } catch (Exception e) {
            logger.error("FileDAO: save : " + Utilities.getStackTrace(e));

        } finally {
            if (objConn != null) {
                dbconnection.closeConnection(null, ps, objConn);
            }
        }
    }


//    public List<File> list() throws SQLException {
//        PreparedStatement pstmt = null;
//        Connection objConn = null;
//        List<File> objFile = new ArrayList<File>();
//        ResultSet rs = null;
//        try {
//            String selectQuery = ConfigUtil.getProperty("user.image.query", "select * from images");
//            objConn = dbconnection.getConnection();
//            pstmt = objConn.prepareStatement(selectQuery);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                File file = new File();
//                file.setId(rs.getInt("id"));
//                file.setName(rs.getString("NAME"));
//                file.setContentType(rs.getString("contentType"));
//                file.setFilename(rs.getString("filename"));
//                file.setContent(rs.getBlob("image"));
//                objFile.add(file);
//            }
//        } catch (SQLException sqle) {
//            logger.error("FileDAO: save " + Utilities.getStackTrace(sqle));
//
//        } catch (Exception e) {
//            logger.error("FileDAO: save : " + Utilities.getStackTrace(e));
//
//        } finally {
//            pstmt.close();
//
//        }
//        return objFile;
//    }

    public File get(Integer id) {
        return null;
    }

    @Transactional
    public void remove(Integer id) {

    }
}
