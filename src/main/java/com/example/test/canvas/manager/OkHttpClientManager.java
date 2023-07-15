//package com.example.test.canvas.manager;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.log4j.Log4j2;
//import okhttp3.Headers;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.UnknownHttpStatusCodeException;
//
//import java.io.File;
//import java.util.Objects;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.log4j.Log4j2;
//import okhttp3.Headers;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.UnknownHttpStatusCodeException;
//
//import java.io.File;
//import java.util.Objects;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
///**
// * The type Ok http client manager.
// */
//@Component
//@Log4j2
//public class OkHttpClientManager {
//
//    @Autowired
//    private TransformUtil transformUtil;
//
//    /**
//     * The Mapper.
//     */
//    @Autowired
//    private ObjectMapper mapper;
//
//    /**
//     * The Client.
//     */
//    @Autowired
//    private OkHttpClient client;
//
//    /**
//     * Get t.
//     *
//     * @param <T>               the type parameter
//     * @param baseUrl           the base url
//     * @param resourceUrl       the resource url
//     * @param query             the query
//     * @param headers           the headers
//     * @param responseClassType the response class type
//     * @return the t
//     * @throws Exception the exception
//     */
//    public <T> T get(final String baseUrl, final String resourceUrl, final String query,
//                     final Headers headers, final Class<T> responseClassType) throws Exception {
//
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            Request request = new Request.Builder().headers(headers).url(fullUrl).build();
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: get : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//            throw ex;
//        }
//        return null;
//    }
//
//    /**
//     * Post t.
//     *
//     * @param <T>               - The type parameter
//     * @param baseUrl           - The baseUrl
//     * @param resourceUrl       - The resourceUrl
//     * @param query             - The query
//     * @param jsonObject        - The jsonObject
//     * @param headers           - The headers
//     * @param responseClassType - The responseClassType
//     * @param <T>               - The T
//     * @return - <T>
//     */
//    public <T> T post(final String baseUrl, final String resourceUrl, final String query,
//                      final Object jsonObject, final Headers headers, final Class<T> responseClassType) throws Exception {
//
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            String bodyJson = null;
//            RequestBody requestBody = null;
//            if (Objects.nonNull(jsonObject)) {
//                bodyJson = transformUtil.toJson(jsonObject);
//                requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_VALUE), bodyJson);
//            } else {
//                requestBody = RequestBody.create(null, new byte[0]);
//            }
//            Request request = new Request.Builder().post(requestBody).headers(headers).url(fullUrl).build();
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: Post : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//            throw ex;
//        }
//        return null;
//    }
//
//    public <T> T postCommand(final String baseUrl, final String resourceUrl, final String query,
//                             final Object body, final Headers headers, final Class<T> responseClassType) throws Exception {
//
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            RequestBody requestBody = null;
//            String bodyJson = null;
//            if (body != null) {
//                bodyJson = transformUtil.toJson(body);
//                requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_VALUE), bodyJson);
//            } else {
//                requestBody = RequestBody.create(null, new byte[0]);
//            }
//
//            Request request = new Request.Builder().post(requestBody).headers(headers).url(fullUrl).build();
//
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: Post : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//            throw ex;
//        }
//        return null;
//    }
//
//    /**
//     * Post UrlEncoded t.
//     *
//     * @param baseUrl           - The baseUrl
//     * @param resourceUrl       - The resourceUrl
//     * @param query             - The query
//     * @param requestBody       - The requestBody
//     * @param requestOkHeaders  - The  requestOkHeaders
//     * @param responseClassType - The responseClassType
//     * @param <T>               - The type parameter
//     * @return - <T>
//     */
//    public <T> T postEncoded(final String baseUrl, final String resourceUrl, final String query,
//                             final RequestBody requestBody, final Headers requestOkHeaders,
//                             final Class<T> responseClassType) throws Exception {
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            Request request = new Request.Builder().post(requestBody).headers(requestOkHeaders).url(fullUrl).build();
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: postEncoded : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//            throw ex;
//        }
//        return null;
//    }
//
//    /**
//     * Post Form
//     *
//     * @param baseUrl           - The baseUrl
//     * @param resourceUrl       - The resourceUrl
//     * @param query             - The query
//     * @param mailTo            - The mailTo
//     * @param file              - The file
//     * @param filesKey          - The filesKey
//     * @param requestHeaders    - The requestHeaders
//     * @param responseClassType - The responseClassType
//     * @param <T>               - The type parameter
//     * @return - The <T>
//     * @throws Exception -
//     */
//    public <T> T postForm(final String baseUrl, final String resourceUrl, final String query,
//                          final String mailTo, final File file, final String filesKey,
//                          final Headers requestHeaders, final Class<T> responseClassType)
//            throws Exception {
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                    .addFormDataPart(Constants.EMAIL_SPOC, Constants.DEFAULT_TO)
//                    .addFormDataPart(filesKey, file.getName(),
//                            RequestBody.create(MediaType.parse("text/csv"), file)).build();
//            Request request = new Request.Builder().post(requestBody).headers(requestHeaders)
//                    .url(fullUrl).build();
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: postEncoded : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//            throw ex;
//        }
//        return null;
//    }
//
//    /**
//     * Put t.
//     *
//     * @param baseUrl           - The baseUrl
//     * @param resourceUrl       - The resourceUrl
//     * @param query             - The query
//     * @param body              - The body
//     * @param headers           - The headers
//     * @param responseClassType - The responseClassType
//     * @param <T>               - The type parameter
//     * @return - The <T>
//     * @throws Exception - Exception
//     */
//    public <T> T put(final String baseUrl, final String resourceUrl, final String query,
//                     final Object body, final Headers headers, final Class<T> responseClassType) throws Exception {
//
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            RequestBody requestBody = RequestBody.create(
//                    MediaType.parse("application/json"), body.toString());
//            Request request = new Request.Builder().put(requestBody).headers(headers).url(fullUrl).build();
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: Put : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * Put urlEncoded t.
//     *
//     * @param baseUrl           - The baseUrl
//     * @param resourceUrl       - The resourceUrl
//     * @param query             - The query
//     * @param requestBody       - The requestBody
//     * @param requestOkHeaders  - The  requestOkHeaders
//     * @param responseClassType - The responseClassType
//     * @param <T>               - The type parameter
//     * @return - The <T>
//     */
//    public <T> T putEncoded(final String baseUrl, final String resourceUrl, final String query,
//                            final RequestBody requestBody, final Headers requestOkHeaders,
//                            final Class<T> responseClassType) {
//        T responseEntity = null;
//        try {
//            String fullUrl = getFullUrl(baseUrl, resourceUrl, query);
//            log.info("full URL: " + fullUrl);
//            Request request = new Request.Builder().put(requestBody).headers(requestOkHeaders).url(fullUrl).build();
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                responseEntity = mapper.readValue(response.body().string(), responseClassType);
//                return responseEntity;
//            } else {
//                handleException(response, HttpStatus.valueOf(response.code()));
//            }
//        } catch (Exception ex) {
//            log.error("Error in OkHttpClientManager: putEncoded : {} ; Exception : {}", responseEntity,
//                    ex.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * Handle exception.
//     *
//     * @param response   the response
//     * @param statusCode the status code
//     * @throws Exception the exception
//     */
//    private void handleException(final Response response, final HttpStatus statusCode) throws Exception {
//        String statusText = String.valueOf(response.code());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        String message = response.body().string();
//        byte[] body = response.body().toString().getBytes();
//        switch (statusCode.series()) {
//            case CLIENT_ERROR:
//                throw HttpClientErrorException.create(message, statusCode, statusText, httpHeaders,
//                        body, null);
//            case SERVER_ERROR:
//                throw HttpServerErrorException.create(message, statusCode, statusText, httpHeaders,
//                        body, null);
//            default:
//                throw new UnknownHttpStatusCodeException(message, statusCode.value(), statusText,
//                        httpHeaders, body, null);
//        }
//    }
//
//    /**
//     * Gets full url.
//     *
//     * @param baseUrl the base url
//     * @param url     the url
//     * @param query   the query
//     * @return the full url
//     */
//    private String getFullUrl(final String baseUrl, final String url, final String query) {
//        StringBuilder fullUrl = new StringBuilder();
//        fullUrl.append(baseUrl);
//        if (url != null) {
//            fullUrl.append(url);
//        }
//        String finalQuery = query;
//        if (finalQuery != null && finalQuery.startsWith("?")) {
//            finalQuery = finalQuery.substring(1);
//        }
//        finalQuery = StringUtils.trimToNull(finalQuery);
//        if (finalQuery != null) {
//            fullUrl.append("?");
//            fullUrl.append(finalQuery);
//        }
//        return fullUrl.toString();
//    }
//}
