/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;


/**
 *
 * @author chhavikumar.b
 */
public class SearchResult<T> {

    /**
     * Total number of pages
     */
    private String total;
    /**
     * The current page number
     */
    private int page;
    /**
     * Total number of records
     */
    private int records;
    /**
     * The actual data
     */
    private List<T> rows;
    /**
     * To add any additional Properties.
     */
    private Map additionalProp;
    /**
     * For dynamic columns. {"columns":[ {"key":"elementKey", "keyTitle":"custom
     * Title"}, {"key":"elementKey", "keyTitle":"custom Title"},
     * {"key":"elementKey", "keyTitle":"custom Title"}, {"key":"elementKey",
     * "keyTitle":"custom Title"} ] }
     */
    private JSONObject columns;

    public Map getAdditionalProp() {
        return additionalProp;
    }

    public void setAdditionalProp(Map additionalProp) {
        this.additionalProp = additionalProp;
    }

    public SearchResult() {
    }

    public SearchResult(String total, int page, int records, List<T> rows) {
        this.total = total;
        this.page = page;
        this.records = records;
        this.rows = rows;
    }

    public String getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getRecords() {
        return records;
    }

    public List<T> getRows() {
        return rows;
    }

//    @Override
//    public String toString() {
//       // return "SearchResult{" + "total=" + total + ", page=" + page + ", records=" + records + ", rows=" + rows + '}';
////        JSONObject obj = new JSONObject();
////        obj.put("total", total);
////        obj.put("page", page);
////        obj.put("records", records);
////        JSONArray rowObj = new JSONArray();
////        obj.put("rows", rowObj);
////        
////        if(rows!=null){
////            for(T t:rows){
////                if(t instanceof BasicDynaBean){
////                    BasicDynaBean d = (BasicDynaBean)t;
////                    Map m = d.getMap();
////                    JSONObject o = new JSONObject(m);
////                    rowObj.put(o);
////                }
////            }
////        }
//        
////        return obj.toString();
//    }
    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(int records) {
        this.records = records;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public JSONObject getColumns() {
        return columns;
    }

    public void setColumns(JSONObject columns) {
        this.columns = columns;
    }
}

