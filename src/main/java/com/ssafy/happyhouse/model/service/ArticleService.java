package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ArticleDto;

public interface ArticleService {

	public void writeArticle(ArticleDto articleDto) throws Exception;
	public List<ArticleDto> listArticle(String key, String word) throws Exception;
	
	public ArticleDto getArticle(int articleno) throws Exception;
	public void modifyArticle(ArticleDto articleDto) throws Exception;
	public void deleteArticle(int articleno) throws Exception;
	
}
