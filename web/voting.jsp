<%--
  Created by IntelliJ IDEA.
  User: hyunsoolim
  Date: 2022/12/09
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
    <style>
        @import url(style.css);
    </style>
</head>
<body>
<header>
    <h2>(과정평가형 정보처리산업기사) 지역구의원 투표 프로그램 ver 2020-05</h2>
</header>
<nav>
    <div>
        <a href="list.do">후보조회</a>
        <a href="voting.do">투표신청</a>
        <a href="inquiry.do">투표검수조회</a>
        <a href="rank.do">후보자등수</a>
        <a href="index.jsp">홈으로</a>
    </div>
</nav>
<section>
    <h1>투표하기</h1>
    <table width="700" border="1">
        <form action="insert.do" method="post">
            <tr>
                <td>주민번호</td>
                <td class="data"><input type="text" name="v_jumin" size="30">
                    <span>예 : 8906153154726</span></td>
            </tr>
            <tr>
                <td>성명</td>
                <td class="data"><input type="text" name="v_name" size="20"></td>
            </tr>
            <tr>
                <td>투표번호</td>
                <td class="data">
                    <select name="m_no">
                        <c:forEach var="number" items="${votingList}">
                            <option value="${number.m_no}">${number.m_no}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>투표시간</td>
                <td class="data"><input type="text" name="v_time" size="20"></td>
            </tr>
            <tr>
                <td>투표장소</td>
                <td class="data"><input type="text" name="v_area" size="20"></td>
            </tr>
            <tr>
                <td>유권자확인</td>
                <td class="data">
                    <input type="radio" name="v_confirm" value="Y">확인
                    <input type="radio" name="v_confirm" value="N">미확인
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="투표하기">
                    <input type="button" value="다시하기"></td>
            </tr>
        </form>
    </table>
</section>
<footer>
    <h3 id="foot">HRD KOREA Copyright © 2015 All rights reserved Human Resources Development Service of Korea.</h3>
</footer>
</body>
</html>
