/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author chhavikumar.b
 */
import com.beans.Content;
import com.beans.SaveDish;
import com.beans.User;
import static com.common.ResponseCodes.ServiceErrorCodes.GENERIC_ERROR;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_JSON;
import com.common.Utilities;
import com.google.gson.JsonSyntaxException;
import com.service.UserService;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {

    private static final String template = "Welcome, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private ServletContext servletContext;
    private static UserService objUserService = null;

    LoginController() {
        try {
            if (objUserService == null) {
                objUserService = new UserService();
            }

        } catch (Exception e) {
            logger.error("Exception in UserService(),ex:" + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        HttpSession session = request.getSession();
        session.invalidate();
        model.setViewName("index");
        return new RedirectView("", true);

    }

    @RequestMapping(value = "/add_book", method = RequestMethod.GET)
    public Object add_book(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("add_book");
        return model;

    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public Object home(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        return model;

    }

    @RequestMapping(value = "/edit_rss_content", method = RequestMethod.GET)
    public Object editrestaurant(HttpServletRequest request, @RequestParam("id") String id) {

        ModelAndView model = new ModelAndView();
        JSONObject strResponse = objUserService.edit_rss_content(id);
        HttpSession session = request.getSession();
        session.setAttribute("rssdet", strResponse.toString());
        model.setViewName("edit_rss_content");
        return model;

    }

    @RequestMapping(value = "/addcontent", method = RequestMethod.POST, produces = {"application/json"})
    public String addcontent(@RequestBody String strJSON, HttpSession httpSession) {
        String response = "";
        try {
            Content objContent = Utilities.fromJson(strJSON, Content.class);
            response = objUserService.addcontent(objContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/edit_content", method = RequestMethod.POST, produces = {"application/json"})
    public String edit_content(@RequestBody String strJSON, HttpSession httpSession) {
        String response = "";
        try {
            Content objContent = Utilities.fromJson(strJSON, Content.class);
            response = objUserService.edit_content(objContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/list_book", method = RequestMethod.GET)
    public Object list_book(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("list_book");
        return model;

    }

    @RequestMapping(value = "/get_single_upload_list", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json"})
    public String get_single_upload_list(@RequestParam("page") int page, @RequestParam("rows") int endIndex) {
        String strTid = UUID.randomUUID().toString();
        String strResult = null;
        try {
            int fromIndex = 0;
            if (page > 0) {
                fromIndex = (page - 1) * endIndex;
            }
            JSONObject json = new JSONObject();
            JSONArray obj = objUserService.get_single_upload_list(strTid, fromIndex, endIndex);
            json.put("total", objUserService.get_single_upload_listCount(strTid));
            json.put("page", page);
            json.put("records", obj.length());
            json.put("rows", obj);
            return json.toString();
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), strTid);
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    @RequestMapping(value = "/delete_single_upload", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String delete_single_upload(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.delete_single_upload(id, transId);

        return strResponse;
    }

    @RequestMapping(value = "/getcategory", method = RequestMethod.GET)
    public String getcategory(HttpServletRequest httpreq) {
        String transId = UUID.randomUUID().toString();
        String objPropertyType = null;
        try {
            objPropertyType = objUserService.getcategory(transId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objPropertyType;
    }

    @RequestMapping(value = "/get_sub_category", method = RequestMethod.GET)
    public String get_sub_category(HttpServletRequest httpreq, @RequestParam(value = "id", required = false) String id) {
        String transId = UUID.randomUUID().toString();
        String objPropertyType = null;
        try {
            objPropertyType = objUserService.get_sub_category(id, transId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objPropertyType;
    }

    @RequestMapping(value = "/add_bulk_upload", method = RequestMethod.GET)
    public Object add_bulk_upload(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("add_bulk_upload");
        return model;

    }
    
    @RequestMapping(value = "/edit_book", method = RequestMethod.GET)
    public Object edit_book(HttpServletRequest request, @RequestParam("id") String id) {

        ModelAndView model = new ModelAndView();
        JSONObject strResponse = objUserService.edit_book(id);
        HttpSession session = request.getSession();
        session.setAttribute("restdet", strResponse.toString());
        model.setViewName("edit_book");
        return model;

    }

    @RequestMapping(value = "/edit_bulk_upload", method = RequestMethod.GET)
    public Object edit_bulk_upload(HttpServletRequest request, @RequestParam("id") String id) {

        ModelAndView model = new ModelAndView();
        JSONObject strResponse = objUserService.edit_bulk_upload(id);
        HttpSession session = request.getSession();
        session.setAttribute("restdet", strResponse.toString());
        model.setViewName("edit_bulk_upload");
        return model;

    }

    @RequestMapping(value = "/add_bulk_content", method = RequestMethod.POST, produces = {"application/json"})
    public String add_bulk_content(@RequestBody String strJSON, HttpSession httpSession) {
        String response = "";
        try {
            Content objContent = Utilities.fromJson(strJSON, Content.class);
            response = objUserService.add_bulk_content(objContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/edit_bulk_content", method = RequestMethod.POST, produces = {"application/json"})
    public String edit_bulk_content(@RequestBody String strJSON, HttpSession httpSession) {
        String response = "";
        try {
            Content objContent = Utilities.fromJson(strJSON, Content.class);
            response = objUserService.edit_bulk_content(objContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/list_bulk_upload", method = RequestMethod.GET)
    public Object list_bulk_upload(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("list_bulk_upload");
        return model;

    }

    @RequestMapping(value = "/get_bulk_upload_list", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json"})
    public String get_bulk_upload_list(@RequestParam("page") int page, @RequestParam("rows") int endIndex) {
        String strTid = UUID.randomUUID().toString();
        String strResult = null;
        try {
            int fromIndex = 0;
            if (page > 0) {
                fromIndex = (page - 1) * endIndex;
            }
            JSONObject json = new JSONObject();
            JSONArray obj = objUserService.get_bulk_upload_list(strTid, fromIndex, endIndex);
            json.put("total", objUserService.get_bulk_upload_listCount(strTid));
            json.put("page", page);
            json.put("records", obj.length());
            json.put("rows", obj);
            return json.toString();
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), strTid);
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid);
        }
    }

    @RequestMapping(value = "/delete_bulk_upload", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String delete_bulk_upload(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.delete_bulk_upload(id, transId);
        return strResponse;
    }

    @RequestMapping(value = "/list_rss_feed", method = RequestMethod.GET)
    public Object list_rss_feed(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("list_rss_feed");
        return model;

    }

    @RequestMapping(value = "/get_list_rss_feed", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody byte[] get_list_rss_feed(@RequestParam(value = "catid", required = false) String catid, @RequestParam("page") int page, @RequestParam("rows") int endIndex) throws UnsupportedEncodingException {
        String strTid = UUID.randomUUID().toString();
        String strResult = null;
        try {
            int fromIndex = 0;
            if (page > 0) {
                fromIndex = (page - 1) * endIndex;
            }
            JSONObject json = new JSONObject();
            JSONArray obj = objUserService.get_list_rss_feed(catid, strTid, fromIndex, endIndex);
            json.put("total", objUserService.get_list_rss_feedCount(catid, strTid));
            json.put("page", page);
            json.put("records", obj.length());
            json.put("rows", obj);
            return json.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), strTid).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), strTid).getBytes("UTF-8");
        }
    }

    @RequestMapping(value = "/getcategorylist", method = RequestMethod.GET)
    public String getcategorylist(HttpServletRequest httpreq) {
        String transId = UUID.randomUUID().toString();
        String objPropertyType = null;
        try {
            objPropertyType = objUserService.getsubcategory(transId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objPropertyType;
    }

    @RequestMapping(value = "/add_rss_feed", method = RequestMethod.GET)
    public Object add_rss_feed(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("add_rss_feed");
        return model;
    }

    @RequestMapping(value = "/rss_feed_insert", method = RequestMethod.POST, produces = {"application/json"})
    public ModelAndView rss_feed_insert(@RequestParam("file[]") MultipartFile[] files, @RequestParam(value = "sub_category_id", required = false) String sub_category_id, @RequestParam(value = "description", required = false) String description, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = "";
        InputStream Filename = null;
        try {

            for (MultipartFile multipartfile : files) {
                if (multipartfile != null && multipartfile.getOriginalFilename() != null && multipartfile.getOriginalFilename() != "") {
                    Filename = multipartfile.getInputStream();
                }
            }
            strResponse = objUserService.rss_feed_insert(Filename, sub_category_id, description, transId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/list_rss_feed");
    }

    @RequestMapping(value = "/user/adminsignin", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String adminSignin(@RequestBody User requestBean, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        User strResponse = objUserService.adminLoginDetails(requestBean, transId);
        if (strResponse != null && strResponse.getErrorMessage().contains("SUCCESS")) {
            httpSession.setAttribute("token", transId);
            httpSession.setAttribute("useradmin", strResponse);
        }
        return strResponse.getErrorMessage();
    }
}