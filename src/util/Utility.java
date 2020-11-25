/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

/**
 *
 * @author Juan
 */
public class Utility {

    private static Pattern pattern;
    private static Matcher matcher;
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_FORMAT_STRING_SHORT = "yyyy-MM-dd";
    //public static final String GOOGLE_MAPS_API_KEY = "AIzaSyAhOv2MNrliIM1BrBBOdgzD2Fip1rzUkHQ";
    //public static final String GOOGLE_MAPS_API_KEY = "AIzaSyDplHxaH2ApyLhDUqCzN9gPfHfav7z3ykM";
    public static final String GOOGLE_MAPS_API_KEY = "AIzaSyCV4nZf5KfXy2ImAOqfHzxj_JBbBpCnGU4";
    public static final String LOGIN_PATH = "/login";
    public static final String USER_PATH = "/user";
    //public static final String USER_RETRIEVE_PATH = "/user/retrieve";
    public static final String USER_BLOCK_PATH = "/user/block";
    public static final String SERVICE_CREATE_PATH = "/service";
    public static final String TOWER_CREATE_PATH = "/tower";
    public static final String TOWER_BY_EMAIL_PATH = "/tower/email";
    public static final String USER_CREATE_PATH = "/user";
    //public static final String USER_ID_BY_EMAIL_PATH = "/user/email/id";
    public static final String TOWER_PATH = "/tower";
    public static final String CLIENT_BY_EMAIL_PATH = "/client/email";
    public static final String USER_BY_EMAIL_PATH = "/user/email";
    public static final String BASE_URL = "http://localhost:8080/QicFixSystem/api";
    //public static final String BASE_URL = "http://www.qicfixit.com:8080/api";
    public static final String ACCEPT_REQUEST_PATH = "/hasTower/accept";
    public static final String DECLINE_REQUEST_PATH = "/hasTower/decline";
    public static final String CLIENT_CREATE_PATH = "/client";
    public static final String LOGOUT_PATH = "/logout";
    public static final String APPLICATION_PATH = "/application";
    public static final String USER_EMAIL_EXISTS_PATH = "/user/exists";
    public static final String SERVICE_CLIENT_PATH = "/service/client";
    
    //Parse a String to Date
    public static Date StringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        Date date = null;

        try {
            if(!strDate.equals("")){
            date = format.parse(strDate);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    //Parse a Date Type to String
    public static String ConvertirDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        String strDate = null;

        try {
            strDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strDate;
    }
    
    //Minimum Password Length 4 and cannot contain spaces
    public static boolean validatePassFormat(String pass) {
        boolean resp = true;

        if ((pass.length() < 4 || pass.contains(" "))) {
            resp = false;
        }

        return resp;
    }
    
    //Email format should be: letters@letters.letters
    public static boolean validateEmailFormat(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Translates from "222 SW 82ND ST, Miami FL, 33143" to Location(83.11115,-83.14545)
    public static Location getLocationFromAddress(String address) throws Exception {
        Location location = null;
        String[] locationPickup = getLatLongPositions(address);
        Double latitude = Double.parseDouble(locationPickup[0]);
        Double longitude = Double.parseDouble(locationPickup[1]);
        location = new Location(latitude, longitude);
        System.out.println("Address: " + address + " - location: " + latitude + "," + longitude);
        return location;
    }
    
    //Translates from "222 SW 82ND ST, Miami FL, 33143" to String[] = "83.11115,-83.14545"
    public static String[] getLatLongPositions(String address) throws Exception {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true&key=" + GOOGLE_MAPS_API_KEY;
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = (Document) builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/GeocodeResponse/status");
            String status = (String) expr.evaluate(document, XPathConstants.STRING);
            if (status.equals("OK")) {
                expr = xpath.compile("//geometry/location/lat");
                String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
                expr = xpath.compile("//geometry/location/lng");
                String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
                return new String[]{latitude, longitude};
            } else {
                throw new Exception("Error from the API - response status: " + status);
            }
        }
        return null;
    }

    public static String getAddressByLocation(Location location) {
        try {
            int responseCode = 0;
            String api = "http://maps.googleapis.com/maps/api/geocode/xml?location=" + URLEncoder.encode(location.getLatitude() +","+ location.getLongitude(), "UTF-8") + "&sensor=true&key=" + GOOGLE_MAPS_API_KEY;
            URL url = new URL(api);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.connect();
            responseCode = httpConnection.getResponseCode();
            if (responseCode == 200) {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = (Document) builder.parse(httpConnection.getInputStream());
                XPathFactory xPathfactory = XPathFactory.newInstance();
                XPath xpath = xPathfactory.newXPath();
                XPathExpression expr = xpath.compile("/GeocodeResponse/status");
                String status = (String) expr.evaluate(document, XPathConstants.STRING);
                if (status.equals("OK")) {
                    expr = xpath.compile("//geometry/location/lat");
                    String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
                    expr = xpath.compile("//geometry/location/lng");
                    String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
                    return latitude+","+longitude;
                } else {
                    throw new Exception("Error from the API - response status: " + status);
                }
            }
            return null;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    //Deserializer of Type Date in Json
    public static class JsonDateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
            try {
                return df.parse(json.getAsString());
            } catch (final java.text.ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    
    //Function to Build Custom HTTP Responses
    public static Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setMaxAge(-1);
        cc.setMustRevalidate(true);
        return Response.status(status).cacheControl(cc);
    }

    /*
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distanceWithHeight(double lat1, double lat2, double lon1,
            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = distance * 3.28084; // convert to feet

        double height = (el1 - el2) * 3.28084;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
