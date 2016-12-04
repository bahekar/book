/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.beans.RssBean;
import static com.common.ResponseCodes.ServiceErrorCodes.GENERIC_ERROR;
import static com.common.ResponseCodes.ServiceErrorCodes.INVALID_JSON;
import com.common.Utilities;
import com.google.gson.JsonSyntaxException;
import com.service.CategoryService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author chhavikumar.b
 */
@RestController
@EnableWebMvc
public class CategoryController {

    private static final String template = "Welcome, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private ServletContext servletContext;
    private static CategoryService objUserService = null;

    CategoryController() {
        try {
            if (objUserService == null) {
                objUserService = new CategoryService();
            }

        } catch (Exception e) {
            logger.error("Exception in UserService(),ex:" + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public Object category(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("category");
        return model;
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public Object add_category(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("add-category");
        return model;
    }

    @RequestMapping(value = "/categorysave", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String categorysave(@RequestParam(value = "category", required = false) String category, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.addCategory(category, transId);
        return strResponse;
    }

    @RequestMapping(value = "/categorylist", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json"})
    public String categorylist(@RequestParam("page") int page,
            @RequestParam("rows") int endIndex) {
        String strTid = UUID.randomUUID().toString();
        String strResult = null;
        try {
            int fromIndex = 0;
            if (page > 0) {
                fromIndex = (page - 1) * endIndex;
            }
            JSONObject json = new JSONObject();
            JSONArray obj = objUserService.categorylist(strTid, fromIndex, endIndex);
            json.put("total", objUserService.categorylistCount(strTid));
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

    @RequestMapping(value = "/editCategory", method = RequestMethod.GET)
    public Object editCategory(HttpServletRequest request, @RequestParam(value = "id", required = false) String id) {

        ModelAndView model = new ModelAndView();
        String transId = UUID.randomUUID().toString();
        model.setViewName("edit-category");
        HttpSession session = request.getSession();
        JSONObject strResponse = objUserService.getCategory_details(transId, id);
        try {
            session.setAttribute("category_name", strResponse.get("name"));
            // session.setAttribute("id", strResponse.get("id"));
            //  session.setAttribute("description", strResponse.get("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/categoryupdate", method = RequestMethod.GET, produces = {"application/json"})
    public String categoryupdate(@RequestParam("category") String category, @RequestParam("id") String id, HttpSession httpSession) {
        String response = "";
        try {
            response = objUserService.categoryupdate(category, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/sub_category", method = RequestMethod.GET)
    public Object sub_category(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("sub_category");
        return model;

    }

    @RequestMapping(value = "/add_sub_category", method = RequestMethod.GET)
    public Object add_sub_category(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.setViewName("add_sub_category");
        return model;

    }

    @RequestMapping(value = "/edit_sub_category", method = RequestMethod.GET)
    public Object edit_sub_category(HttpServletRequest request, @RequestParam(value = "id", required = false) String id) {

        ModelAndView model = new ModelAndView();
        String transId = UUID.randomUUID().toString();
        model.setViewName("edit_sub_category");
        HttpSession session = request.getSession();
        JSONObject strResponse = objUserService.get_sub_category_details(transId, id);
        try {
            session.setAttribute("sub_category_name", strResponse.get("name"));
            session.setAttribute("category_id", strResponse.get("id"));
            //  session.setAttribute("description", strResponse.get("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;

    }

    @RequestMapping(value = "/sub_category_list", method = {RequestMethod.GET, RequestMethod.POST})
    public String sub_category_list(@RequestParam("page") int page, @RequestParam("rows") int endIndex) throws UnsupportedEncodingException {
        String strTid = UUID.randomUUID().toString();
        String strResult = null;
        try {
            int fromIndex = 0;
            if (page > 0) {
                fromIndex = (page - 1) * endIndex;
            }
            JSONObject json = new JSONObject();
            JSONArray obj = objUserService.sub_category_list(strTid, fromIndex, endIndex);
            json.put("total", objUserService.sub_category_listCount(strTid));
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

    @RequestMapping(value = "/sub_category_insert", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String sub_category_insert(@RequestParam(value = "sub_category", required = false) String sub_category, @RequestParam(value = "category_id", required = false) String category_id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.sub_category_insert(sub_category, category_id, transId);

        return strResponse;
    }

    @RequestMapping(value = "/sub_category_update", method = RequestMethod.GET, produces = {"application/json"})
    public String sub_category_update(@RequestParam("sub_category") String sub_category, @RequestParam("id") String id, @RequestParam(value = "category_id", required = false) String category_id, HttpSession httpSession) {
        String response = "";
        try {
            response = objUserService.sub_category_update(sub_category, id, category_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/delete_category", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String delete_category(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.delete_category(id, transId);

        return strResponse;
    }

    @RequestMapping(value = "/delete_sub_category", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String delete_sub_category(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.delete_sub_category(id, transId);

        return strResponse;
    }

    @RequestMapping(value = "/api/categorylist", method = RequestMethod.GET)
    public @ResponseBody
    byte[] categorylist(HttpSession httpSession) throws UnsupportedEncodingException {
        JSONArray strResult = null;
        String transId = UUID.randomUUID().toString();

        try {
            JSONObject objRequest = new JSONObject();
            objRequest.put("code", "0");
            objRequest.put("description", "success");
            strResult = objUserService.getcategory(transId);

            objRequest.put("categorylist", strResult);
            return objRequest.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId).getBytes("UTF-8");
        }
    }

    @RequestMapping(value = "/api/subcategorylist", method = RequestMethod.GET)
    public @ResponseBody
    byte[] subcategorylist(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) throws UnsupportedEncodingException {
        JSONArray strResult = null;
        String transId = UUID.randomUUID().toString();

        try {
            JSONObject objRequest = new JSONObject();
            objRequest.put("code", "0");
            objRequest.put("description", "success");
            strResult = objUserService.getsubcategory(id);

            objRequest.put("subcategorylist", strResult);
            return objRequest.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId).getBytes("UTF-8");
        }
    }

    @RequestMapping(value = "/delete_rss_feed", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String delete_rss_feed(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.delete_rss_feed(transId, id);

        return strResponse;
    }

    @RequestMapping(value = "/rss_feed_update", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String rss_feed_update(@RequestBody String strJSON, HttpSession httpSession) {
        RssBean rss = null;
        String strResponse = null;
        String transId = UUID.randomUUID().toString();
        try {
            rss = Utilities.fromJson(strJSON, RssBean.class);
            strResponse = objUserService.rss_feed_update(rss);

        } catch (JsonSyntaxException je) {
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId);
        } catch (Exception e) {
            logger.error("Exception in signup(),ex:" + e.getMessage(), e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId);
        }
        return strResponse;
    }

    @RequestMapping(value = "/list_faq", method = RequestMethod.GET)
    public Object list_faq(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("list_faq");
        return model;
    }

    @RequestMapping(value = "/add_faq", method = RequestMethod.GET)
    public Object add_faq(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("add_faq");
        return model;
    }

    @RequestMapping(value = "/faqlist", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json"})
    public String faqlist(@RequestParam("page") int page,
            @RequestParam("rows") int endIndex) {
        String strTid = UUID.randomUUID().toString();
        String strResult = null;
        try {
            int fromIndex = 0;
            if (page > 0) {
                fromIndex = (page - 1) * endIndex;
            }
            JSONObject json = new JSONObject();
            JSONArray obj = objUserService.faqlist(strTid, fromIndex, endIndex);
            json.put("total", objUserService.faqlistCount(strTid));
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

    @RequestMapping(value = "/faqsave", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String faqsave(@RequestParam(value = "question", required = false) String question, @RequestParam(value = "answer", required = false) String answer, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.faqsave(question, answer, transId);
        return strResponse;
    }

    @RequestMapping(value = "/delete_faq", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/json"})
    public String delete_faq(@RequestParam(value = "id", required = false) String id, HttpSession httpSession) {
        String transId = UUID.randomUUID().toString();
        String strResponse = objUserService.delete_faq(id, transId);

        return strResponse;
    }

    @RequestMapping(value = "/edit_faq", method = RequestMethod.GET)
    public Object edit_faq(HttpServletRequest request, @RequestParam(value = "id", required = false) String id) {

        ModelAndView model = new ModelAndView();
        String transId = UUID.randomUUID().toString();
        model.setViewName("edit_faq");
        HttpSession session = request.getSession();
        JSONObject strResponse = objUserService.getFAQ_details(transId, id);
        try {
            session.setAttribute("question", strResponse.get("question"));
            session.setAttribute("answer", strResponse.get("answer"));
            // session.setAttribute("id", strResponse.get("id"));
            //  session.setAttribute("description", strResponse.get("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/faqupdate", method = RequestMethod.GET, produces = {"application/json"})
    public String faqupdate(@RequestParam("question") String question, @RequestParam("answer") String answer, @RequestParam("id") String id, HttpSession httpSession) {
        String response = "";
        try {
            response = objUserService.faqupdate(question, answer, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/api/getbooks", method = RequestMethod.GET)
    public @ResponseBody
    byte[] getbooks(@RequestParam(value = "language", required = false) String language, HttpSession httpSession) throws UnsupportedEncodingException {
        JSONObject strResult = null;
        String transId = UUID.randomUUID().toString();

        try {
//            JSONObject objRequest = new JSONObject();
//            objRequest.put("code", "0");
//            objRequest.put("description", "success");
            strResult = objUserService.getbooks(language);
//
//            objRequest.put("bookslist", strResult);
            return strResult.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId).getBytes("UTF-8");
        }
    }

    @RequestMapping(value = "/api/getauthors", method = RequestMethod.GET)
    public @ResponseBody
    byte[] getAuthors(HttpSession httpSession) throws UnsupportedEncodingException {
        JSONArray strResult = null;
        String transId = UUID.randomUUID().toString();

        try {
//            JSONObject objRequest = new JSONObject();
//            objRequest.put("code", "0");
//            objRequest.put("description", "success");
            strResult = objUserService.getAuthors();

//            objRequest.put("authorslist", strResult);
            return strResult.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId).getBytes("UTF-8");
        }
    }

    @RequestMapping(value = "/api/getphotos", method = RequestMethod.GET)
    public @ResponseBody
    byte[] getphotos(HttpSession httpSession) throws UnsupportedEncodingException {
        JSONArray strResult = null;
        String transId = UUID.randomUUID().toString();

        try {
//            JSONObject objRequest = new JSONObject();
//            objRequest.put("code", "0");
//            objRequest.put("description", "success");
            strResult = objUserService.getphotos();

//            objRequest.put("photoslist", strResult);
            return strResult.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId).getBytes("UTF-8");
        }
    }

    @RequestMapping(value = "/api/getContent", method = RequestMethod.GET)
    public @ResponseBody
    byte[] getContent(@RequestParam(value = "type", required = false) String type, HttpSession httpSession) throws UnsupportedEncodingException {
        JSONArray strResult = null;
        String transId = UUID.randomUUID().toString();

        try {
//            JSONObject objRequest = new JSONObject();
//            objRequest.put("code", "0");
//            objRequest.put("description", "success");
            strResult = objUserService.getContent(type);

//            objRequest.put("contentlist", strResult);
            System.out.println("svn test");
            return strResult.toString().getBytes("UTF-8");
        } catch (JsonSyntaxException e) {
            logger.error(e);
            return Utilities.prepareReponse(INVALID_JSON.getCode(), INVALID_JSON.DESC(), transId).getBytes("UTF-8");
        } catch (Exception e) {
            logger.error(e);
            return Utilities.prepareReponse(GENERIC_ERROR.getCode(), GENERIC_ERROR.DESC(), transId).getBytes("UTF-8");
        }
    }

}
