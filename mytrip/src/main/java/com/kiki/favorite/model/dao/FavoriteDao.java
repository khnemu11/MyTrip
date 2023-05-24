package com.kiki.favorite.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.favorite.model.FavoriteDto;
import com.kiki.tour.model.TourDto;

@Mapper
public interface FavoriteDao {
	public int isFavorite (FavoriteDto favoriteDto);
	public int registFavorite (FavoriteDto favoriteDto);
	public int deleteFavorite (FavoriteDto favoriteDto);
	public List<TourDto> selectMostFavorite();
}
