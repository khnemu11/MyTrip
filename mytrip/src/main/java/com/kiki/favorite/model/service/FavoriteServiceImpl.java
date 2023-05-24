package com.kiki.favorite.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiki.favorite.model.FavoriteDto;
import com.kiki.favorite.model.dao.FavoriteDao;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.dao.TourDao;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	FavoriteDao favoriteDao;
	@Autowired
	public FavoriteServiceImpl(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}
	
	
	@Override
	public int isFavorite(FavoriteDto favoriteDto) {
		return favoriteDao.isFavorite(favoriteDto);
	}

	@Override
	public int registFavorite(FavoriteDto favoriteDto) {
		// TODO Auto-generated method stub
		return favoriteDao.registFavorite(favoriteDto);
	}

	@Override
	public int deleteFavorite(FavoriteDto favoriteDto) {
		// TODO Auto-generated method stub
		return favoriteDao.deleteFavorite(favoriteDto);
	}


	@Override
	public List<TourDto> selectMostFavorite() {
		return favoriteDao.selectMostFavorite();
	}
	
	
}
