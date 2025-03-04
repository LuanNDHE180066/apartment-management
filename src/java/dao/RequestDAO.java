/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.DBContext;
import model.RequestType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import model.Request;
import model.Resident;
import model.Role;
import model.Staff;
import util.Util;

/**
 *
 * @author thanh
 */
public class RequestDAO extends DBContext {

    public List<Request> getAll() {
        String sql = "select * from Request order by date desc";
        List<Request> list = new ArrayList<>();
        ResidentDAO rd = new ResidentDAO();
        StaffDAO sd = new StaffDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                Resident r = rd.getById(rs.getString("rid"));
                Staff s = sd.getById(rs.getString("sid"));
                String detail = rs.getString("detail");
                String response = rs.getString("response");
                String date = rs.getDate("date").toString();
                String responseDate;
                if (rs.getDate("responseDate") == null) {
                    responseDate = null;
                } else {
                    responseDate = rs.getDate("responseDate").toString();
                }
                String status = rs.getString("status");
                String shift = rs.getString("shift");
                RequestType rt = rtd.getById(rs.getString("tid"));
                Request rq = new Request(id, r, s, detail, response, date, responseDate, status,shift, rt);
                list.add(rq);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Request> getRequestByRolesAndPid(int role, String pId) {
        String sql = "select Request.Id as id,rId,sid,detail,Response,date,responsedate,Request.Status as status, tId ,roleId "
                + "from Request join Staff on Request.[sId]=Staff.Id ";
        sql += " where Staff.id = "+"'"+pId+"'"+" and Staff.roleid = "+role+"  order by date desc";
        List<Request> list = new ArrayList<>();
        ResidentDAO rd = new ResidentDAO();
        StaffDAO sd = new StaffDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                Resident r = rd.getById(rs.getString("rid"));
                Staff s = sd.getById(rs.getString("sid"));
                String detail = rs.getString("detail");
                String response = rs.getString("response");
                String date = rs.getDate("date").toString();
                String responseDate;
                if (rs.getDate("responseDate") == null) {
                    responseDate = null;
                } else {
                    responseDate = rs.getDate("responseDate").toString();
                }
                String status = rs.getString("status");
                String shift = rs.getString("shift");
                RequestType rt = rtd.getById(rs.getString("tid"));
                Request rq = new Request(id, r, s, detail, response, date, responseDate, status,shift, rt);
                list.add(rq);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Request> getRequestByRoles(int role) {
        String sql = "select Request.Id as id,rId,sid,detail,Response,date,responsedate,Request.Status as status, tId ,roleId from Request join Staff on Request.[sId]=Staff.Id  ";
        List<Request> list = new ArrayList<>();
        ResidentDAO rd = new ResidentDAO();
        StaffDAO sd = new StaffDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (role == rs.getInt("roleId")) {
                    String id = rs.getString("id");
                    Resident r = rd.getById(rs.getString("rid"));
                    Staff s = sd.getById(rs.getString("sid"));
                    String detail = rs.getString("detail");
                    String response = rs.getString("response");
                    String date = rs.getDate("date").toString();
                    String responseDate;
                    if (rs.getDate("responseDate") == null) {
                        responseDate = null;
                    } else {
                        responseDate = rs.getDate("responseDate").toString();
                    }
                    String status = rs.getString("status");
                    String shift = rs.getString("shift");
                    RequestType rt = rtd.getById(rs.getString("tid"));
                    Request rq = new Request(id, r, s, detail, response, date, responseDate, status,shift, rt);
                    list.add(rq);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Request> getRequestByStatus(String statu) {
        String sql = "select * from Request where status=?";
        List<Request> list = new ArrayList<>();
        ResidentDAO rd = new ResidentDAO();
        StaffDAO sd = new StaffDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statu);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                Resident r = rd.getById(rs.getString("rid"));
                Staff s = sd.getById(rs.getString("sid"));
                String detail = rs.getString("detail");
                String response = rs.getString("response");
                String date = rs.getDate("date").toString();
                String responseDate;
                if (rs.getDate("responseDate") == null) {
                    responseDate = null;
                } else {
                    responseDate = rs.getDate("responseDate").toString();
                }
                String status = rs.getString("status");
                String shift = rs.getString("shift");
                RequestType rt = rtd.getById(rs.getString("tid"));
                Request rq = new Request(id, r, s, detail, response, date, responseDate, status,shift, rt);
                list.add(rq);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    
   public String getNewestIdRequest(){
       String sql = "select top 1 id from Request order by id desc";
       try(PreparedStatement st = connection.prepareStatement(sql);) {
           ResultSet rs = st.executeQuery();
           while(rs.next()) return rs.getString("id");
       }
       catch (SQLException e){
           System.out.println(e);
       }
       return "";
   }
    public int addRequest(String rId, String detail, RequestType rt) {
        StaffDAO sd = new StaffDAO();
        String nid = getNewestIdRequest();
        Util util = new Util();
        String sql = "insert into request(id,rid,sid,detail,date,status,tid) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "R" + util.getNumberFromTextPlusOne(nid));
            st.setString(2, rId);
            st.setString(3, sd.getByRequestType(rt).getId());
            st.setString(4, detail);
            st.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            st.setString(6, "Waiting");
            st.setString(7, rt.getId());
            st.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
    
    
    public boolean checkShiftStaff(String staffid,String shift){
        Date dates = Date.valueOf(LocalDate.now());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(dates);
        String sql = "Select * from Request where sid='"+staffid+"' and shift='"+shift+"' and date ='" + formatDate + "'";
        try(PreparedStatement st = connection.prepareStatement(sql)){
            ResultSet rs = st.executeQuery();
            while(rs.next()) return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public void deleteRequest(String id){
        String sql = "delete from Request where id ="+"'"+id+"'";
        try(PreparedStatement st = connection.prepareStatement(sql)){
            st.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void AssignRequest(String requestid, String staffid,String shift) {
        String sql = "update Request set sid = ? , Status = 'In process' , Response = 'In process', Shift = ? where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, staffid);
            st.setString(2, shift);
            st.setString(3, requestid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Request> getPageByNumber(List<Request> list, int page, int number) {
        List<Request> listpage = new ArrayList<>();
        int start = number * (page - 1);
        int end = number * page - 1;
        if(list.isEmpty()) return listpage;
        for (int i = start; i <= end; i++) {
            listpage.add(list.get(i));
            if (i == list.size() - 1) {
                break;
            }
        }
        return listpage;
    }

    public List<Request> getByStatus(List<Request> list, String status) {
        List<Request> ls = this.getRequestByStatus(status);
        List<Request> sl = new ArrayList<>();
        if(list.isEmpty()) return null;
        for (Request l : ls) {
            for (Request request : list) {
                if (l.getId().equals(request.getId())) {
                    sl.add(request);
                }
            }
        }
        return sl;
    }
    
    public List<Request> getWaitingTable(List<Request> list) {
        List<Request> sl = new ArrayList<>();
        for (Request request : list) {
            if(request.getStatus().equalsIgnoreCase("No response") || request.getStatus().equalsIgnoreCase("Waiting")){
                sl.add(request);
            }
        }
        return sl;
    }
    
    public List<Request> getInProcessgTable(List<Request> list) {
        List<Request> sl = new ArrayList<>();
        for (Request request : list) {
            if(request.getStatus().equalsIgnoreCase("In process")){
                sl.add(request);

            }
        }
        return sl;
    }
    
    public List<Request> getDoneTable(List<Request> list) {
        List<Request> sl = new ArrayList<>();
        for (Request request : list) {
            if(request.getStatus().equalsIgnoreCase("Done")){
                sl.add(request);
            }
        }
        return sl;
    }

    public List<Request> getByRoles(List<Request> list, int role) {
        List<Request> ls = this.getRequestByRoles(role);
        List<Request> sl = new ArrayList<>();
        for (Request l : ls) {
            for (Request request : list) {
                if (l.getId().equals(request.getId())) {
                    sl.add(request);
                }
            }
        }
        return sl;
    }

    public List<Request> getByResidentID(String id) {
        List<Request> result = new ArrayList<>();
        List<Request> getAllRequest = getAll();
        for (Request request : getAllRequest) {
            if (request.getResidentId().getpId().equals(id)) {
                result.add(request);
            }
        }
        return result;

    }

    public List<Request> getByResidentIDAndDate(String id, String from, String to, String requestType) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Request WHERE rId = ? order by Date desc");
        List<Request> list = new ArrayList<>();
        ResidentDAO rd = new ResidentDAO();
        StaffDAO sd = new StaffDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();

        // Handle optional parameters
        if (from != null && to != null) {
            sql.append(" AND (date BETWEEN ? AND ?)");
        }
        if (requestType != null && !requestType.isEmpty()) {
            sql.append(" AND tId = ?");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            st.setString(1, id);

            int paramIndex = 2;
            if (from != null && to != null) {
                st.setString(paramIndex++, from);
                st.setString(paramIndex++, to);
            }
            if (requestType != null && !requestType.isEmpty()) {
                st.setString(paramIndex++, requestType);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Resident r = rd.getById(id);
                Staff s = sd.getById(rs.getString("sid"));
                String detail = rs.getString("detail");
                String response = rs.getString("response");
                String date = rs.getDate("date").toString();
                String responseDate = rs.getDate("responseDate") != null ? rs.getDate("responseDate").toString() : null;
                String status = rs.getString("status");
                RequestType rt = rtd.getById(rs.getString("tid"));
                Request rq = new Request(id, r, s, detail, response, date, responseDate, status, rt);
                list.add(rq);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger for real applications
        }
        return list;
    }

    public List<String> getAllRequestByStatus(String status) {
        List<String> list = new ArrayList<>();
        String sql = " select * from request where status=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                list.add(id);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Request getById(String id) {
        String sql = "select * from Request where id =?";
        ResidentDAO rd = new ResidentDAO();
        StaffDAO sd = new StaffDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Resident r = rd.getById(rs.getString("rid"));
                Staff s = sd.getById(rs.getString("sid"));
                String detail = rs.getString("detail");
                String response = rs.getString("response");
                String date = rs.getDate("date").toString();
                String responseDate;
                if (rs.getDate("responseDate") == null) {
                    responseDate = null;
                } else {
                    responseDate = rs.getDate("responseDate").toString();
                }
                String status = rs.getString("status");
                RequestType rt = rtd.getById(rs.getString("tid"));
                Request rq = new Request(id, r, s, detail, response, date, responseDate, status, rt);
                return rq;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public void declineRequestWithoutMessageById(String id){
        String sql="update request set Response='Decline', status='Done', responsedate=? where id=?";
        LocalDate date = LocalDate.now();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, Date.valueOf(date));
            st.setString(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void updateRequest(String id,String res,String status,String responseDate){
        String sql="update request set response=?, status=?,responseDate=? where id=?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, res);
            st.setString(2, status);
            st.setString(3, responseDate);
            st.setString(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public boolean Editrequest(Request r){
        RequestTypeDAO rd=new RequestTypeDAO();
        String sql="update Request set tId=?, Detail=? where id=?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, r.getRequestType().getId());
            ps.setString(2, r.getDetail());
            ps.setString(3, r.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public static void main(String[] args) {
        RequestDAO dao = new RequestDAO();
//        List<Request> list = new ArrayList<>();
//        list = dao.getAll();
////        list = dao.getAll();
////        list = dao.getByRoles(list, 5);
////        List<Request> getByRID = dao.getByResidentIDAndDate("P110", "2025-01-01", "2025-01-25", "R001");
////        System.out.println(getByRID.get(0).getRequestType().getName());
//        System.out.println(""+list);
//        System.out.println(""+dao.getInProcessgTable(list));
//        dao.AssignRequest("R005", "S1005");
//        System.out.println(dao.getAllRequestByStatus("waiting").size());
//List<Request> list = dao.getAll();
//List<Request> inprocess_list = dao.getInProcessgTable(list);
//        Util u = new Util();
//        int totalPage_waiting = u.getTotalPage(inprocess_list, 5);
//        System.out.println(inprocess_list.size() + "page: "+totalPage_waiting);
//RequestTypeDAO rd=new RequestTypeDAO();
//Request r= new Request();
//r.setId("R9");
//r.setDetail("hehe");
//r.setRequestType(rd.getById("R003"));
//System.out.println(dao.Editrequest(r));
    }
}
