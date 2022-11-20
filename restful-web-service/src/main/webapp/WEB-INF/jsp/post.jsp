<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글상세</title>
    <style>
        table {
            width: 100%;
            border: 1px solid #444444;
            border-collapse: collapse;
        }
        table th {
            border: 1px solid #444444;
            text-align: center;
            height: 40px;
            background-color: dodgerblue;
            color: cornsilk;
        }
        table td {
            border: 1px solid #444444;
            text-align: left;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    <h3>글쓰기</h3>
    <hr>
    <form action="updateBoard" method="post">
        <input type="hidden" name="seq" value="${board.boardId}"/>
        <table style="width: 700px; margin: auto">
            <tr>
                <td width="70" style="background-color: dodgerblue; color: cornsilk">제목</td>
                <td><input type="text" name="title" value="${board.boardTitle}"/></td>
            </tr>
            <tr>
                <td style="background-color: dodgerblue; color: cornsilk">작성자</td>
                <td>${board.user}</td>
            </tr>
            <tr>
                <td style="background-color: dodgerblue; color: cornsilk">내용</td>
                <td><textarea name="content" cols="40" rows="10">${board.boardContent}</textarea></td>
            </tr>
            <tr>
                <td style="background-color: dodgerblue; color: cornsilk">등록일</td>
                <td><fmt:formatDate value="${board.boardDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div style="text-align: center;">
                        <input type="submit" value="수정"/>
                    </div>
                </td>
            </tr>
        </table>
    </form>
    <hr>
    <a href="insertBoardView">글등록</a>
    <a href="deleteBoard?seq=${board.seq}">글삭제</a>
    <a href="getBoardList">글목록</a>
</div>
</body>
</html>