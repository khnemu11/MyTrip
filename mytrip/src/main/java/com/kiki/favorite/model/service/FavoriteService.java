package com.kiki.favorite.model.service;

import java.util.List;

import com.kiki.favorite.model.FavoriteDto;
import com.kiki.route.model.SearchDto;
import com.kiki.tour.model.TourDto;

public interface FavoriteService {
	public int isFavorite(FavoriteDto favoriteDto);
	public int registFavorite(FavoriteDto favoriteDto);
	public int deleteFavorite(FavoriteDto favoriteDto);
	public List<TourDto> selectMostFavorite();
	public List<TourDto> selectFavoriteList(SearchDto searchDto);
	public Integer countFavoriteList(SearchDto searchDto);
}
