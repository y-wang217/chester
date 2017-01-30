package Tracking.user.action;

import Tracking.user.DAO.TrackingDao;
import Tracking.user.util.Status;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yale on 23/09/16.
 */
public class UserAction {
    public static String upload(String user, InputStream photo){
        //sql statement to 1. check if user exists, 2. see if password matches
        String stmt = "INSERT INTO signatures (user, photo) values (?, ?)";

        //use trackingDao to verify login
        TrackingDao dao = new TrackingDao();
        dao.createConnection();
        int uploadResult = dao.executeUpdateWithPhoto(stmt, user, photo);

        if(uploadResult != 1){
            //something went wrong with the insert
            return Status.FAIL_MSG+" something went wrong with upload ";

        }else{
            return Status.SUCC_MSG;

        }
    }

    public static String login(String user, String pwd){
        //sql statement to 1. check if user exists, 2. see if password matches
        String stmt = "SELECT pwd from users where user = '" + user + "';";

        //use trackingDao to verify login
        TrackingDao dao = new TrackingDao();
        dao.createConnection();
        ResultSet rs = dao.executeQuery(stmt);

        ResultSet pwdSet = dao.executeQuery("select MD5('"+pwd+"')");

        try{
            if(rs.next()){
                //compare password given to password on file
                String hashedPwd = "";
                if(pwdSet.next()) {
                    hashedPwd = pwdSet.getString(1);
                }
                if(hashedPwd.equals(rs.getString(1))){
                    return Status.SUCC_MSG;
                }else{
                    return "Password incorrect. ";
                }
            }else{
                return "User does not exist. Please register.";
            }
        }catch (Exception e){
            e.printStackTrace();
            return Status.FAIL_MSG+" Sql exception" + e.getMessage();
        }
    }

    public static String register(String user, String pwd){
        //sql statement to 1. check if user exists, 2. see if password matches
        String stmt = "SELECT pwd from users where user = '" + user + "';";

        //use trackingDao to verify login
        TrackingDao dao = new TrackingDao();
        dao.createConnection();
        ResultSet rs = dao.executeQuery(stmt);

        try{
            if(!rs.next()){
                String insert_stmt = "INSERT INTO users ('"+user+"', MD5('"+pwd+"'), '','','','');";
                int insertResult = dao.executeUpdate(insert_stmt);
                if(insertResult != 1){
                    //something went wrong with the insert
                    return Status.FAIL_MSG+" something went wrong with registration ";


                }else{
                    return Status.SUCC_MSG;

                }

            }else{
                //user already exists
                return Status.FAIL_MSG+" a user with this username already exists";
            }
        }catch (Exception e){
            e.printStackTrace();
            return Status.FAIL_MSG+" Sql exception" + e.getMessage();
        }
    }

    public static HashMap<String,Object> getUserInfo(String user){
        HashMap<String,Object> response = new HashMap<>();

        //sql statement to 1. check if user exists, 2. see if password matches
        String stmt_1 = "SELECT customers from users where user = '" + user + "';";
        //use trackingDao to verify login
        TrackingDao dao = new TrackingDao();
        dao.createConnection();

        ResultSet customerSet = dao.executeQuery(stmt_1);

        try{
            if(customerSet.next()){
                String customers = customerSet.getString(1);
                String stmt_2 = "SELECT * from customers where cs_code = '" + customers + "';";
                ResultSet customerInfoSet = dao.executeQuery(stmt_2);

                if(customerInfoSet.next()){
                    Map<String,String> customerInfo = new HashMap<>();
                    customerInfo.put("cs_code", customerInfoSet.getString(1));
                    customerInfo.put("cs_name", customerInfoSet.getString(2));
                    customerInfo.put("cs_adr", customerInfoSet.getString(3));
                    customerInfo.put("cs_city", customerInfoSet.getString(4));
                    customerInfo.put("cs_postal", customerInfoSet.getString(5));
                    response.put("customerInfo", customerInfo);

                    response.put("status", Status.SUCC_MSG);
                    return response;
                }else{
                    //customer info missing
                    response.put("status", Status.FAIL_MSG+" no customer info found");
                    return response;
                }

            }else{
                //no customers assigned to user
                response.put("status", Status.FAIL_MSG+" no customer assigned to user");
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("status", Status.FAIL_MSG+" Sql exception" + e.getMessage());
            return response;
        }

    }

    public static HashMap<String,Object> getManifestInfo(String code){
        HashMap<String,Object> response = new HashMap<>();

        //sql statement to 1. check if user exists, 2. see if password matches
        String stmt_1 = "SELECT * from bols where bl_cus = '" + code + "';";
        //use trackingDao to verify login
        TrackingDao dao = new TrackingDao();
        dao.createConnection();

        ResultSet bolsSet = dao.executeQuery(stmt_1);

        try{
            if(bolsSet.next()){
                Map<String,String> bolsMap = new HashMap<>();
                bolsMap.put("bl_manifest", bolsSet.getString(1));
                bolsMap.put("bl_man_date", bolsSet.getString(2));
                bolsMap.put("bl_create_date", bolsSet.getString(3));
                bolsMap.put("bl_man_job", bolsSet.getString(4));
                bolsMap.put("bl_bol", bolsSet.getString(5));
                bolsMap.put("bl_weight", bolsSet.getString(6));
                bolsMap.put("bl_unm", bolsSet.getString(7));
                bolsMap.put("bl_pieces", bolsSet.getString(8));
                bolsMap.put("bl_location", bolsSet.getString(9));
                bolsMap.put("bl_loc_adr", bolsSet.getString(10));
                bolsMap.put("bl_city_prov", bolsSet.getString(11));
                bolsMap.put("bl_postal", bolsSet.getString(12));

                bolsMap.put("bl_load", bolsSet.getString(14));
                bolsMap.put("bl_load_status", bolsSet.getString(15));
                bolsMap.put("bl_sheet", bolsSet.getString(16));
                bolsMap.put("bl_sheet_date", bolsSet.getString(17));
                bolsMap.put("bl_signed_date", bolsSet.getString(18));
                bolsMap.put("bl_detailed_status", bolsSet.getString(19));
                bolsMap.put("r_number", bolsSet.getString(20));
                bolsMap.put("bl_load_info", bolsSet.getString(21));
                response.put("manifestInfo", bolsMap);

                response.put("status", Status.SUCC_MSG);
                return response;

            }else{
                //no customers assigned to user
                response.put("status", Status.FAIL_MSG+" no bols to display");
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("status", Status.FAIL_MSG+" Sql exception" + e.getMessage());
            return response;
        }

    }

    public static void main(String [] args){
        System.out.println(login("test", "test"));
    }
}
