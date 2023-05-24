package com.kiki.favorite.model.service;

import com.kiki.favorite.model.FavoriteDto;

public interface FavoriteService {
	public int isFavorite(FavoriteDto favoriteDto);
	public int registFavorite(FavoriteDto favoriteDto);
	public int deleteFavorite(FavoriteDto favoriteDto);
}
