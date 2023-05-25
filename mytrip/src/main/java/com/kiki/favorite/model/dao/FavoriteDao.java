package com.kiki.favorite.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.favorite.model.FavoriteDto;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;

@Mapper
public interface FavoriteDao {
	public int isFavorite (FavoriteDto favoriteDto);
	public int registFavorite (FavoriteDto favoriteDto);
	public int deleteFavorite (FavoriteDto favoriteDto);
	public List<TourDto> selectMostFavorite();
	public List<TourDto> selectFavoriteList(SearchDto searchDto);
	public Integer countFavoriteList(SearchDto searchDto);
}
