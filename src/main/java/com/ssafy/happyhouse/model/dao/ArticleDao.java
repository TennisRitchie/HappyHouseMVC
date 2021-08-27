package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.ArticleDto;

public interface ArticleDao {

	public void writeArticle(ArticleDto articleDto) throws SQLException;
	public List<ArticleDto> listArticle(String key, String word) throws SQLException;
	
	public ArticleDto getArticle(int articleno) throws SQLException;
	public void modifyArticle(ArticleDto articleDto) throws SQLException;
	public void deleteArticle(int articleno) throws SQLException;
	
}
