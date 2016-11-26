package com.common;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author pavankumar.g
 */
public class HttpDispatchClient extends AsyncHttpClient {

//    private Log log = LogFactory.getLog(HttpDispatchClient.class);
    private static final Logger logger = Logger.getLogger(HttpDispatchClient.class);

    private static HttpDispatchClient httpClient = null;

    private HttpDispatchClient(AsyncHttpClientConfig config) {
        super(config);
    }

    public static HttpDispatchClient getInstance() {

        if (httpClient == null) {
            int maxConTotal = Integer.parseInt(ConfigUtil.getProperty("maximumConnectionsTotal", "0"));
            int timeout = Integer.parseInt(ConfigUtil.getProperty("requestTimeoutInMs", "0"));
            int maxConPerHost = Integer.parseInt(ConfigUtil.getProperty("maxConnectionsPerHost", "0"));

            if (!(maxConPerHost > 0)) {
                String strmaxConnectionsPerHost = ConfigUtil.getProperty("maxConnectionsPerHost");
                if (strmaxConnectionsPerHost == null) {
                    strmaxConnectionsPerHost = "500";
                }
                maxConPerHost = Integer.parseInt(strmaxConnectionsPerHost.trim());
            } else {
                maxConPerHost = maxConPerHost;
            }

            if (!(maxConTotal > 0)) {

                String strmaximumConnectionsTotal = ConfigUtil.getProperty("maximumConnectionsTotal");
                if (strmaximumConnectionsTotal == null) {
                    strmaximumConnectionsTotal = "30000";
                }
                maxConTotal = Integer.parseInt(strmaximumConnectionsTotal.trim());
            } else {
                maxConTotal = maxConTotal;
            }
            if (!(timeout > 0)) {

                timeout = Integer.parseInt(ConfigUtil.getProperty("requestTimeoutInMs", "60000").trim());
            } else {
                timeout = timeout;
            }

            AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
            builder.setMaximumConnectionsPerHost(maxConPerHost);
            builder.setMaximumConnectionsTotal(maxConTotal);
            builder.setRequestTimeoutInMs(timeout);
            AsyncHttpClientConfig config = builder.build();

            return new HttpDispatchClient(config);
        }
        return httpClient;
    }

    /**
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public Response Get(String strURL, Map<String, String> headers) throws IOException, Exception {

        if (StringUtils.isBlank(strURL)) {
            logger.error("URL Is Empty.");
            return null;
        }

        FluentCaseInsensitiveStringsMap headersMap = new FluentCaseInsensitiveStringsMap();
        Future<Response> future = null;
        RequestBuilder requestBuilder = new RequestBuilder();

        requestBuilder.setMethod("GET");
        requestBuilder.setUrl(strURL);

        if (logger.isInfoEnabled()) {
            logger.info("GET to : \n " + strURL + " at " + new Date());
        }

        if (headers != null) {

            if (logger.isInfoEnabled()) {
                logger.info("\nAnd Headers are : \n " + headers.toString());
            }
            Set<Entry<String, String>> entrySet = headers.entrySet();
            for (Entry<String, String> entry : entrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                headersMap.add(key, value);
            }
            requestBuilder.setHeaders(headersMap);
        }

        Request request = requestBuilder.build();
        try {
            future = executeRequest(request);
            Response resp = future.get();
            return resp;
        } catch (IOException ioe) {
            throw new IOException(ioe.getMessage());
        } catch (Exception e) {
            logger.error(Utilities.getStackTrace(e));
            throw new Exception(e.getMessage());
        }
    }
}
