package com.kiki.favorite.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kiki.favorite.model.FavoriteDto;

@Mapper
public interface FavoriteDao {
	public int isFavorite (FavoriteDto favoriteDto);
	public int registFavorite (FavoriteDto favoriteDto);
	public int deleteFavorite (FavoriteDto favoriteDto);
}
