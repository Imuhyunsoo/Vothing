package edu.global.ex.Dao;

import edu.global.ex.Dto.CandidateDto;
import edu.global.ex.Dto.RankDto;
import edu.global.ex.Dto.VoteDto;
import edu.global.ex.Dto.VotingNumDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VotingDao {

    private DataSource dataSource = null;

    public VotingDao() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CandidateDto> list() {

        List<CandidateDto> dtoList = new ArrayList<>();

        System.out.println("list() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT M_NO, M_NAME, P_NAME, (CASE P_SCHOOL " +
                    "WHEN '1' THEN '고졸' " +
                    "WHEN '2' THEN '학사' " +
                    "WHEN '3' THEN '석사' " +
                    "WHEN '4' THEN '박사' END) " +
                    "AS P_SCHOOL, CONCAT(CONCAT(SUBSTR(M_JUMIN, 1, 6), '-'), SUBSTR(M_JUMIN, 7, 7)) AS M_JUMIN, M_CITY, " +
                    "CONCAT(CONCAT(CONCAT(P_TEL1, '-'),CONCAT(P_TEL2, '-')), P_TEL3) AS TEL " +
                    "FROM TBL_MEMBER_202005 MEMBER, TBL_PARTY_202005 PARTY " +
                    "WHERE MEMBER.P_CODE = PARTY.P_CODE ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String m_no = resultSet.getString("m_no");
                String m_name = resultSet.getString("m_name");
                String p_name = resultSet.getString("p_name");
                String p_school = resultSet.getString("p_school");
                String m_jumin = resultSet.getString("m_jumin");
                String m_city = resultSet.getString("m_city");
                String tel = resultSet.getString("tel");

                CandidateDto dto = new CandidateDto(m_no, m_name, p_name, p_school, m_jumin, m_city, tel);
                dtoList.add(dto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public List<VotingNumDto> votingName() {

        List<VotingNumDto> votingNums = new ArrayList<>();

        System.out.println("votingName() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT DISTINCT M_NO FROM TBL_VOTE_202005 ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String m_no = resultSet.getString("m_no");

                VotingNumDto votingNumDto = new VotingNumDto(m_no);
                votingNums.add(votingNumDto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return votingNums;
    }

    public void insertVote(String v_jumin, String v_name, String m_no, String v_time, String v_area, String v_confirm) {

        System.out.println("insertVote() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO TBL_VOTE_202005 (V_JUMIN, V_NAME, M_NO, V_TIME, V_AREA, V_CONFIRM) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,v_jumin);
            preparedStatement.setString(2,v_name);
            preparedStatement.setString(3,m_no);
            preparedStatement.setString(4,v_time);
            preparedStatement.setString(5,v_area);
            preparedStatement.setString(6,v_confirm);

            int update = preparedStatement.executeUpdate();
            System.out.println("업데이트 갯수 : " + update);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<VoteDto> voteList() {

        List<VoteDto> dtoList = new ArrayList<>();

        System.out.println("voteList() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT V_NAME, CONCAT(CONCAT(CONCAT('19',CONCAT(SUBSTR(V_JUMIN, 1, 2), '년')), " +
                    "CONCAT(SUBSTR(V_JUMIN, 3, 2), '월')), CONCAT(SUBSTR(V_JUMIN,4,2), '일생')) AS V_JUMIN, " +
                    "CONCAT('만 ',CONCAT(EXTRACT(YEAR FROM SYSDATE) - " +
                    "(DECODE(SUBSTR(V_JUMIN,7,1),'1', '19','2','19','20') || SUBSTR(V_JUMIN,1,2)),'세'))  as V_AGE, " +
                    "CASE SUBSTR(V_JUMIN,7,1) " +
                    "WHEN '1' THEN '남' " +
                    "WHEN '2' THEN '여' " +
                    "END AS V_GENDER,M_NO, " +
                    "CONCAT(CONCAT(SUBSTR(V_TIME, 1, 2), ':'), SUBSTR(V_TIME,3,2)) AS V_TIME, " +
                    "CASE V_CONFIRM " +
                    "WHEN 'Y' THEN '확인' " +
                    "WHEN 'N' THEN '미확인' " +
                    "END AS V_CONFIRM " +
                    "FROM TBL_VOTE_202005";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String v_name = resultSet.getString("v_name");
                String v_jumin = resultSet.getString("v_jumin");
                String v_age = resultSet.getString("v_age");
                String v_gender = resultSet.getString("v_gender");
                String m_no = resultSet.getString("m_no");
                String v_time = resultSet.getString("v_time");
                String v_confirm = resultSet.getString("v_confirm");

                VoteDto dto = new VoteDto(v_name, v_jumin, v_age, v_gender, m_no, v_time, v_confirm);
                dtoList.add(dto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public List<RankDto> rankList() {

        List<RankDto> rankList = new ArrayList<>();

        System.out.println("rankList() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT VOTE.M_NO AS M_NO, M_NAME, TOTAL AS M_TOTAL FROM (SELECT DISTINCT MEMBER.M_NO, MEMBER.M_NAME " +
                    "FROM TBL_MEMBER_202005 MEMBER, TBL_VOTE_202005 VOTE " +
                    "WHERE VOTE.M_NO = MEMBER.M_NO) VOTE, (SELECT M_NO, COUNT(*) AS TOTAL FROM TBL_VOTE_202005 GROUP BY M_NO) TOTAL " +
                    "WHERE VOTE.M_NO = TOTAL.M_NO ORDER BY TOTAL DESC";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String m_no = resultSet.getString("m_no");
                String m_name = resultSet.getString("m_name");
                String m_total = resultSet.getString("m_total");

                RankDto dto = new RankDto(m_no, m_name, m_total);
                rankList.add(dto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return rankList;
    }
}
