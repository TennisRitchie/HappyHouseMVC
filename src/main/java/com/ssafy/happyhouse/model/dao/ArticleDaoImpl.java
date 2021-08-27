package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.util.DBUtil;

public class ArticleDaoImpl implements ArticleDao {

	@Override
	public void writeArticle(ArticleDto articleDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("insert into article (userid, subject, content, regtime) \n");
			insertMember.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, articleDto.getUserid());
			pstmt.setString(2, articleDto.getSubject());
			pstmt.setString(3, articleDto.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<ArticleDto> listArticle(String key, String word) throws SQLException {
		List<ArticleDto> list = new ArrayList<ArticleDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select articleno, userid, subject, content, regtime \n");
			sql.append("from article \n");
			if(!word.isEmpty()) {
				if("subject".equals(key)) {
					sql.append("where subject like ? \n");
				} else {
					sql.append("where " + key + " = ? \n");
				}
			}
			sql.append("order by articleno desc \n");
			pstmt = conn.prepareStatement(sql.toString());
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(1, "%" + word + "%");
				else
					pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ArticleDto articleDto = new ArticleDto();
				articleDto.setArticleno(rs.getInt("articleno"));
				articleDto.setUserid(rs.getString("userid"));
				articleDto.setSubject(rs.getString("subject"));
				articleDto.setContent(rs.getString("content"));
				articleDto.setRegtime(rs.getString("regtime"));
				
				list.add(articleDto);
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public ArticleDto getArticle(int articleno) throws SQLException {
		ArticleDto articleDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select articleno, userid, subject, content, regtime \n");
			sql.append("from article \n");
			sql.append("where articleno = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleDto = new ArticleDto();
				articleDto.setArticleno(rs.getInt("articleno"));
				articleDto.setUserid(rs.getString("userid"));
				articleDto.setSubject(rs.getString("subject"));
				articleDto.setContent(rs.getString("content"));
				articleDto.setRegtime(rs.getString("regtime"));
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return articleDto;
	}

	@Override
	public void modifyArticle(ArticleDto articleDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("update article \n");
			insertMember.append("set subject = ?, content = ? \n");
			insertMember.append("where articleno = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, articleDto.getSubject());
			pstmt.setString(2, articleDto.getContent());
			pstmt.setInt(3, articleDto.getArticleno());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(int articleno) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("delete from article \n");
			insertMember.append("where articleno = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setInt(1, articleno);
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}

}
